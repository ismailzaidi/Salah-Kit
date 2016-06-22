package com.zdmedia.salahnotify.model;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.zdmedia.salahnotify.MainActivity;

/**
 * Created by JavaFreak on 18/06/2016.
 */
public class Utility extends Application implements LocationListener{
    private static final double LONDON_LATITUDE = 51.49680;
    private static final double LONDON_LONGTITUDE = -0.14611;
    private LocationManager  mManger;
    private static final String[] GPS_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private int GPS_REQUEST_CODE = 1234;
    private double latitude = 0, longtitude = 0;
    private Context context;
    private Activity activity;
    public Utility(Context context) {
        this.context=context;
    }
    public void setActivityForPermissions(Activity activity){
        this.activity = activity;
    }
    public void processData(){
        mManger =  (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(isGPSEnabled()){
            boolean ACCESS_LOC = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED;
            boolean COARSE_LOC = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
            Log.v(MainActivity.GPS_CONFIG, "Access Fine Location Permission: " + ACCESS_LOC + " Access Coarse Location " + COARSE_LOC);
            if (ACCESS_LOC && COARSE_LOC) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    ActivityCompat.requestPermissions(activity, GPS_PERMS, GPS_REQUEST_CODE);
                } else {
                    ActivityCompat.requestPermissions(activity, GPS_PERMS, GPS_REQUEST_CODE);
                }
                return;
            } else {
                Location location = mManger.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    mManger.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    location = mManger.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
                Log.v(MainActivity.GPS_CONFIG, " Location: " + location);
                if(location!=null){
                    longtitude = location.getLongitude();
                    latitude = location.getLatitude();
                    Log.v(MainActivity.GPS_CONFIG, "Longtitude: " + longtitude + " Latitude: " + latitude);
                }
            }
        }else{
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

    }
    public boolean isGPSEnabled(){
        return mManger.isProviderEnabled(LocationManager.GPS_PROVIDER);
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
    public double getLatitude() {
        double latChecker = (latitude==0)?LONDON_LATITUDE:latitude;
        return latChecker;
    }
    public double getLongtitude() {
        double latChecker = (longtitude==0)?LONDON_LONGTITUDE:longtitude;
        return latChecker;
    }

}
