package com.zdmedia.salahnotify.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zdmedia.salahnotify.CustomViews.PrayerView;
import com.zdmedia.salahnotify.R;
import com.zdmedia.salahnotify.model.Prayer;

import java.util.ArrayList;

public class RecyclerPrayerAdapter  extends RecyclerView.Adapter<RecyclerPrayerAdapter.PrayerHolder>{

    public ArrayList<Prayer> listOfPrayers;

    public RecyclerPrayerAdapter(ArrayList<Prayer> listOfPrayers) {
        this.listOfPrayers = listOfPrayers;
    }

    @Override
    public PrayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PrayerView prayerItem = (PrayerView) LayoutInflater.from(parent.getContext()).inflate(R.layout.prayer_item,null);
        PrayerHolder prayerHolder = new PrayerHolder(prayerItem);
        return prayerHolder;
    }

    @Override
    public void onBindViewHolder(PrayerHolder holder, int position) {
        Prayer prayer = listOfPrayers.get(position);
        holder.prayerView.updateItem(prayer);
    }

    @Override
    public int getItemCount() {
        return listOfPrayers.size();
    }

    public class PrayerHolder extends RecyclerView.ViewHolder{
        private PrayerView prayerView;
        public PrayerHolder(View view){
            super(view);
            prayerView = (PrayerView) view;
        }
    }
}
