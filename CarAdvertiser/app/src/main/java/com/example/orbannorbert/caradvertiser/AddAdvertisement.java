package com.example.orbannorbert.caradvertiser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class AddAdvertisement extends Fragment {


    EditText image;
    EditText price;
    EditText title;
    EditText type;
    EditText model;
    EditText sdescription;
    Button chooseImg;
    Uri filePath;
    Uri downloadUrl;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storref=storage.getReferenceFromUrl("gs://caradvertiser-9bbb0.appspot.com");
    ProgressDialog pd;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DatabaseReference veh=ref.child("vehicles");
    ArrayList<String>images=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_add_advertisement, container, false);

        pd = new ProgressDialog(this.getContext());
        pd.setMessage("Uploading....");

         price = (EditText)v.findViewById(R.id.price);
         title = (EditText)v.findViewById(R.id.atitle);
         type  = (EditText)v.findViewById(R.id.type);
         model = (EditText)v.findViewById(R.id.model);
         sdescription = (EditText)v.findViewById(R.id.sdescription);


        final Button add = (Button) v.findViewById(R.id.add);
        chooseImg = (Button)v.findViewById(R.id.choseImg);
        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
            }
        });



         add.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {

                 if(filePath != null) {

                        String seged=filePath.toString();
                     StorageReference childRef = storref.child(seged);

                     //uploading the image
                     UploadTask uploadTask = childRef.putFile(filePath);
                     uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                         @Override
                         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                             pd.dismiss();
                             Toast.makeText(getActivity(), "Upload successful", Toast.LENGTH_SHORT).show();
                             Uri downloadUrl = taskSnapshot.getDownloadUrl();
                             images.add(downloadUrl.toString());
                             uploadDatabase();
                         }
                     }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             pd.dismiss();
                             Toast.makeText(getActivity(), "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                         }
                     });

                 }
                 else{
                     Toast.makeText(getActivity(),"Select an image", Toast.LENGTH_SHORT).show();
                 }


            }
        });



        return v;
    }

    private void uploadDatabase(){




        Vehicle vehicle = new Vehicle();
        vehicle.setTitle(title.getText().toString());
        vehicle.setShortDescription(sdescription.getText().toString());
        vehicle.setPrice(Double.parseDouble(price.getText().toString()));
        vehicle.setSubType(model.getText().toString());
        vehicle.setType(type.getText().toString());
        vehicle.setEngine(1000);
        vehicle.setImages(images);
        vehicle.setHorsePower(100);
        vehicle.setBodyType("dasdsa");
        vehicle.setKm(1000);
        vehicle.setFuelType("benz");
        vehicle.setNumberOfDoors(4);
        vehicle.setDescription("fsdfdsfsdfsdf");
        vehicle.setSold(false);
        vehicle.setYear(2012);
        vehicle.setTags(images);
        String key = database.getReference("todoList").push().getKey();
        vehicle.setId(key);
        veh.child(key).setValue(vehicle);
        Advertises advertises=new Advertises();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentplace, advertises,"findThisFragment")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

        }
    }


}
