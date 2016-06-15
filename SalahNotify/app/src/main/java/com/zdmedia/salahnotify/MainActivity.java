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
import com.zdmedia.salahnotify.adapters.RecyclerPrayerAdapter;
import com.zdmedia.salahnotify.model.Country;
import com.zdmedia.salahnotify.model.HTTPHandler;
import com.zdmedia.salahnotify.model.Prayer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LocationListener, HTTPHandler.CountryFetcher, HTTPHandler.PrayerFetcher {
    public static final String GPS_CONFIG = "GPS_CONFIG";
    private LocationManager mManger;
    private static final String[] GPS_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private int GPS_REQUEST_CODE = 1234;
    private HTTPHandler handler;
    private RecyclerView prayerRecyclerView;
    private ImageView settings_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prayerRecyclerView = (RecyclerView) findViewById(R.id.prayerRecyclerView);
        prayerRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        prayerRecyclerView.setHasFixedSize(true);
        prayerRecyclerView.addItemDecoration(new DividerDecoration(5));
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
        setupGPSPermissions();
    }

    private void setupGPSPermissions() {
        boolean isEnabled = mManger.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isEnabled) {
            boolean ACCESS_LOC = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED;
            boolean COARSE_LOC = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
            Log.v(GPS_CONFIG, "Access Fine Location Permission: " + ACCESS_LOC + " Access Coarse Location " + COARSE_LOC);
            if (ACCESS_LOC && COARSE_LOC) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                    ActivityCompat.requestPermissions(this, MainActivity.GPS_PERMS, GPS_REQUEST_CODE);

                } else {
                    ActivityCompat.requestPermissions(this, MainActivity.GPS_PERMS, GPS_REQUEST_CODE);
                }
                return;
            } else {
                Location location = mManger.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    mManger.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    location = mManger.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
                Log.v(GPS_CONFIG, " Location: " + location);
                double longtitude = location.getLongitude();
                double latitude = location.getLatitude();
                Log.v(GPS_CONFIG, "Longtitude: " + longtitude + " Latitude: " + latitude);
                getLocationString(latitude, longtitude);
            }
        } else {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    public void getLocationString(double latitude, double longtitude) {
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
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void getUserCountry(Country country) {
        Toast.makeText(this, country.getCountryName(), Toast.LENGTH_SHORT).show();
        handler.prayerHandler(country);
        handler.setupPrayerCallback(this);

    }

    @Override
    public void getPrayerList(ArrayList<Prayer> listOfPrayers) {
        RecyclerPrayerAdapter adapter = new RecyclerPrayerAdapter(listOfPrayers);
        prayerRecyclerView.setAdapter(adapter);
        Toast.makeText(this, "How Many Prayers are there: " + listOfPrayers.size(), Toast.LENGTH_SHORT).show();
    }
}
