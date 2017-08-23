package rubicon.iky.com.ikyrocks.Helpers;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;

import rubicon.iky.com.ikyrocks.GetDataReceiverMain;
import rubicon.iky.com.ikyrocks.GlobalVariables;

public class UpdateService extends Service {
    public UpdateService() {
    }
    Alarm alarm = new Alarm();
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        alarm.setAlarm(this);
        IntentFilter filter  = new IntentFilter();
        filter.addAction(GlobalVariables.ACTION_FECTH_DATA);
        this.registerReceiver(new GetDataReceiverMain(),filter);
         return START_STICKY;
    }
    private final static int INTERVAL = 1000 * 60 * 2; //2 minutes
    Handler mHandler = new Handler();

    Runnable mHandlerTask = new Runnable()
    {
        @Override
        public void run() {
            API.FetchData(getApplicationContext());
            mHandler.postDelayed(mHandlerTask, INTERVAL);
        }
    };

    void startRepeatingTask()
    {
        mHandlerTask.run();
    }

    void stopRepeatingTask()
    {
        mHandler.removeCallbacks(mHandlerTask);
    }
    @Override
    public void onStart(Intent intent, int startId)
    {
        alarm.setAlarm(this);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
