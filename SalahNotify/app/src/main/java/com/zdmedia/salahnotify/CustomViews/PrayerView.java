package com.zdmedia.salahnotify.CustomViews;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zdmedia.salahnotify.R;
import com.zdmedia.salahnotify.model.Prayer;

/**
 * Created by Ismail on 13/06/2016.
 */
public class PrayerView  extends CardView{
    private TextView arabicTxtView,englishTxtView, timeTxtView;
    public PrayerView(Context context){
        this(context,null);
    }
    public PrayerView(Context context, AttributeSet attr){
        this(context,attr,0);
    }
    public PrayerView(Context context, AttributeSet attr, int defStyle){
        super(context,attr,defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        arabicTxtView = (TextView) findViewById(R.id.arabicTextView);
        englishTxtView = (TextView) findViewById(R.id.englishTextView);
        timeTxtView = (TextView) findViewById(R.id.timeTextView);
    }

    public void updateItem(Prayer prayer){
        arabicTxtView.setText(prayer.getArabicPrayer());
        englishTxtView.setText(prayer.getEnglishPrayer());
        timeTxtView.setText(prayer.getPrayerTime());
    }
}
