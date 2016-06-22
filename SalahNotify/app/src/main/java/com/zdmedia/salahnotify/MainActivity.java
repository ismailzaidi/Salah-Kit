package com.zdmedia.salahnotify;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.zdmedia.salahnotify.CustomViews.DividerDecoration;
import com.zdmedia.salahnotify.Fragments.PrayerFragment;
import com.zdmedia.salahnotify.Fragments.RecitorFragment;
import com.zdmedia.salahnotify.adapters.RecyclerPrayerAdapter;
import com.zdmedia.salahnotify.model.Country;
import com.zdmedia.salahnotify.model.HTTPHandler;
import com.zdmedia.salahnotify.model.Prayer;
import com.zdmedia.salahnotify.model.Utility;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  HTTPHandler.CountryFetcher, HTTPHandler.PrayerFetcher {
    public static final String GPS_CONFIG = "GPS_CONFIG";
    private LocationManager mManger;
    private static final String[] GPS_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private int GPS_REQUEST_CODE = 1234;
    private HTTPHandler handler;
    private ImageView settings_icon;
    private ArrayList<Prayer> listOfPrayers;
    private Utility utility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        settings_icon = (ImageView) findViewById(R.id.settings_icon);
        settings_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsPreferenceActivity.class);
                startActivity(intent);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mManger = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        utility = new Utility(this);
        setupGPSPermissions();
        displayFragment(0);
    }

    private void displayFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = PrayerFragment.InstanceOf(listOfPrayers);
                break;
            case 1:
                fragment = RecitorFragment.InstanceOf();
                break;
        }
        if(fragment !=null){
            fragmentManager.beginTransaction().replace(R.id.frame_content, fragment,fragment.getTag()).commit();
        }

    }
    private void setupGPSPermissions() {
        utility.setActivityForPermissions(this);
        double latitude = utility.getLatitude();
        double longtitude = utility.getLongtitude();
        fetchUserLocation(latitude,longtitude);
    }
    public void fetchUserLocation(double latitude, double longtitude) {
        handler = new HTTPHandler(this);
        handler.countryHandler(latitude, longtitude);
        handler.setupCountryCallback(this);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.v(GPS_CONFIG, "Request Code: " + requestCode);
        if (requestCode == GPS_REQUEST_CODE) {
            Log.v(GPS_CONFIG, "Permission Status: " + grantResults[0]);
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.v(GPS_CONFIG, "Permission: Accepted " + grantResults[0]);

            }
        }
    }

    @Override
    public void getUserCountry(Country country) {
        Toast.makeText(this, country.getCountryName(), Toast.LENGTH_SHORT).show();
        handler.prayerHandler(country);
        handler.setupPrayerCallback(this);
    }

    @Override
    public void getPrayerList(ArrayList<Prayer> listOfPrayers) {
        this.listOfPrayers = listOfPrayers;
        Toast.makeText(this, "How Many Prayers are there: " + listOfPrayers.size(), Toast.LENGTH_SHORT).show();
    }
}
