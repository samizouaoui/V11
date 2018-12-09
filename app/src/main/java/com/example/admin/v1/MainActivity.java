package com.example.admin.v1;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
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

    String poids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DatabaseHelper(this);
        email = (EditText) findViewById(R.id.login);
        mp = (EditText) findViewById(R.id.password);
        Register = (TextView) findViewById(R.id.inscr);
        cnx = (Button) findViewById(R.id.btncnx);


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
           String  m=email.getText().toString();
                String p=mp.getText().toString();

               Cursor res = helper.searchPass(m,p);
                 Cursor resp=helper.getPoids(m);


                if(res.getCount() == 0) {

                    showMessage("Connexion Impossible","Email et/ou mot de passe incorrecte");
                }
                else

                {

                    while (resp.moveToNext()) {
                        poids = resp.getString(0);

                        if (poids==null)
                        {
                            Intent Imc = new Intent(MainActivity.this,CalculImc.class);
                            Imc.putExtra("email",m);
                        startActivity(Imc);
                        }
                        else

                        {
                            Intent Imc = new Intent(MainActivity.this,Profile.class);
                            startActivity(Imc);
                        }

                    }

                }




            }
        });



    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}