package com.example.admin.v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView Register;
    private Button cnx;
    private EditText email;
    private EditText mp;
    DatabaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper=new DatabaseHelper(this);
        email=(EditText)findViewById(R.id.email);
        mp=(EditText)findViewById(R.id.mp);
        Register=(TextView) findViewById(R.id.inscr);
        cnx=(Button)findViewById(R.id.btncnx);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,InscriptionActivity.class);
                startActivity(i);
            }
        });
        cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String Password=helper.searchPass(email);
                if (mp.getText().toString().equals(Password))
                {
                    Intent Imc=new Intent(MainActivity.this,CalculImc.class);
                    startActivity(Imc);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Password don't match",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
