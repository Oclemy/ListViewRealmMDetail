package com.tutorials.hp.listviewrealmmdetail.m_DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tutorials.hp.listviewrealmmdetail.R;
import com.tutorials.hp.listviewrealmmdetail.m_UI.ImageLoader;


public class DetailActivity extends AppCompatActivity {

   TextView nameDetailTxt,propDetailTxt,descDetailTxt;
   ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameDetailTxt= (TextView) findViewById(R.id.nameDetailTxt);
        propDetailTxt= (TextView) findViewById(R.id.propellantDetailTxt);
        descDetailTxt= (TextView) findViewById(R.id.descDetailTxt);
        img= (ImageView) findViewById(R.id.spacecraftDetailImg);

        //RECEIVE DATA
        Intent i=this.getIntent();

        String name=i.getExtras().getString("NAME_KEY");
        String propellant=i.getExtras().getString("PROP_KEY");
        String desc=i.getExtras().getString("DESC_KEY");
        String imageurl=i.getExtras().getString("IMAGEURL_KEY");

        //BIND
        nameDetailTxt.setText(name);
        propDetailTxt.setText(propellant);
        descDetailTxt.setText(desc);
        ImageLoader.downloadImage(this,imageurl,img);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
