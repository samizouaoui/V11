package com.example.admin.v1;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Photo extends AppCompatActivity {
    private final static int PICK_IMAGE=100;
    Uri imageUri;
    Button imp,suivant,ignorer;
    ImageView p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        p=(ImageView)findViewById(R.id.p);
        imp=(Button)findViewById(R.id.imp);
        suivant=(Button)findViewById(R.id.suiv);
        ignorer=(Button)findViewById(R.id.ign);
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(Photo.this,CalculImc.class);
                startActivity(intent2);
            }
        });
        ignorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(Photo.this,CalculImc.class);
                startActivity(intent2);
            }
        });
        imp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });
    }

    protected void OpenGallery() {
        Intent gallery =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
}
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE){
            imageUri=data.getData();
            p.setImageURI(imageUri);
        }
    }
}