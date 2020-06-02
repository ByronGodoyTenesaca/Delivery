package com.example.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Carrito extends AppCompatActivity {

    ListView lv;
    TextView txtpagar;
    ArrayList<Car> lista = new ArrayList<>();
    ArrayList<String> lis=new ArrayList<>();
    ArrayAdapter adaptador;
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        lv=(ListView)findViewById(R.id.lista);
        txtpagar=(TextView)findViewById(R.id.txtTotal);
        listado();
        //adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,lis);
        lv.setAdapter(adaptador);
    }

    public void listado(){

        double pagar=0;

        AdminSQLiteOpenHelper adim = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = adim.getWritableDatabase();

        Cursor productos = db.rawQuery("select producto,cantidad,costo from carrito", null);

        if(productos.moveToFirst()){
            do{
                car= new Car();
            car.setProducto(productos.getString(0));
            car.setCantidad(productos.getInt(1));
            car.setCosto(productos.getDouble(2));
            pagar=pagar+productos.getDouble(2);
            lis.add(car.toString());
            lista.add(car);

            }while(productos.moveToNext());
           txtpagar.setText(String.valueOf(pagar));
        }

        Toast.makeText(this, "llegue", Toast.LENGTH_SHORT).show();
    }


    // cambiar cuando este el reconocimiento

    public void aceptar(View view){
        AdminSQLiteOpenHelper adim = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = adim.getWritableDatabase();

        db.delete("carrito","cantidad>0",null);
        Toast.makeText(this, "Compra exitosa", Toast.LENGTH_SHORT).show();
    }
}
