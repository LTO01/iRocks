package rubicon.iky.com.ikyrocks;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.firetrap.verso.Verso;

import rubicon.iky.com.ikyrocks.Helpers.PreferenceManager;
import rubicon.iky.com.ikyrocks.Helpers.UpdateService;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by obake on 7/26/2017.
 */
public class GlobalApplication extends Application{
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Verso.getInstance().setup(this); //TODO: Offline geo-coding for more relevant info on the MAP Activity
          CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/RobotoLight.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        PreferenceManager pref = new PreferenceManager(getApplicationContext());

    }
}
