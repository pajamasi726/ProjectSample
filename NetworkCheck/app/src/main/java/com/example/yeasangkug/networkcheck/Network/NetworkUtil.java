package com.example.yeasangkug.networkcheck.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * Created by yeasangkug on 2016. 6. 15..
 */
public class NetworkUtil {

    private Context mContext;
    private final String TAG = "NetworkUtil";

    public NetworkUtil(Context context)
    {
        this.mContext = context;
        Log.i(TAG,"NetworkUtil class init");
    }

    /**
     * 네트워크 사용 여부를 리턴하는 메소드
     * @return true : 사용가능, false : 사용 불가능
     */
    public boolean getNetworkState()
    {
        if(mContext == null)
            return false;

        ConnectivityManager manager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean mobileAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isAvailable();
        boolean mobileConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

        boolean wifiAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isAvailable();
        boolean wifiConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();


        if((mobileAvailable && mobileConnect) || (wifiAvailable && wifiConnect)){
            Log.i(TAG,"네트워크 사용 가능");
            return true;
        }
        else{
            Log.i(TAG,"네트워크 사용 불가능");
            return false;
        }

    }
}
