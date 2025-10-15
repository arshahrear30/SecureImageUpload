package com.arshahrear.imageupload;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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



    }

    //================================stringRequest

    private void stringRequest(String image64){
        progressBar.setVisibility(View.VISIBLE);
        String url="https://nubsoft.xyz/apisecurity.php";
        //link hit করার জন্য StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new এর পর suggestion করবে Response.Listener
        //Response.Listener শেষ ) 1st close bracket এর আগে কমা new ,new লিখলে Response.ErrorListener নিবে ।
        //Response.Listener শেষ ) 1st close bracket এর পর {   } 2nd bracket নেও । 2nd bracket এর মাঝে Mouse right click >>Generate >> Override Methods >> getParams(): নিবো
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
                //getParams() এর মাধ্যমে  encrypted করে data secure manner এ server এ যায়
                //;;;;;;;;;;;;;;;;;;
                Map mymap = new HashMap<String,String>();
                //mymap.put("key","value");
                mymap.put("image",image64);

                return mymap;
                //;;;;;;;;;;;;;;;;;;
                //return super.getParams(); এই লাইন কেটে দেও

            }
        };
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);

    }
    //================================
}
