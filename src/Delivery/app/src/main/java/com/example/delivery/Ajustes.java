package com.example.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Ajustes extends AppCompatActivity {

    private TextView estado,informacion;
    private Button boton;
    private String es;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        estado=(TextView)findViewById(R.id.txtreconocimiento);
        boton=(Button)findViewById(R.id.btnactivar);
        informacion=(TextView)findViewById(R.id.txtInformacion);
        verificar();
    }

    public void verificar(){

        AdminSQLiteOpenHelper adim = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = adim.getWritableDatabase();

        Cursor fila = db.rawQuery("select estado from reconocimiento where codigo=" + 1, null);

        if (fila.moveToFirst()) {
            // Toast.makeText(this, "entre al verificar", Toast.LENGTH_SHORT).show();
            es=fila.getString(0);
            estado.setText(fila.getString(0));
            if(es.equals("activado")){
                //estado.setText(fila.getString(0));
                boton.setText("Desactivar");
                informacion.setText("Desea desactivar el reconocimiento facial");
            }else {
                // estado.setText("Desactivado");
                boton.setText("Activar");
                informacion.setText("Desea activar el reconocimiento facial");
            }
        }
        db.close();
    }

    public void boton(View view) {

        AdminSQLiteOpenHelper adim = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = adim.getWritableDatabase();

        if (boton.getText().equals("Activar")) {
            ContentValues valor = new ContentValues();
            valor.put("codigo", 1);
            valor.put("estado", 1);
            db.update("reconocimiento", valor, "codigo=" + 1, null);
            db.close();
            Toast.makeText(this, "El reconocimiento facial se a activado", Toast.LENGTH_SHORT).show();
            boton.setText("Desactivar");
            informacion.setText("Desea desactivar el reconocimiento facial");
            estado.setText("Activado");
        }else {
            ContentValues valor = new ContentValues();
            valor.put("codigo", 1);
            valor.put("estado", 0);
            db.update("reconocimiento", valor, "codigo=" + 1, null);
            db.close();
            Toast.makeText(this, "El reconocimiento facial se a desactivado", Toast.LENGTH_SHORT).show();
            boton.setText("Activar");
            informacion.setText("Desea activar el reconocimiento facial");
            estado.setText("Desactivado");
        }
    }

}
