package com.arshahrear.imageupload;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    ImageView imageView,imageEdit;
    Button uploadButton;
    TextView tvDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageEdit = findViewById(R.id.imageEdit);
        uploadButton = findViewById(R.id.uploadButton);
        tvDisplay = findViewById(R.id.tvDisplay);

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


            }
        });



    }
}
