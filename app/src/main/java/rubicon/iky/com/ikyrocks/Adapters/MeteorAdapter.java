package rubicon.iky.com.ikyrocks.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import rubicon.iky.com.ikyrocks.Helpers.DbHelper;
import  rubicon.iky.com.ikyrocks.R;
import java.util.ArrayList;

import rubicon.iky.com.ikyrocks.Model.Meteor;
import rubicon.iky.com.ikyrocks.UI.ColorDataSet;
import rubicon.iky.com.ikyrocks.UI.ColorGroup;

/**
 * Created by obake on 7/21/2017.
 */

public class MeteorAdapter extends RecyclerView.Adapter<MeteorAdapter.mViewHolder> implements SectionIndexer {
    private Context mContext;
    private ArrayList<Meteor> mItems;
    private int lastPosition = -1;
   OnItemClick mListner;
ColorDataSet mDataSet;
    public MeteorAdapter(Context c, ArrayList<Meteor> data){
        super();
        mItems =data;
        mContext=c;
        mDataSet =new ColorDataSet();
    }
    public MeteorAdapter(Context c, OnItemClick lister,ArrayList<Meteor> data){
        super();
        mItems =  data;
        mContext=c;
        mListner=lister;
    }
    public void SetListner(OnItemClick lister){
        this.mListner = lister;
    }

    @Override
    public Object[] getSections() {

        return  ColorGroup.values();
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    public class  mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         Meteor item;
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
         public TextView subtitleTextView;

         public TextView yearView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public mViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
             nameTextView = (TextView) itemView.findViewById(R.id.title);
            subtitleTextView= (TextView) itemView.findViewById(R.id.mass);
            yearView= (TextView) itemView.findViewById(R.id.year);
            itemView.setOnClickListener(this);
         }


         @Override
         public void onClick(View v) {
             if (mListner != null) mListner.onClick(v, getAdapterPosition());
         }
     }



    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_item, parent, false);
          // Return a new holder instance
        mViewHolder viewHolder = new mViewHolder(contactView);

        return viewHolder;
    }

    public void UpdateList(ArrayList<Meteor> m){
        mItems=m;
        this.notifyDataSetChanged();

    }
    @Override
    public void onBindViewHolder(mViewHolder holder, final int position) {
        Meteor item = mItems.get(position);
        //SET DATA TO VIEWHOLDER
        holder.nameTextView.setText(item.name.toUpperCase());
        holder.yearView.setText(item.year.toString());
        Double d= Double.parseDouble(item.mass)/1000;
        holder.subtitleTextView.setText(String.format("%.2f",d) +"kg");
        setAnimation(holder.itemView,position);
    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {

            Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.pull_in_right);
            //  Animation fadeOut = new AlphaAnimation(0, 1);
            //   fadeOut.Interpolator = new AccelerateInterpolator();
            // fadeOut.Duration = 1000;

            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
    public int getItemCount() {
        return mItems.size();
    }
}
