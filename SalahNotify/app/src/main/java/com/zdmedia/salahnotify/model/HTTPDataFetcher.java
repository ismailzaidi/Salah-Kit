package com.zdmedia.salahnotify.model;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by JavaFreak on 14/06/2016.
 *
 *
 * API KEY: 66816c5d9d0b2ade921cc19f97d1ba94
 *
 * https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=AIzaSyBSyUv0SU0KcFeSsp1wN_DVE0RUEiRDTZ8
 */
public class HTTPDataFetcher extends AsyncTask<String,Void,String> {
    private HTTPEvent callback;
    private REQUEST_TYPE type;
    public enum REQUEST_TYPE {
        LOCATION, PRAYER_LONDON, PRAYER_GLOBAL
    }
    public interface HTTPEvent {
        void getHTTPResponse(REQUEST_TYPE type,String response);
    }

    public void setType(REQUEST_TYPE type){
        this.type = type;
    }

    public void setupCallback(HTTPEvent callback){
        this.callback=callback;
    }
    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(params[0]).build();
        String responseString = null;
        try {
            Response response = client.newCall(request).execute();
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }
    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        callback.getHTTPResponse(type,response);
    }
}
