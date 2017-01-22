package com.tutorials.hp.listviewrealmmdetail.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tutorials.hp.listviewrealmmdetail.R;
import com.tutorials.hp.listviewrealmmdetail.m_DetailActivity.DetailActivity;
import com.tutorials.hp.listviewrealmmdetail.m_Realm.Spacecraft;

import java.util.ArrayList;

/**
 * Created by Oclemy on 6/17/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft> spacecrafts;

    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int position) {
        return spacecrafts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }

        TextView nameTxt= (TextView) convertView.findViewById(R.id.nameTxt);
        TextView propTxt= (TextView) convertView.findViewById(R.id.propellantTxt);
        TextView descTxt= (TextView) convertView.findViewById(R.id.descTxt);
        ImageView img= (ImageView) convertView.findViewById(R.id.spacecraftImage);

        Spacecraft s= (Spacecraft) this.getItem(position);

        final String name=s.getName();
        final String propellant=s.getPropellant();
        final String desc=s.getDescription();
        final String imageUrl=s.getImageUrl().replace("localhost","10.0.2.2");

        nameTxt.setText(name);
        propTxt.setText(propellant);
        descTxt.setText(desc);
        ImageLoader.downloadImage(c, imageUrl, img);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //OPEN DETAIL ACTIVITY
                openDetailActivity(name,propellant,desc,imageUrl);
            }
        });


        return convertView;
    }

    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c, DetailActivity.class);

        //PACK DATA
        i.putExtra("NAME_KEY",details[0]);
        i.putExtra("PROP_KEY",details[1]);
        i.putExtra("DESC_KEY",details[2]);
        i.putExtra("IMAGEURL_KEY",details[3]);

        //start
        c.startActivity(i);
    }

}

















