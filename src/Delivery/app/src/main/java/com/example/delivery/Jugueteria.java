package com.example.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Jugueteria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugueteria);
    }

    public void seleccion(View view){
        switch (view.getId()){
            case R.id.btnCarro:
                Intent i=new Intent(this,VentanaCarro.class);
                startActivity(i);
                break;

        }
    }
}
