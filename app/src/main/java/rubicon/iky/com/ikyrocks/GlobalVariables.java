package rubicon.iky.com.ikyrocks;

import okhttp3.MediaType;

/**
 * Created by obake on 8/21/2017.
 */

public final class GlobalVariables {
    public static final String ACTION_FECTH_DATA ="rubicon.iky.com.fetchData";
    public static String EXTRA_SERVER_RESPONSE="SERVER_RESPONSE";
    public static final MediaType RequestContentType= MediaType.parse("text/xml; charset=utf-8");
    public static final String  DataURl= "https://data.nasa.gov/resource/y77d-th95.json?$where=year > '2010-01-01T00:00:00.000'";
    public static final String  geoDataURl= "https://data.nasa.gov/resource/y77d-th95.geojson?$where=year > '2010-01-01T00:00:00.000'";

}
