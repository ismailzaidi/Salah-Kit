package com.zdmedia.salahnotify.CustomViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zdmedia.salahnotify.R;

/**
 * Created by JavaFreak on 18/06/2016.
 */
public class CustomProgressDialog {
    private MaterialDialog dialog;
    private Context context;
    public CustomProgressDialog(Context context){
        this.context=context;
    }
    private void initDialog() {
        dialog = getMaterialDialog().build();
        dialog.setCancelable(false);
        dialog.show();
    }

    @NonNull
    private MaterialDialog.Builder getMaterialDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.title("Loading Stats...");
        builder.content("Please Wait. . .");
        builder.autoDismiss(false);
//        if(isGuest){
        builder.progress(true, 0);
//        }else{
//            builder.progress(false, 100);
//        }
        builder.widgetColor(ContextCompat.getColor(context, R.color.colorPrimary));
        builder.backgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        builder.titleColor(ContextCompat.getColor(context, android.R.color.white));
        builder.contentColor(ContextCompat.getColor(context, android.R.color.white));
        return builder;
    }
    public void dismiss(){
        dialog.dismiss();
    }

}
