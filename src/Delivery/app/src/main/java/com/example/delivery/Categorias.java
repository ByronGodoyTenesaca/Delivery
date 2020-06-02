package com.example.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class Categorias extends AppCompatActivity {

    private FeatureCoverFlow mCoverFlow;
    private TextSwitcher mTitle;
    private CatAdapter mAdapter;
    private ArrayList<model> mData=new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        mData.add(new model(R.drawable.bazar,R.string.bazar));
        mData.add(new model(R.drawable.comidarapida,R.string.comidarapida));
        mData.add(new model(R.drawable.jugueteria,R.string.jugueteria));

        mTitle=(TextSwitcher) findViewById(R.id.title);
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater=LayoutInflater.from(Categorias.this);
                TextView textView=(TextView)inflater.inflate(R.layout.item_title,null);
                return textView;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        Animation out=AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        mAdapter=new CatAdapter(this);
        mAdapter.setData(mData);
        mCoverFlow=(FeatureCoverFlow)findViewById(R.id.coverflow);
        mCoverFlow.setAdapter(mAdapter);


        // hacer cuando aplasta en una imagen
        mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Categorias.this, getResources().getString(mData.get(position).titleResId), Toast.LENGTH_SHORT).show();
                if(getResources().getString(mData.get(position).titleResId).equals("Jugueteria")){
                    cambiar();
                }
            }
        });
        mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(getResources().getString(mData.get(position).titleResId));
            }

            @Override
            public void onScrolling() {
                mTitle.setText("");
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu., menu);
        return true;
    }

    public void cambiar(){
        Intent i=new Intent(this,Jugueteria.class);
        startActivity(i);
    }

}
