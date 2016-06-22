package com.zdmedia.salahnotify.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.zdmedia.salahnotify.CustomViews.RecitorView;
import com.zdmedia.salahnotify.R;
import com.zdmedia.salahnotify.model.AudioHandler;
import com.zdmedia.salahnotify.model.Recitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecitorAdapter extends RecyclerView.Adapter<RecitorAdapter.PrayerHolder> {

    public ArrayList<Recitor> listOfPrayers;

    public RecitorAdapter(ArrayList<Recitor> listOfPrayers) {
        this.listOfPrayers = listOfPrayers;
    }

    @Override
    public PrayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecitorView prayerItem = (RecitorView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recitor_item, null);
        PrayerHolder prayerHolder = new PrayerHolder(prayerItem, parent.getContext());
        return prayerHolder;
    }

    @Override
    public void onBindViewHolder(PrayerHolder holder, int position) {
        Recitor prayer = listOfPrayers.get(position);
        holder.recitorView.updateRecitorItem(prayer);
    }

    @Override
    public int getItemCount() {
        return listOfPrayers.size();
    }

    public class PrayerHolder extends RecyclerView.ViewHolder {
        private RecitorView recitorView;
        private ImageView audioButton;
        private ImageView defaultButton;
        private Context context;
        private Recitor recitor;

        public PrayerHolder(View view, Context context) {
            super(view);
            this.context = context;
            recitorView = (RecitorView) view;
            audioButton = recitorView.getPlayAudioRecitor();
            defaultButton = recitorView.getSelectRecitorButton();
            audioButton.setOnClickListener(new EventListener());
            defaultButton.setOnClickListener(new EventListener());

        }

        public class EventListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                recitor = listOfPrayers.get(getLayoutPosition());
                if (id == audioButton.getId()) {
                    Integer img_resource = (Integer) audioButton.getTag();
                    AudioHandler handler = new AudioHandler(context,recitor.getRecitorAudioURL());
                    switch (img_resource) {
                        case R.drawable.icon_play_blue:
                            audioButton.setImageResource(R.drawable.icon_pause_blue);
                            audioButton.setTag(R.drawable.icon_pause_blue);
                            handler.generateSound("PLAY");
                            break;
                        case R.drawable.icon_pause_blue:
                            audioButton.setImageResource(R.drawable.icon_play_blue);
                            audioButton.setTag(R.drawable.icon_play_blue);
                            handler.generateSound("PAUSE");
                            break;
                    }

                }
                if (id == defaultButton.getId()) {
                    Toast.makeText(context, "Audio Play Button Clicked", Toast.LENGTH_SHORT).show();
                    if(recitor.isDefault()){
                        Toast.makeText(context, "Already default", Toast.LENGTH_SHORT).show();
                    }else{
                        executeDefault(recitor);
                    }

                }
            }

            private void executeDefault(Recitor recitor){
                recitor.setIsDefault(true);
                int itemCount = 0;
                for(Recitor recitorItem: listOfPrayers){
                    if(recitorItem.equals(recitor)){
                        recitorItem.setIsDefault(true);
                        listOfPrayers.set(itemCount,recitorItem);
                    }else{
                        recitorItem.setIsDefault(false);
                        listOfPrayers.set(itemCount,recitorItem);
                    }
                    itemCount++;
                }
                notifyDataSetChanged();
            }
        }
    }
}
