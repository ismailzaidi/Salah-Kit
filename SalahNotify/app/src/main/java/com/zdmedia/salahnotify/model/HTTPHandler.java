package com.zdmedia.salahnotify.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JavaFreak on 14/06/2016.
 */
public class HTTPHandler implements HTTPDataFetcher.HTTPEvent {
    private Context context;
    private CountryFetcher countryCallback;
    private PrayerFetcher prayerCallback;
    private static final String DEBUG_JSON = "JSON_DEBUG";
    public interface CountryFetcher{
        void getUserCountry(Country country);

    }
    public interface PrayerFetcher{
        void getPrayerList(ArrayList<Prayer> listOfPrayers);
    }
    public HTTPHandler(Context context) {
        this.context = context;
    }

    //Country Confic
    public void setupCountryCallback(CountryFetcher countryCallback){
        this.countryCallback = countryCallback;
    }
    public void countryHandler(double latitude, double longtitude) {
        String googleURL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longtitude + "&key=" + Config.GOOGLE_MAP_API;
        HTTPDataFetcher httpDataFetcher = new HTTPDataFetcher();
        httpDataFetcher.setupCallback(this);
        httpDataFetcher.setType(HTTPDataFetcher.REQUEST_TYPE.LOCATION);
        httpDataFetcher.execute(googleURL);
    }
    public void setupPrayerCallback(PrayerFetcher prayerCallback){
        this.prayerCallback=prayerCallback;
    }
    public void prayerHandler(Country country){
        String countryParam = country.getCityName()+","+country.getCountryISOCode();
        String globalURL = "http://muslimsalat.com/"+countryParam+"/daily/true/.json?key="+Config.MUSLIM_LEAGUE_API;
        HTTPDataFetcher httpDataFetcher = new HTTPDataFetcher();
        httpDataFetcher.setupCallback(this);
        httpDataFetcher.setType(HTTPDataFetcher.REQUEST_TYPE.PRAYER_GLOBAL);
        httpDataFetcher.execute(globalURL);
    }

    @Override
    public void getHTTPResponse(HTTPDataFetcher.REQUEST_TYPE type, String response) {
        Log.v(DEBUG_JSON, "JSON Size: " + response.length() + " Location Type: " + type.name());
        JSONHandler jsonHandler = new JSONHandler(context);
        if (response != null) {
            switch (type) {
                case LOCATION:
                    Country country = jsonHandler.getCountry(response);
                    countryCallback.getUserCountry(country);
                    break;
                case PRAYER_LONDON:
                    break;
                case PRAYER_GLOBAL:
                    ArrayList<Prayer> listOfPrayers = jsonHandler.getPrayerList(response);
                    prayerCallback.getPrayerList(listOfPrayers);
                    break;
            }
        }
    }
}
