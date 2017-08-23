package rubicon.iky.com.ikyrocks;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import rubicon.iky.com.ikyrocks.Helpers.DbHelper;
import rubicon.iky.com.ikyrocks.Helpers.PreferenceManager;

public class Splash extends AppCompatActivity {
    private static final int INTENT_INIT = 128;
    private static final int INTENT_MAIN = 875;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbHelper db = new DbHelper(Splash.this);
        PreferenceManager pref = new PreferenceManager(Splash.this);
        boolean hasInit = pref.IsInitialized();
        Intent dash;
        if(hasInit){
            //proceed to main activity
            dash = new Intent(Splash.this,ListActivity.class);
            startActivity(dash);
        }
        else{
            //start app initializer (for result)
            dash = new Intent(Splash.this,AppInitializer.class);
            startActivityForResult(dash,INTENT_INIT);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== RESULT_CANCELED){
            this.finish();
        }
        else{
            if(requestCode==INTENT_INIT){
                Intent dash;
                dash = new Intent(Splash.this,ListActivity.class);
                startActivityForResult(dash,INTENT_MAIN);
            }
            if(requestCode==INTENT_MAIN){
                this.finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
