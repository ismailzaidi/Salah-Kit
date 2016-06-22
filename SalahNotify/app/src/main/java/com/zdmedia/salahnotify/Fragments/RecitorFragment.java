package com.zdmedia.salahnotify.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zdmedia.salahnotify.R;
import com.zdmedia.salahnotify.adapters.RecitorAdapter;
import com.zdmedia.salahnotify.model.Recitor;

import java.util.ArrayList;

/**
 * Created by JavaFreak on 18/06/2016.
 */
public class RecitorFragment  extends Fragment{
    private Context context;
    public static RecitorFragment InstanceOf(){
        RecitorFragment fragment = new RecitorFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(context, R.layout.default_recycle_view,null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        RecitorAdapter adapter = new RecitorAdapter(getRecitorList());
        recyclerView.setAdapter(adapter);
        return view;
    }
    public ArrayList<Recitor> getRecitorList(){
        ArrayList<Recitor> listOfRecitors = new ArrayList<Recitor>();
        Resources resources = context.getResources();
        listOfRecitors.add(new Recitor("https://i1.sndcdn.com/artworks-000043021579-pvkcde-t500x500.jpg","Al Afasy", "http://download.tvquran.com/download/TvQuran.com__Athan/TvQuran.com__04.athan.mp3", true));
        listOfRecitors.add(new Recitor("https://i1.sndcdn.com/artworks-000043021579-pvkcde-t500x500.jpg","Al Afasy", "http://download.tvquran.com/download/TvQuran.com__Athan/TvQuran.com__04.athan.mp3", false));
        return listOfRecitors;
    }
}
