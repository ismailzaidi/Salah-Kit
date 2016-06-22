package com.zdmedia.salahnotify.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zdmedia.salahnotify.CustomViews.DividerDecoration;
import com.zdmedia.salahnotify.R;
import com.zdmedia.salahnotify.adapters.RecitorAdapter;
import com.zdmedia.salahnotify.adapters.RecyclerPrayerAdapter;
import com.zdmedia.salahnotify.model.Prayer;
import com.zdmedia.salahnotify.model.Recitor;

import java.util.ArrayList;

/**
 * Created by JavaFreak on 18/06/2016.
 */
public class PrayerFragment extends Fragment {
    public static final String PRAYER_KEY = "com.zdmedia.prayerlist";
    private RecyclerView prayerRecyclerView;
    public static PrayerFragment InstanceOf(ArrayList<Prayer> listOfPrayers){
        PrayerFragment fragment = new PrayerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PRAYER_KEY, listOfPrayers);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity().getApplicationContext(), R.layout.prayer_fragment,null);
        prayerRecyclerView = (RecyclerView) view.findViewById(R.id.prayerRecyclerView);
        prayerRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        prayerRecyclerView.setHasFixedSize(true);
        prayerRecyclerView.addItemDecoration(new DividerDecoration(5));
        ArrayList<Prayer> listOfPrayers = (ArrayList<Prayer>) getArguments().getSerializable(PRAYER_KEY);
        RecyclerPrayerAdapter adapter = new RecyclerPrayerAdapter(listOfPrayers);
        prayerRecyclerView.setAdapter(adapter);
        return view;
    }
}
