package com.tutorials.hp.listviewrealmmdetail;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tutorials.hp.listviewrealmmdetail.m_Realm.RealmHelper;
import com.tutorials.hp.listviewrealmmdetail.m_Realm.Spacecraft;
import com.tutorials.hp.listviewrealmmdetail.m_UI.CustomAdapter;

import io.realm.Realm;
import io.realm.RealmChangeListener;


public class MainActivity extends AppCompatActivity {

    Realm realm;
    RealmChangeListener realmChangeListener;
    CustomAdapter adapter;
    ListView lv;
    EditText nameEditText,propEditTxt,descEditTxt,urlEditTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv= (ListView) findViewById(R.id.lv);

        //INITIALIZE REALM
        realm=Realm.getDefaultInstance();

        //RETRIEVE
        final RealmHelper helper=new RealmHelper(realm);
        helper.retrieveFromDB();

        //ADAPTER
        adapter=new CustomAdapter(this,helper.refresh());
        lv.setAdapter(adapter);

        //DATA CHANGE
        realmChangeListener=new RealmChangeListener() {
            @Override
            public void onChange() {
                adapter=new CustomAdapter(MainActivity.this,helper.refresh());
                lv.setAdapter(adapter);
            }
        };
        realm.addChangeListener(realmChangeListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               displayInputDialog();
            }
        });
    }

    private void displayInputDialog() {

        Dialog d=new Dialog(this);
        d.setTitle("Save To Realm Database");
        d.setContentView(R.layout.input_dialog);

        //EDITEXTS
        nameEditText= (EditText) d.findViewById(R.id.nameEditText);
        propEditTxt= (EditText) d.findViewById(R.id.propellantEditText);
        descEditTxt= (EditText) d.findViewById(R.id.descEditText);
        urlEditTxt= (EditText) d.findViewById(R.id.urlEditText);
        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);

        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String name=nameEditText.getText().toString();
                String propellant=propEditTxt.getText().toString();
                String desc=descEditTxt.getText().toString();
                String imageUrl=urlEditTxt.getText().toString();

                //VERIFY
                if(name.length()>0 && name != null)
                {
                    //SET DATA
                    Spacecraft s=new Spacecraft();
                    s.setName(name);
                    s.setPropellant(propellant);
                    s.setDescription(desc);
                    s.setImageUrl(imageUrl);

                    //SAVE
                    RealmHelper helper=new RealmHelper(realm);
                    if(helper.save(s))
                    {
                        nameEditText.setText("");
                        propEditTxt.setText("");
                        descEditTxt.setText("");
                        urlEditTxt.setText("");

                    }else
                    {
                        Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();

                    }


                }else
                {
                    Toast.makeText(MainActivity.this, "Name mustn't Be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


        d.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}

















