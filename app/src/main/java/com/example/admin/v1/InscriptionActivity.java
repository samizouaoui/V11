package com.example.admin.v1;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
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
    private EditText poids;
    private EditText taille;
    private Button b1;
    DatabaseHelper helper;
    Button btnviewAll;

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
        poids=(EditText)findViewById(R.id.poids);
        taille=(EditText)findViewById(R.id.taille);
        b1=(Button)findViewById(R.id.ins);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);


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
                    boolean isInserted = helper.insertData(u);
                    if(isInserted == true)
                    { Intent i1=new Intent(InscriptionActivity.this,Photo.class);
                        startActivity(i1);
                    }
                    else
                        Toast.makeText(InscriptionActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                }

                }

            }
        });

        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = helper.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Prenom :"+ res.getString(0)+"\n");
                            buffer.append("Nom :"+ res.getString(1)+"\n");
                            buffer.append("Email :"+ res.getString(2)+"\n");
                            buffer.append("Mot de passe :"+ res.getString(3)+"\n");
                            buffer.append("Poids :"+ res.getString(4)+"\n");
                            buffer.append("Taille :"+ res.getString(5)+"\n\n");

                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );}


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }







}
