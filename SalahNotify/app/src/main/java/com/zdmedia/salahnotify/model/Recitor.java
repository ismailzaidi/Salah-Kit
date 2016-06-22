package com.zdmedia.salahnotify.model;

/**
 * Created by JavaFreak on 18/06/2016.
 */
public class Recitor {
    private String reciter_image;
    private String recitorName,recitorAudioURL;
    private boolean isDefault;
    public Recitor(String reciter_image, String recitorName, String recitorAudioURL, boolean isDefault) {
        this.reciter_image = reciter_image;
        this.recitorName = recitorName;
        this.recitorAudioURL = recitorAudioURL;
        this.isDefault = isDefault;
    }
    public String getReciter_image() {
        return reciter_image;
    }

    public void setReciter_image(String reciter_image) {
        this.reciter_image = reciter_image;
    }

    public String getRecitorName() {
        return recitorName;
    }

    public void setRecitorName(String recitorName) {
        this.recitorName = recitorName;
    }

    public String getRecitorAudioURL() {
        return recitorAudioURL;
    }

    public void setRecitorAudioURL(String recitorAudioURL) {
        this.recitorAudioURL = recitorAudioURL;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }


}
