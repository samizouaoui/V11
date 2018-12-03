package com.example.admin.v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {
    private EditText prenom;
    private EditText nom;
    private EditText email;
    private EditText mp;
    private EditText cmp;
    private Button b1;
    DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        helper=new DatabaseHelper(this);
        prenom=(EditText)findViewById(R.id.prenom);
        nom=(EditText)findViewById(R.id.nom);
        email=(EditText)findViewById(R.id.email);
        mp=(EditText)findViewById(R.id.mp);
        cmp=(EditText)findViewById(R.id.cmp);
        b1=(Button)findViewById(R.id.ins);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=prenom.getText().toString();
                String s2=nom.getText().toString();
                String s3=email.getText().toString();
                String s4=mp.getText().toString();
                String s5=cmp.getText().toString();
                if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else
                {if (!s4.equals(s5))
                {
                    Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    user u=new user();
                    u.setPrenom(s1);
                    u.setNom(s2);
                    u.setEmail(s3);
                    u.setMp(s4);
                    helper.insertuser(u);
                }
                }
            }
        });


    }
}
