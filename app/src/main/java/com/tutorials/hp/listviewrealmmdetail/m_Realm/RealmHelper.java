package com.tutorials.hp.listviewrealmmdetail.m_Realm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

/**
 * Created by Oclemy on 6/17/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class RealmHelper {

    Realm realm;
    RealmResults<Spacecraft> spacecrafts;
    Boolean saved=null;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //WRITE
    public Boolean save(final Spacecraft spacecraft)
    {
        if(spacecraft==null)
        {
            saved=false;
        }else
        {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    try
                    {
                        realm.copyToRealm(spacecraft);
                        saved=true;

                    }catch (RealmException e)
                    {
                        e.printStackTrace();
                        saved=false;
                    }
                }
            });
        }


        return saved;
    }

    //READ
    public void retrieveFromDB()
    {
        spacecrafts=realm.where(Spacecraft.class).findAll();
    }

    //REFRESH
    public ArrayList<Spacecraft> refresh()
    {
        ArrayList<Spacecraft> latest=new ArrayList<>();
        for (Spacecraft s : spacecrafts)
        {
            latest.add(s);
        }

        return latest;
    }
}















