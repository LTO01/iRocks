package rubicon.iky.com.ikyrocks.Model;

import android.graphics.Point;

import com.google.gson.annotations.SerializedName;
import com.noodle.Id;
import com.noodle.collection.Collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by obake on 8/21/2017.
 */

public class Sighting {

    @SerializedName("fall")
    public String fall;
    @SerializedName("geolocation")
    public Geolocation geolocation;

    @SerializedName("id")
    public String id;

    @SerializedName("mass")
    public String mass;
    @SerializedName("name")
    public String name;
    @SerializedName("nametype")
    public String nametype;
    @SerializedName("recclass")
    public String recclass;
    @SerializedName("reclat")
    public String reclat;
    @SerializedName("reclong")
    public String reclong;
    @SerializedName("year")
    public String year;



    public static class Geolocation {
        @SerializedName("type")
        public String type;
        @SerializedName("coordinates")
        public List<Double> coordinates;
    }
}
