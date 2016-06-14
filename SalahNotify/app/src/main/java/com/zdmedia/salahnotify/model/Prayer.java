package com.zdmedia.salahnotify.model;

/**
 * Created by JavaFreak on 14/06/2016.
 */
public class Prayer{
    private String arabicPrayer,englishPrayer, prayerTime;
    public Prayer(String arabicPrayer, String englishPrayer, String prayerTime) {
        this.arabicPrayer = arabicPrayer;
        this.englishPrayer = englishPrayer;
        this.prayerTime = prayerTime;
    }

    public String getArabicPrayer() {
        return arabicPrayer;
    }

    public void setArabicPrayer(String arabicPrayer) {
        this.arabicPrayer = arabicPrayer;
    }

    public String getEnglishPrayer() {
        return englishPrayer;
    }

    public void setEnglishPrayer(String englishPrayer) {
        this.englishPrayer = englishPrayer;
    }

    public String getPrayerTime() {
        return prayerTime;
    }

    public void setPrayerTime(String prayerTime) {
        this.prayerTime = prayerTime;
    }
}
