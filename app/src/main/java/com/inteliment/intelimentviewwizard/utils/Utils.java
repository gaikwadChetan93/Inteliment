package com.inteliment.intelimentviewwizard.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chetan on 23/11/17.
 */
public class Utils {

    private static final boolean SHOW_LOG = true;
    /**
     * Method is used to check internet connectivity
     * @param context
     * @return
     */
    public static boolean isConnectedToInternet(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    /**
     * Method to show debug log message
     * @param tag
     * @param message
     */
    public static void showLog(String tag, String message){
        if(SHOW_LOG){
            Log.d(tag,message);
        }
    }

}
