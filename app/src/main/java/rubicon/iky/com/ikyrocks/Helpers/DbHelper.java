package rubicon.iky.com.ikyrocks.Helpers;

import android.content.Context;
import android.provider.Contacts;
import android.util.Log;
import com.noodle.Noodle;
import com.noodle.collection.Collection;
import com.noodle.description.Description;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import rubicon.iky.com.ikyrocks.Model.Meteor;
import rubicon.iky.com.ikyrocks.Model.Sighting;
import rubicon.iky.com.ikyrocks.UI.ColorGroup;

/**
 * Created by obake on 8/1/2017.
 */

public  class DbHelper {

    private Context mContext;

    Noodle db;
    Collection mServicesCollection;
      DbHelper Instance;
    public  DbHelper(Context ctx){
     mContext =ctx  .getApplicationContext();
     db=Noodle.with(mContext)
             .addType(Description.of(Meteor.class).withIdField("id").build())
             .build();
        mServicesCollection=db.collectionOf(Meteor.class);

    }
    public   DbHelper getInstance(Context c){
        return Instance = new DbHelper(c);
    }


    public ArrayList<Meteor> GetData(){
        List<Meteor> mList = (List<Meteor>) mServicesCollection.all().now();
        ArrayList<Meteor> returnObj = new ArrayList<Meteor>();

        for(int i=0;i<mList.size();i++) {
            Meteor x = mList.get(i);
            returnObj.add(x);
        }
        return  returnObj;
    }
    public void SaveServices(List<Sighting> response){
        List<Sighting> list= response;
        mServicesCollection=db.collectionOf(Meteor.class);
        List<Meteor> mList = (List<Meteor>) mServicesCollection.all().now();
        for (int i=0;i<mList.size();i++){
            //clear db
            mServicesCollection.delete(mList.get(i).id).now();
        }
        for(int x=0;x<list.size();x++){
           Sighting s = list.get(x);
            Meteor m = new Meteor(s.id,s.name,s.nametype,s.mass,s.reclat,s.reclong,s.year,s.recclass);
            mServicesCollection.put(m).now();
        }
    }
     public void StoreLastUpdateOccurence(String name){
        Noodle n =   Noodle.with(mContext).build();
        n.put("dt_data",name.toString()).now();

    }

    public void SetIsInitialized(Boolean isInit){
        Noodle n =   Noodle.with(mContext).build();
        n.put("hasInitialized",isInit).now();

    }
    public String GetLastUpdateOccurence(){
        Noodle n =   Noodle.with(mContext).build();
        return n.get("dt_data", String.class).now();
    }
    public Boolean IsInitialized(){
        Noodle n =   Noodle.with(mContext).build();
        return n.get("hasInitialized", Boolean.class).now();
    }



}
