package com.example.yeasangkug.bluetoothcheck.BlueTooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Set;

/**
 * Created by yeasangkug on 2016. 6. 15..
 */
public class BlueToothUtil {

    private Context mContext;   // Context
    private Activity mActivity; // Activity
    private BluetoothAdapter mBtAdapter;    // Bluetooth adapter


    private final String TAG = "BlueToothUtil"; // TAG
    public static final int REQUEST_ENABLE_BLUETOOTH = 2; // Intent value

    public BlueToothUtil(Activity activity)
    {
        this.mActivity = activity;
        this.mContext = activity.getApplicationContext();
        this.mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        Log.i(TAG,"BluetoothUtil Class init");
    }


    /**
     * 디바이스가 Bluetooth를 지원하는지 체크
     * @return true : 지원 false : 미지원
     */
    public boolean getSupportBluetooth()
    {
        if(mBtAdapter == null) {
            Log.i(TAG,"Bluetooth 미지원 단말");
            return false;
        }
        else{
            Log.i(TAG,"Bluetooth 지원 단말");
            return true;
        }
    }


    /**
     * Bluetooth의 활성화 여부 체크
     * @return true : 활성화 false : 비활성화
     */
    public boolean getBluetoothState()
    {
        if(mBtAdapter == null)
            return false;

        if(mBtAdapter.isEnabled()){
            Log.i(TAG,"Bluetooth 활성화 상태");
            return true;
        }
        else{
            Log.i(TAG,"Bluetooth 비활성화 상태");
            return false;
        }
    }

    /**
     * Bluetooth 활성화 요청
     */
    public void setOnBluetooth()
    {
        if(mBtAdapter == null || mActivity == null)
            return;

        if(!getBluetoothState()){
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            mActivity.startActivityForResult(intent, REQUEST_ENABLE_BLUETOOTH);
            Log.i(TAG,"Bluetooth 활성화 요청");
        }
    }

    /**
     *  Bluetooth 활성화
     */
    public void setEnableBluetooth()
    {
        if(mBtAdapter == null || mActivity == null)
            return;

        mBtAdapter.enable();
        Log.i(TAG,"Bluetooth 활성화");
    }

    /**
     *  Bluetooth 비활성화
     */
    public void setDisableBluetooth()
    {
        if(mBtAdapter == null || mActivity == null)
            return;

        mBtAdapter.disable();
        Log.i(TAG,"Bluetooth 비활성화");
    }

    /**
     * 페어링 되지 않은 주변 Bluetooth 검색
     */
    public void getAroundDeviceList()
    {
        if(mBtAdapter == null || mActivity == null)
            return;

        mBtAdapter.startDiscovery();

    }

}
