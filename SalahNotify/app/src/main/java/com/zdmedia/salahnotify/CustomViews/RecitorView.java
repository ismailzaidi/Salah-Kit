package com.zdmedia.salahnotify.CustomViews;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zdmedia.salahnotify.R;
import com.zdmedia.salahnotify.model.Prayer;
import com.zdmedia.salahnotify.model.Recitor;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ismail on 13/06/2016.
 */
public class RecitorView extends CardView {
    private CircleImageView recitorImage;
    private TextView recitorName;
    private ImageView playAudioRecitor,selectRecitorButton;
    public RecitorView(Context context) {
        this(context, null);
    }
    public RecitorView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }
    public RecitorView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recitorImage = (CircleImageView) findViewById(R.id.recitorImage);
        recitorName = (TextView) findViewById(R.id.recitorName);
        playAudioRecitor = (ImageView)findViewById(R.id.playAudioRecitor);
        selectRecitorButton = (ImageView) findViewById(R.id.selectRecitorButton);

        playAudioRecitor.setTag(R.drawable.icon_play_blue);
    }
    public void updateRecitorItem(Recitor recitor) {
        Picasso.with(getContext()).load(recitor.getReciter_image()).into(recitorImage);
        recitorName.setText(recitor.getRecitorName());
        if(recitor.isDefault()){
            selectRecitorButton.setImageResource(R.drawable.button_postive);
        }else{
            selectRecitorButton.setImageResource(R.drawable.button_disabled);
        }
    }
    public ImageView getPlayAudioRecitor() {
        return playAudioRecitor;
    }

    public ImageView getSelectRecitorButton() {
        return selectRecitorButton;
    }
}
