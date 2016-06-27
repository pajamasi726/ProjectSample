package com.example.yeasangkug.gpssensor.GPS;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by yeasangkug on 2016. 6. 27..
 */
public class GooglePlayGPS {

    private static final String TAG = "GooglePlayGPS";

    private int UPDATE_INTERVAL     = 10*1000;      // 10 초 default
    private int FASTEST_INTERVAL    = 10*1000;      // 10 초 default
    private int DISPLACEMENT        = 0;            // 0 meters default

    private Context mContext;                       // Context
    private LocationRequest mLocationRequest;       // 위치 요청 객체
    private LocationListener mLocationListener;     // 위치 갱신 콜백 리스너

    /**
     * 생성자 메소드
     * @param context
     * @param listener
     */
    public GooglePlayGPS(Context context, LocationListener listener){
        this.mContext = context;
        this.mLocationListener = listener;

        createLocationRequest();
    }

    /**
     * 위치정보 요청 파라미터 생성
     */
    public void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);

        Log.i(TAG,"위치 정보 요청 생성 완료");
    }

    /**
     * 위치정보 업데이트 시작
     * @param client
     */
    public void locationUpdateStart(GoogleApiClient client){

        if(mLocationRequest == null){
            createLocationRequest();
            Log.i(TAG,"위치 정보 요청 재생성");
        }

        if(mLocationListener == null) {
            Log.e(TAG,"위치 정보 콜백 리스너 없습니다");
            return;
        }

        try{
            // 위치 정보 갱신 요청
            LocationServices.FusedLocationApi.requestLocationUpdates(client, mLocationRequest, mLocationListener);
        }catch(SecurityException e){
                Log.e(TAG,"SecurityException 안드로이드 버전 에러");
        }

        Log.i(TAG,"위치 정보 업데이트 시작");
    }

    /**
     * 위치 정보 업데이트 중지
     * @param client
     */
    public void locationUpdateStop(GoogleApiClient client){

        if(mLocationListener != null)
            LocationServices.FusedLocationApi.removeLocationUpdates(client,mLocationListener);
    }

    /**
     * GPS의 마지막 위치 정보 가져오기
     * @param client
     * @return
     */
    public Location getLastLocation(GoogleApiClient client){

        Location location = null;

        try{
            // 마지막 위치 정보 가져오기
            LocationServices.FusedLocationApi.getLastLocation(client);
        }catch(SecurityException e){
            Log.e(TAG,"SecurityException 안드로이드 버전 에러");
        }

        return location;
    }


    // GET, SET

    public int getUPDATE_INTERVAL() {
        return UPDATE_INTERVAL;
    }

    public void setUPDATE_INTERVAL(int UPDATE_INTERVAL) {
        this.UPDATE_INTERVAL = UPDATE_INTERVAL;
    }

    public int getFASTEST_INTERVAL() {
        return FASTEST_INTERVAL;
    }

    public void setFASTEST_INTERVAL(int FASTEST_INTERVAL) {
        this.FASTEST_INTERVAL = FASTEST_INTERVAL;
    }

    public int getDISPLACEMENT() {
        return DISPLACEMENT;
    }

    public void setDISPLACEMENT(int DISPLACEMENT) {
        this.DISPLACEMENT = DISPLACEMENT;
    }
}
