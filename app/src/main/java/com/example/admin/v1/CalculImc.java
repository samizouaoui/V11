package com.example.admin.v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CalculImc extends AppCompatActivity {
      EditText poids;
    EditText taille;
    TextView imc;
    Button calculer;
    ImageView img;
    ImageView f3;
    ImageView f1;
    ImageView f6;
    ImageView f2;
    ImageView f4;
    DatabaseHelper helper;
    @Override
    //n
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul_imc);
        helper = new DatabaseHelper(this);
        poids = (EditText) findViewById(R.id.poids);
        taille = (EditText) findViewById(R.id.taille);
        imc = (TextView) findViewById(R.id.imc);
        calculer = (Button) findViewById(R.id.btn);

        img=(ImageView) findViewById(R.id.imgimc);
        f3=(ImageView) findViewById(R.id.f11);
        f1=(ImageView) findViewById(R.id.f1);
        f6=(ImageView) findViewById(R.id.f6);
        f2=(ImageView) findViewById(R.id.f2);
        f4=(ImageView) findViewById(R.id.f4);


        calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentrecup=getIntent();
                String email=intentrecup.getStringExtra("email");
                String pd=poids.getText().toString();
                String ta=taille.getText().toString();
if ((helper.updatePT(email,pd,ta)) == true)
    Toast.makeText(CalculImc.this,"Data Inserted",Toast.LENGTH_LONG).show();
else
    Toast.makeText(CalculImc.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                float res;
                float t=(Float.parseFloat(ta));
                res =(Float.parseFloat(pd))/(t*t);
                img.setVisibility(View.VISIBLE);
                imc.setText(String.valueOf("Votre IMC est : "+res));
                if (0<res && res <18.5) {
                    f1.setVisibility(View.VISIBLE);
                    f2.setVisibility(View.INVISIBLE);
                    f3.setVisibility(View.INVISIBLE);
                    f4.setVisibility(View.INVISIBLE);
                    f6.setVisibility(View.INVISIBLE);

                }
                if (18.5<res && res <24.9)
                {
                    f1.setVisibility(View.INVISIBLE);
                    f2.setVisibility(View.VISIBLE);
                    f3.setVisibility(View.INVISIBLE);
                    f4.setVisibility(View.INVISIBLE);
                    f6.setVisibility(View.INVISIBLE);
                }
                if (24.9<res && res <29.9)
                {
                    f1.setVisibility(View.INVISIBLE);
                    f2.setVisibility(View.INVISIBLE);
                    f3.setVisibility(View.VISIBLE);
                    f4.setVisibility(View.INVISIBLE);
                    f6.setVisibility(View.INVISIBLE);
                }
                if (29.9<res && res <40)
                {
                    f1.setVisibility(View.INVISIBLE);
                    f2.setVisibility(View.INVISIBLE);
                    f3.setVisibility(View.INVISIBLE);
                    f4.setVisibility(View.VISIBLE);
                    f6.setVisibility(View.INVISIBLE);
                }
                if ( res >40)
                {
                    f1.setVisibility(View.INVISIBLE);
                    f2.setVisibility(View.INVISIBLE);
                    f3.setVisibility(View.INVISIBLE);
                    f4.setVisibility(View.INVISIBLE);
                    f6.setVisibility(View.VISIBLE);
                }

            }
        });

    }
}
