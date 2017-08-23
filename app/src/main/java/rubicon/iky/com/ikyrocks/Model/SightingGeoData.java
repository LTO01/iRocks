package rubicon.iky.com.ikyrocks.Model;

import android.graphics.Point;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by obake on 8/21/2017.
 */

public class SightingGeoData {

    @SerializedName("type")
    public String type;
    @SerializedName("features")
    public List<Features> features;
    @SerializedName("crs")
    public Crs crs;

    public static class Geometry {
        @SerializedName("type")
        public String type;
        @SerializedName("coordinates")
        public List<Double> coordinates;
    }

    public static class mProperties {
        @SerializedName("mass")
        public String mass;
        @SerializedName("name")
        public String name;
        @SerializedName(":@computed_region_cbhk_fwbd")
        public String Computed_Region_CBHK_FWDB;
        @SerializedName("reclong")
        public String reclong;
        @SerializedName("geolocation_address")
        public String geolocation_address;
        @SerializedName("geolocation_zip")
        public String geolocation_zip;
        @SerializedName("year")
        public String year;
        @SerializedName("geolocation_state")
        public String geolocation_state;
        @SerializedName("fall")
        public String fall;
        @SerializedName("id")
        public String id;
        @SerializedName(":@computed_region_nnqa_25f4")
        public String Computed_Region_NNQA;
        @SerializedName("recclass")
        public String recclass;
        @SerializedName("reclat")
        public String reclat;
        @SerializedName("geolocation_city")
        public String geolocation_city;
        @SerializedName("nametype")
        public String nametype;
    }

    public static class Features {
        @SerializedName("type")
        public String type;
        @SerializedName("geometry")
        public Geometry geometry;
        @SerializedName("properties")
        public Properties properties;
    }

    public static class Properties {
        @SerializedName("name")
        public String name;
    }

    public static class Crs {
        @SerializedName("type")
        public String type;
        @SerializedName("properties")
        public Properties properties;
    }
}
