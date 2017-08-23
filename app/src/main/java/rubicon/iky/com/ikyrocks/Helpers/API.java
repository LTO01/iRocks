package rubicon.iky.com.ikyrocks.Helpers;


import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rubicon.iky.com.ikyrocks.GlobalVariables;

/**
 * Created by obake on 7/21/2017.
 */

public class API {
    public static void FetchData(Context c){

        new Post(c,GlobalVariables.DataURl,null,GlobalVariables.ACTION_FECTH_DATA).execute();

    }
       public static class Post extends AsyncTask<Void, Void, String> {

        private Exception exception;
        private String mUrl;
        private String mObject;
        private Context mContext;
        private String mFilter;
        public Post(Context c, String url, String data,String intentFilter){
            super();
            mUrl=url;
            mObject=data;
            mContext=c;
            mFilter=intentFilter;
        }

       @Override
       protected String doInBackground(Void... params) {
           String obj="";

           try {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                       .url(mUrl)
                       .addHeader("X-App-Token","iF6OUYNiIcNCCTQMkVY0NUXa9").get()
                       .build();
               try {

                   Response response = client.newCall(request).execute();
                   obj= response.body().string();

               }
               catch (Exception ex) {
                   Log.d("OlloNuts",ex.getMessage());
                   obj= ex.getMessage().toString();

               }
           } catch (Exception e) {
               this.exception = e;
               Log.d("OlloNuts",e.getMessage());
               obj= e.getMessage().toString();

           }
           return  obj;
       }

       protected void onPostExecute(String feed) {
            // TODO: check this.exception

                Intent dash = new Intent(mFilter);
                dash.putExtra(GlobalVariables.EXTRA_SERVER_RESPONSE,feed);
                mContext.sendBroadcast(dash);

            // TODO: do something with the feed
        }
    }



}
