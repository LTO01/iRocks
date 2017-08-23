package rubicon.iky.com.ikyrocks;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.comix.overwatch.HiveProgressView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rubicon.iky.com.ikyrocks.Helpers.API;
import rubicon.iky.com.ikyrocks.Helpers.Alarm;
import rubicon.iky.com.ikyrocks.Helpers.DbHelper;
import rubicon.iky.com.ikyrocks.Helpers.PreferenceManager;
import rubicon.iky.com.ikyrocks.Helpers.UpdateService;
import rubicon.iky.com.ikyrocks.Model.Sighting;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AppInitializer extends AppCompatActivity {

    @BindView(R.id.lbStatus)
    TextView lbStatus;
    @BindView(R.id.prg)
    HiveProgressView prg;

    boolean isListerRegistered=false;
    GetDataReceiver receiver;

    Alarm alarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_initializer);
        ButterKnife.bind(this);
        prg.setVisibility(View.VISIBLE);
        receiver = new GetDataReceiver();
        IntentFilter filter  = new IntentFilter();
        filter.addAction(GlobalVariables.ACTION_FECTH_DATA);
        registerReceiver(receiver,filter);
        isListerRegistered=true;

        API.FetchData(AppInitializer.this);

    }

    public  void LogUpdate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        new DbHelper(AppInitializer.this).StoreLastUpdateOccurence(date);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    class GetDataReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String jsonResponse = intent.getStringExtra(GlobalVariables.EXTRA_SERVER_RESPONSE);
            try {
        //Check Timeout
                if(jsonResponse.toLowerCase().contains("timeout")){

                    lbStatus.setText("Network Error: Please try again");
                    return;
                }
                else  if(jsonResponse.contains("Unable to resolve host")){
                    lbStatus.setText("Network Error: Please try again");
                    return;
                }

                Log.d("RESPONSE_MESSAGE",jsonResponse);
                lbStatus.setText("Stars aligned successfully.... please wait");
                Type objType = new TypeToken<List<Sighting>>() {
                }.getType();
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                List<Sighting> data=  gson.fromJson(jsonResponse.toString(), objType);

                new DbHelper(AppInitializer.this).SaveServices(data);
                if(isListerRegistered){
                    unregisterReceiver(receiver);
                    isListerRegistered=false;
                }

                new PreferenceManager(AppInitializer.this).SetIsInitialized(true);
                LogUpdate();
                AppInitializer.this.startService(new Intent(getApplicationContext(),UpdateService.class));

                AppInitializer.this.setResult(RESULT_OK);
                AppInitializer.this.finish();
            } catch (Exception e) {
                Log.d("STUPIDERROR", e.getMessage() + e.getStackTrace());
            }
        }
    }
}
