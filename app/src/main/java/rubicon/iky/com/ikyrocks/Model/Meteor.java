package rubicon.iky.com.ikyrocks.Model;

import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.noodle.Id;

import java.nio.DoubleBuffer;

/**
 * Created by obake on 8/21/2017.
 */

public class Meteor implements Comparable<Meteor>{
    @Id
    public long id;

    public String mass;
    public String name;
    public String nametype;
    public String classname;
    public Double lat;
    public Double lng;
    public Integer year;

    public Meteor(String _id,String _name, String _nametype, String _mass, String _lat, String _lng, String _year, String _class){
        id = Long.parseLong(_id);
        name = _name;
        String yr = _year.split("\\-")[0];
        year = Integer.parseInt(yr);
        nametype=_nametype;
        classname=_class;
        mass=_mass;
        lat = Double.parseDouble(_lat);
        lng = Double.parseDouble(_lng);
    }


    @Override
    public int compareTo(@NonNull Meteor o) {
        return year.compareTo(o.year);
    }
}
