package rubicon.iky.com.ikyrocks;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rubicon.iky.com.ikyrocks.Helpers.DbHelper;
import rubicon.iky.com.ikyrocks.Helpers.PreferenceManager;
import rubicon.iky.com.ikyrocks.Helpers.UpdateService;
import rubicon.iky.com.ikyrocks.Model.Sighting;

public class GetDataReceiverMain extends BroadcastReceiver {

    public  void LogUpdate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        new DbHelper(mContext).StoreLastUpdateOccurence(date);

    }
    Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        mContext=context;
        String jsonResponse = intent.getStringExtra(GlobalVariables.EXTRA_SERVER_RESPONSE);
        try {
            //Check Timeout
            if(jsonResponse.toLowerCase().contains("timeout")){
                    ShowNotification(context,"Update Failed","The operation timed out... ");
                  return;
            }

             Type objType = new TypeToken<List<Sighting>>() {
            }.getType();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            List<Sighting> data=  gson.fromJson(jsonResponse.toString(), objType);
            ArrayList current = new DbHelper(context).GetData();
            if(data.size()>current.size()) {
                new DbHelper(context).SaveServices(data);
                ShowNotification(context, "New Meteors Discovered!","Check out what NASA discovered...");
                LogUpdate();
            }

        } catch (Exception e) {
            Log.d("STUPIDERROR", e.getMessage() + e.getStackTrace());
        }
    }

    public  void ShowNotification(Context ctx, String title,String message){
        Intent intent = new Intent(ctx, ListActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(ctx);
        BitmapDrawable bitmapdraw = (BitmapDrawable) ctx.getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap x = bitmapdraw.getBitmap();
        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_stat_solar_system)
                .setTicker("iRock by Iky")
                .setContentTitle(title)
                .setContentText(message).setLargeIcon(x)

                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setContentIntent(contentIntent)
                .setContentInfo("Info");


        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());
    }
}
