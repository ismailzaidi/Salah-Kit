package com.zdmedia.salahnotify.model;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;

import com.zdmedia.salahnotify.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JavaFreak on 14/06/2016.
 */
public class JSONHandler {
    private Context context;
    private static final String DEBUG_JSON = "JSON_DEBUG";



    public JSONHandler(Context context) {
        this.context = context;
    }
    public ArrayList<Prayer> getPrayerList(String json) {
        Resources resources = context.getResources();
        ArrayList<Prayer> listOfPrayers = new ArrayList<Prayer>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultsArray = jsonObject.getJSONArray("items");
            JSONObject firstItem = resultsArray.getJSONObject(0);
            listOfPrayers.add(new Prayer(resources.getString(R.string.en_prayer_1),resources.getString(R.string.ar_prayer_1),firstItem.getString("fajr")));
            listOfPrayers.add(new Prayer(resources.getString(R.string.en_prayer_2),resources.getString(R.string.ar_prayer_2),firstItem.getString("dhuhr")));
            listOfPrayers.add(new Prayer(resources.getString(R.string.en_prayer_3),resources.getString(R.string.ar_prayer_3),firstItem.getString("asr")));
            listOfPrayers.add(new Prayer(resources.getString(R.string.en_prayer_4),resources.getString(R.string.ar_prayer_4),firstItem.getString("maghrib")));
            listOfPrayers.add(new Prayer(resources.getString(R.string.en_prayer_5),resources.getString(R.string.ar_prayer_5),firstItem.getString("isha")));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return listOfPrayers;
    }
    public Country getCountry(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultsArray = jsonObject.getJSONArray("results");
            JSONObject firstItem = resultsArray.getJSONObject(0);
            JSONArray addressArray = firstItem.getJSONArray("address_components");
            int length = addressArray.length();
            String cityName = "";
            String countryName = "";
            String isoCode = "";
            if (length > 4) {
                JSONObject cityObject = addressArray.getJSONObject(length - 4);
                cityName = cityObject.getString("long_name");
                JSONObject countryObject = addressArray.getJSONObject(length - 3);
                countryName = countryObject.getString("long_name");
                JSONObject isoObject = addressArray.getJSONObject(length - 2);
                isoCode = isoObject.getString("long_name");

                Log.v(DEBUG_JSON, "City Name: " + cityName + " Country Name: " + countryName + " ISO Code: " + isoCode);
            }
            JSONObject geometry = firstItem.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            double latitude = location.getDouble("lat");
            double longtitude = location.getDouble("lng");
            return new Country(cityName, countryName, isoCode, latitude, longtitude);
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
