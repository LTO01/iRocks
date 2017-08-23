package rubicon.iky.com.ikyrocks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rubicon.iky.com.ikyrocks.Helpers.DbHelper;
import rubicon.iky.com.ikyrocks.Model.Meteor;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    @BindView(R.id.lblGeoLocation)
    TextView lblGeoLocation;
    @BindView(R.id.lblYear)
    TextView lblYear;
    @BindView(R.id.lblName)
    TextView lblName;
    @BindView(R.id.btnBack)
    ImageButton btnBack;
    @BindView(R.id.btnNext)
    ImageButton btnNext;
    @BindView(R.id.btnPrev)
    ImageButton btnPrev;
    private GoogleMap mMap;

    private MarkerOptions marker;
    private Marker mMarker;

    int index = 0;
    ArrayList<Meteor> data = new ArrayList<Meteor>();
    ArrayList<Marker> mCraters = new ArrayList<Marker>();
    Meteor selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Gson gson = new Gson();
        Type objType = new TypeToken<Meteor>() {
        }.getType();
        String ob = getIntent().getStringExtra("info");
        if (ob != null) {
            selected = gson.fromJson(ob, objType);
            LatLng pos = new LatLng(selected.lat, selected.lng);

            lblName.setText(selected.name.toUpperCase());
            lblYear.setText(selected.year.toString());
            lblGeoLocation.setText("iRock");


            Double m = Double.parseDouble(selected.mass) / 1000;

            //set the default marker variables
            int height = 100;
            int width = 100;
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.marker_flag_left_map);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            marker = new MarkerOptions();
            marker.position(pos)
                    .title(selected.classname + " : " + m + "kg")
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            lblGeoLocation.setText("Class : "+selected.classname + "  | Mass : " + m + "kg");

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMaxZoomPreference(19f);
        mMap.setMinZoomPreference(4f);
        if (selected != null) {
        mMarker=    mMap.addMarker(marker);
            mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()), 9, null);
        }
        data = new DbHelper(MapActivity.this).GetData();
        int height = 11;
        int width = 20;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.crater_ic);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        //CRATER POSITIONS
        mCraters =new ArrayList<Marker>();
        for(int i = 0; i < data.size(); i++) {
            Meteor x = data.get(i);
         Marker mm=  mMap.addMarker(new MarkerOptions().position(new LatLng(x.lat,x.lng)).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        mm.setTag(String.valueOf(x.id));
        mCraters.add(mm);
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker _marker) {
                 mMarker.setPosition(_marker.getPosition());
                mMap.animateCamera(CameraUpdateFactory.newLatLng(_marker.getPosition()));
              try {


                for(int i=0;i<data.size();i++){
                    Meteor g = data.get(i);
                    String s=String.valueOf(_marker.getTag());
                    String d= String.valueOf(g.id);
                    index=i;
                    if(s.trim().equals(d.trim())){
                        LoadNew(g);

                    }
                }
              }catch (Exception e){
                  Log.d("",e.getMessage());
              }
                return true;
            }
        });
        //DEFAULT MARKER
        for (int i = 0; i < data.size(); i++) {
            Meteor x = data.get(i);
            if (x.id == selected.id) {
                Double d = Double.parseDouble(selected.mass) / 1000;

                lblGeoLocation.setText("Class : "+selected.classname + "  | Mass : " + d + "kg");

                index = i;
                break;
            }
        }

    }

    @OnClick(R.id.btnBack)
    public void onViewClicked() {
        this.setResult(RESULT_OK);
        this.finish();
    }

    void LoadNew(Meteor m) {
        Double d = Double.parseDouble(m.mass) / 1000;
        lblName.setText(m.name.toUpperCase());
        lblYear.setText(m.year.toString());

       mMarker.setTitle(selected.classname + " : " + d + "kg");
        mMarker.setPosition(new LatLng(m.lat, m.lng));
lblGeoLocation.setText("Class : "+m.classname + "  | Mass : " + d + "kg");
        mMap.animateCamera(CameraUpdateFactory.newLatLng(mMarker.getPosition()));

    }

    void LoadNext() {
        if (index < data.size()) {
            index += 1;
            Meteor m = data.get(index);
            LoadNew(m);
        } else {
            Meteor m = data.get(data.size());
            LoadNew(m);
        }
    }

    void LoadPrev() {
        if (index > 0) {
            index -= 1;
            Meteor m = data.get(index);
            LoadNew(m);
        } else {
            Meteor m = data.get(0);
            LoadNew(m);
        }
    }


    @OnClick({R.id.btnNext, R.id.btnPrev})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                LoadNext();
                break;
            case R.id.btnPrev:
                LoadPrev();
                break;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
