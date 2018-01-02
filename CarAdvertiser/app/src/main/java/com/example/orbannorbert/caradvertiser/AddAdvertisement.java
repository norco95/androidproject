package com.example.orbannorbert.caradvertiser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class AddAdvertisement extends Fragment implements ItemClickListener{


    Button edit;
    Button add;
    Button delete;
    EditText description;
    EditText fueltype;
    EditText price;
    EditText km;
    EditText engine;
    EditText title;
    EditText type;
    EditText model;
    EditText sdescription;
    EditText horsepower;
    EditText bodytype;
    EditText numberofdors;
    EditText year;
    ArrayList<ImaginesModel> imgs;
    private  int i=0;
    Button chooseImg;
    Uri filePath;
    ArrayList<Uri> filePatchs=new ArrayList<Uri>();
    ArrayList<String> images1=new ArrayList<>();
    Uri downloadUrl;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storref=storage.getReferenceFromUrl("gs://caradvertiser-9bbb0.appspot.com");
    ProgressDialog pd;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DatabaseReference veh=ref.child("vehicles");
    DatabaseReference user=ref.child("users");
    ArrayList<String>images=new ArrayList<>();
    private String Id=null;
    public void updateText(String text){
            Id=text;
    }
    String id;
    User owner=new User();

    View s;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_add_advertisement, container, false);
        s=v;
        pd = new ProgressDialog(this.getContext());
        pd.setMessage("Uploading....");

        add = (Button) v.findViewById(R.id.add);
        edit=(Button) v.findViewById(R.id.edit);
        delete=(Button) v.findViewById(R.id.delete);

        price = (EditText)v.findViewById(R.id.price);
        horsepower = (EditText)v.findViewById(R.id.horsepower);
        title = (EditText)v.findViewById(R.id.atitle);
        type  = (EditText)v.findViewById(R.id.type);
        model = (EditText)v.findViewById(R.id.model);
        engine = (EditText)v.findViewById(R.id.engine);
        bodytype = (EditText)v.findViewById(R.id.bodytype);
        km = (EditText)v.findViewById(R.id.km);
        numberofdors = (EditText)v.findViewById(R.id.numberofdors);
        fueltype= (EditText)v.findViewById(R.id.fueltype);
        sdescription = (EditText)v.findViewById(R.id.sdescription);
        description=(EditText)v.findViewById(R.id.description);
        year=(EditText)v.findViewById(R.id.year);

        id=((MainActivity)getActivity()).getLoginId();
        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    owner=ds.getValue(User.class);
                    Log.d("username",owner.getEmail());
                    if(owner.getId()==id)
                    {
                        break;
                    }

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("asdas","canacelled");

            }
        });

        if(Id!=null)
        {

            add.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);




            veh.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot ds: dataSnapshot.getChildren()){

                        Vehicle v=ds.getValue(Vehicle.class);
                        if(v.getId()==Id)
                        {
                            price.setText(Double.toString(v.getPrice()));
                            description.setText(v.getDescription());
                            model.setText(v.getType());
                            type.setText(v.getSubType());
                            km.setText(Integer.toString(v.getKm()));
                            fueltype.setText(v.getFuelType());
                            horsepower.setText(Integer.toString(v.getHorsePower()));
                            numberofdors.setText(Integer.toString(v.getNumberOfDoors()));
                            year.setText(Integer.toString(v.getYear()));
                            engine.setText(Double.toString(v.getEngine()));
                            bodytype.setText(v.getBodyType());
                            sdescription.setText(v.getShortDescription());
                            title.setText(v.getTitle());
                            images=v.getImages();
                            initView(s);
                        }

                    }

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("asdas","canacelled");

                }
            });


        }
        else
        {
            add.setVisibility(View.VISIBLE);
            edit.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
        }



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

        delete = (Button)v.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           veh.child(Id).child("sold").setValue(true);
                Advertises advertises=new Advertises();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentplace, advertises,"findThisFragment")
                        .addToBackStack(null)
                        .commit();

            }
        });

        edit = (Button)v.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=0;

                for(Uri a: filePatchs)
                {
                    if(a != null) {

                        String seged=a.toString();
                        StorageReference childRef = storref.child(seged);

                        //uploading the image
                        final UploadTask uploadTask = childRef.putFile(a);
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                i++;
                                pd.dismiss();
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                images.add(downloadUrl.toString());
                                if(i==filePatchs.size())
                                {
                                    editDatabase();

                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pd.dismiss();
                                //  Toast.makeText(getActivity(), "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }

                if(filePatchs.size()==0)
                {
                    editDatabase();
                }



            }
        });
         add.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {

                 i=0;

                 for(Uri a: filePatchs)
                 {
                     if(a != null) {

                         String seged=a.toString();
                         StorageReference childRef = storref.child(seged);

                         //uploading the image
                         final UploadTask uploadTask = childRef.putFile(a);
                         uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                             @Override
                             public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                 i++;
                                 pd.dismiss();
                                 Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                 images.add(downloadUrl.toString());
                                 if(i==filePatchs.size())
                                 {
                                     uploadDatabase();

                                 }
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 pd.dismiss();
                               //  Toast.makeText(getActivity(), "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                             }
                         });

                     }

                 }

                 Toast.makeText(getActivity(), "Upload successful", Toast.LENGTH_SHORT).show();

            }
        });



        return v;
    }


    private void editDatabase()
    {
        veh.child(Id).child("price").setValue(Double.parseDouble(price.getText().toString()));
        veh.child(Id).child("description").setValue(description.getText().toString());
        veh.child(Id).child("type").setValue(model.getText().toString());
        veh.child(Id).child("subType").setValue(type.getText().toString());
        veh.child(Id).child("km").setValue(Integer.parseInt(km.getText().toString()));
        veh.child(Id).child("fuelType").setValue(fueltype.getText().toString());
        veh.child(Id).child("horsePower").setValue(Integer.parseInt(horsepower.getText().toString()));
        veh.child(Id).child("numberOfDoors").setValue(Integer.parseInt(numberofdors.getText().toString()));
        veh.child(Id).child("year").setValue(Integer.parseInt(year.getText().toString()));
        veh.child(Id).child("engine").setValue(Double.parseDouble(engine.getText().toString()));
        veh.child(Id).child("bodyType").setValue(bodytype.getText().toString());
        veh.child(Id).child("shortDescription").setValue(sdescription.getText().toString());
        veh.child(Id).child("title").setValue(title.getText().toString());
        veh.child(Id).child("images").setValue(images);


        Advertises advertises=new Advertises();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentplace, advertises,"findThisFragment")
                .addToBackStack(null)
                .commit();
    }

    private void uploadDatabase() {

        /*Vehicle vehi = new Vehicle.VehicleBuilder(images,

                Double.parseDouble(price.getText().toString()),
                model.getText().toString(),
                title.getText().toString(),
                type.getText().toString(),
                sdescription.getText().toString()).build();
        if (!price.getText().toString().matches("")) {
            Log.d("price::", price.getText().toString());
            vehi.setPrice(Double.parseDouble(price.getText().toString()));
        }*/

        Vehicle vehicle = new Vehicle();

        vehicle.setTitle(title.getText().toString());
        vehicle.setShortDescription(sdescription.getText().toString());
        if (!price.getText().toString().matches("")) {
            Log.d("price::", price.getText().toString());
            vehicle.setPrice(Double.parseDouble(price.getText().toString()));
        }
        vehicle.setType(model.getText().toString());
        vehicle.setSubType(type.getText().toString());
        if (!engine.getText().toString().matches("")) {
            vehicle.setEngine(Double.parseDouble(engine.getText().toString()));
        } else {
            vehicle.setEngine(0);
        }

        vehicle.setImages(images);
        if (!horsepower.getText().toString().matches("")) {
            vehicle.setHorsePower(Integer.parseInt(horsepower.getText().toString()));
        } else {
            vehicle.setHorsePower(0);
        }
        vehicle.setBodyType(bodytype.getText().toString());
        if (!km.getText().toString().matches("")) {
            vehicle.setKm(Integer.parseInt(km.getText().toString()));
        } else {
            vehicle.setKm(0);
        }
        vehicle.setFuelType(fueltype.getText().toString());
        if (!numberofdors.getText().toString().matches("")) {
            vehicle.setNumberOfDoors(Integer.parseInt(numberofdors.getText().toString()));
        } else {
            vehicle.setNumberOfDoors(0);
        }

        if (id != null) {
            vehicle.setOwnerId(id);
        }
        vehicle.setDescription(description.getText().toString());

        vehicle.setSold(false);

        if (!year.getText().toString().matches("")) {
            vehicle.setYear(Integer.parseInt(year.getText().toString()));
        } else {
            vehicle.setYear(0);
        }


        vehicle.setTags(images);
        String key = database.getReference("todoList").push().getKey();
        vehicle.setId(key);

        //vehi.setId(key);
        //vehi.setTags(images);

        veh.child(key).setValue(vehicle);

       // veh.child(key).setValue(vehi);

        ArrayList<String> vehicles=new ArrayList<>();
        if(owner.getVehicleIds()!=null)
        {
            vehicles=owner.getVehicleIds();
        }
        vehicles.add(key);
        user.child(id).child("vehicleIds").setValue(vehicles);
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
            if(filePath!=null)
            {

                filePatchs.add(filePath);
                initView(s);
            }
        }
    }

    private void initView(View v)
    {
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        imgs = prepareData();
        imageAdapter adapter = new imageAdapter(getActivity().getApplicationContext(),imgs);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    private ArrayList prepareData(){

        ArrayList img = new ArrayList<>();
        for(Uri a :filePatchs){

            ImaginesModel imgmodel = new ImaginesModel();
            imgmodel.setName(a.toString());
            img.add(imgmodel);
        }
        for(String i :images)
        {

            ImaginesModel imgmodel = new ImaginesModel();
            imgmodel.setName(i);
            img.add(imgmodel);
        }
        return img;
    }


    @Override
    public void onClick(View view,int position)
    {
        for(Uri uri:filePatchs)
        {
            if(imgs.get(position).getName().matches(uri.toString()))
            {
                filePatchs.remove(uri);
                break;
            }

        }
        for(String str:images)
        {
            if(str.equals(imgs.get(position).getName()))
            {
                images.remove(str);
                break;
            }
        }

        initView(s);
    }


}
