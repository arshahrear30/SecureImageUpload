package com.arshahrear.imageupload;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView imageView,imageEdit;
    Button uploadButton;
    TextView tvDisplay;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageEdit = findViewById(R.id.imageEdit);
        uploadButton = findViewById(R.id.uploadButton);
        tvDisplay = findViewById(R.id.tvDisplay);
        progressBar = findViewById(R.id.progressBar);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //image to base64 convert

                BitmapDrawable bitmapDrawable=(BitmapDrawable)imageView.getDrawable();//iamge ta bitmap e auto same create hobay
                Bitmap bitmap=bitmapDrawable.getBitmap();//akon bitmap code pabo

                ByteArrayOutputStream outputStream=new ByteArrayOutputStream();//Bitmap ke bytearray tay compress koray convert korvay abong byteArrayOutputStream tay rakbbay
                bitmap.compress(Bitmap.CompressFormat.JPEG,60,outputStream);// 3ta option a kaj kore //user thekay nilay coto jaygar jonno quality coto nibo


                byte[] imageBytes=outputStream.toByteArray(); //bytearray convert hoicay
                String image64= Base64.encodeToString(imageBytes, Base64.DEFAULT); //base64 a convert korlam
                tvDisplay.setText(image64);

                stringRequest(image64);


            }
        });

        //3065iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
        //আমরা প্রথমে ছবিটা অ্যাপস এর মধ্যে রাখবো যাতে গ্যালারিতে নেওয়ার সাথে সাথে অ্যাপস এর মধ্যে শো করে তারপর সাবমিট বাটনে ক্লিক করলে তখন সেটা
        // সার্ভারে যাবে এতে করে ইউজার এক্সপেরিয়েন্স ভালো হবে আর যদি শুরুতেই আমরা সার্ভারে দিতে যাই তাহলে এটা লোড হয়ে আসতে আসতে অনেক সময় লাগবে

        imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Intent intentgalary = new Intent(Intent.ACTION_PICK);
                intentgalary.setType("image/*");
                gallerylauncher.launch(intentgalary); */

                if (checkCameraPermission()) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 100);

                }




            }
        });

        //3065iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
    }

    //{{{{{{{{{{{{{{{{{{{{{{{{{{{{{
    private boolean checkCameraPermission(){

        boolean hasPermission = false;

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) //already permission newa asey
        {hasPermission = true;
        }else {
            hasPermission = false;
            ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.CAMERA},100); //string array call korci

        }

        return hasPermission;



    }



    //{{{{{{{{{{{{{{{{{{{{{{{{{{{{{




    //registerForActivityResult এর কাজ হইলো gallary থেকে image selection এ help করা
    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    ActivityResultLauncher<Intent> gallerylauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) { //image click er por result dibay kon image select korcay

                    if (result.getResultCode() == RESULT_OK) {
                       tvDisplay.setText("Image Selected");
                       Intent data = result.getData();
                       Uri uri = data.getData(); //image er url .. online hoilay url bolay .. ofline file hoilay uri bolay

                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imageView.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }else {
                        tvDisplay.setText("Image not Selected");
                    }


                }
            });


            //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA


            //================================stringRequest

    private void stringRequest(String image64){
        progressBar.setVisibility(View.VISIBLE);
        String url="https://nubsoft.xyz/secure.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tvDisplay.setText(response);
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                tvDisplay.setText(error.toString());
                progressBar.setVisibility(View.GONE);

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map mymap = new HashMap<String,String>();

                mymap.put("image",image64);

                return mymap;

            }
        };
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);

    }
    //================================
}
