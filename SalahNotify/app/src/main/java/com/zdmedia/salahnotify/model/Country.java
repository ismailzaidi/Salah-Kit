package com.zdmedia.salahnotify.model;

/**
 * Created by JavaFreak on 14/06/2016.
 */
public class Country {
    private String cityName,countryName, countryISOCode;



    private double latitude, longtitude;
    public Country(String cityName, String countryName, String countryISOCode, double latitude, double longtitude) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.countryISOCode = countryISOCode;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryISOCode() {
        return countryISOCode;
    }

    public void setCountryISOCode(String countryISOCode) {
        this.countryISOCode = countryISOCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public String toString() {
        return cityName +" ," + countryName +" ," + countryISOCode;
    }
}
