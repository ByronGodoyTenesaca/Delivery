package com.example.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VentanaCarro extends AppCompatActivity {

    EditText txtCantidad;
    TextView txttotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_carro);
        txtCantidad=(EditText)findViewById(R.id.txtCanitdad);
        txttotal=(TextView)findViewById(R.id.txtTotal);

    }


    public void aceptar(View view){
        AdminSQLiteOpenHelper adim = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = adim.getWritableDatabase();
        ContentValues valor = new ContentValues();
        valor.put("producto", "carro");
        valor.put("cantidad",Integer.parseInt(txtCantidad.getText().toString()));
        valor.put("costo",Double.parseDouble(txttotal.getText().toString()));
        db.insert("carrito", null, valor);
        db.close();
        txttotal.setText("");
        txtCantidad.setText("");
        Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show();
    }

    public void calculo(View view){
        double total=2*(Integer.parseInt(txtCantidad.getText().toString()));
        txttotal.setText(String.valueOf(total));
    }
}
