package rubicon.iky.com.ikyrocks;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import rubicon.iky.com.ikyrocks.Adapters.MeteorAdapter;
import rubicon.iky.com.ikyrocks.Adapters.OnItemClick;
import rubicon.iky.com.ikyrocks.Helpers.DbHelper;
import rubicon.iky.com.ikyrocks.Helpers.UpdateService;
import rubicon.iky.com.ikyrocks.Model.Meteor;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import xyz.danoz.recyclerviewfastscroller.sectionindicator.title.SectionTitleIndicator;
import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller;

public class ListActivity extends AppCompatActivity implements OnItemClick {

    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.toolbar2)
    Toolbar toolbar;

    @BindView(R.id.fast_scroller)
    VerticalRecyclerViewFastScroller scroller;


    SearchView searchView;
    ArrayList<Meteor> items = new ArrayList<Meteor>();
    ArrayList<Meteor> filtered_items = new ArrayList<Meteor>();

    MeteorAdapter adapter;
    @BindView(R.id.lbl_total_count)
    TextView lblTotalCount;
    @BindView(R.id.lbl_count_subtitle)
    TextView lblCountSubtitle;

    @BindView(R.id.lbl_last_update)
    TextView lblLastUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        items = new DbHelper(ListActivity.this).GetData();
        Integer s =items.size();
        filtered_items= new ArrayList<>();
        lblTotalCount.setText(s.toString());
        Collections.sort(items);
        Collections.reverse(items);
        adapter = new MeteorAdapter(ListActivity.this, this, items);
        list.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        list.setAdapter(adapter);
        //scroller.setSectionIndicator(sectionTitleIndicator);
        filtered_items=items;
        scroller.setRecyclerView(list);
        list.setOnScrollListener(scroller.getOnScrollListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        String last  = new DbHelper(getApplicationContext()).GetLastUpdateOccurence();
        if(last.equals(date)){
            lblLastUpdate.setText("Last Updated : Today");
        }
        else{
            lblLastUpdate.setText("Last Updated : " + last);

        }
    }

    void FilterItems(String query) {
        filtered_items = new ArrayList<Meteor>();
       for(int i=0;i<items.size();i++){
           Meteor m = items.get(i);
           if(m.name.toLowerCase().contains(query)){
               filtered_items.add(m);
           }
       }
       if(query!="") {
           Collections.sort(filtered_items);
           Collections.reverse(filtered_items);
           adapter.UpdateList(filtered_items);
       }
       else {
           Collections.sort(items);
           Collections.reverse(items);
           adapter.UpdateList(items);
       }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        //      SearchManager searchManager = (SearchManager) ListActivity.this.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
            //Filter Logic
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    //Change/Update  Adapter Values
                    FilterItems(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    FilterItems(newText);
                    return true;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view, int position) {
         //NB: I use the filtered list to determine which object to pass to the next screen
        Gson gson = new Gson();
 //       Toast.makeText(ListActivity.this,filtered_items.get(position).name,Toast.LENGTH_SHORT).show();
        Intent dash = new Intent(ListActivity.this,MapActivity.class);
        dash.putExtra("info",gson.toJson(filtered_items.get(position)));
        startActivity(dash);
        overridePendingTransition(R.anim.pull_in_right,R.anim.push_out_left);

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
