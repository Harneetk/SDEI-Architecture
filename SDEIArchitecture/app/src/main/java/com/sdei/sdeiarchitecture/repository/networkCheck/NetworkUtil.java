package com.sdei.sdeiarchitecture.repository.networkCheck;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkUtil {

    private static int TYPE_WIFI = 1;
    private static int TYPE_MOBILE = 2;
    private static int TYPE_NOT_CONNECTED = 0;
    private static int TYPE_IS_CONNECTING = 3;


    public static String KEY_NO_NETWORK = "Not connected to Internet";
    public static String KEY_POOR_NETWORK = "Poor internet connection";
    public static String KEY_NO_CONNECTION = "Internet Connection not available";


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;

            NetworkInfo[] info = cm.getAllNetworkInfo();

            for (NetworkInfo anInfo : info) {
                if (anInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTING) {
                    return TYPE_IS_CONNECTING;
                }
            }
        }

        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = NetworkUtil.getConnectivityStatus(context);
        String status = null;
        if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
            status = KEY_NO_NETWORK;
        } else if (conn == NetworkUtil.TYPE_IS_CONNECTING) {
            status = KEY_POOR_NETWORK;
        }
        return status;
    }
}
