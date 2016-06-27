package com.example.yeasangkug.gpssensor.GPS;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by yeasangkug on 2016. 6. 27..
 */
public class GooglePlayClient implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = "GooglePlayClient";

    private Context mContext;                   // Context
    private GoogleApiClient mGoogleApiClient;   // Google Client 객체 (4.0 이상 지원 GooglePlayService)

    private Api<Api.ApiOptions.NoOptions> API;  // 서비스 연동할 Google Play Service명

    /**
     * 생성자 메소드
     * @param context
     * @param API
     */
    public GooglePlayClient(Context context, Api<Api.ApiOptions.NoOptions> API){
        this.mContext = context;
        this.API = API;

        // 구글 클라이언트 객체 생성
        if(mGoogleApiClient == null){
            buildGoogleApiClient(this.API);
        }
    }

    /**
     * Google Service 를 사용하기위한 GoogleClient 객체 생성
     */
    protected synchronized void buildGoogleApiClient(Api<Api.ApiOptions.NoOptions> API){
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(API).build();
    }

    /**
     * Google Client 를 리턴 하는 메소드
     * @return Google Client
     */
    public GoogleApiClient getGoogleApiClient(){

        if(mContext == null){
            return null;
        }

        if(mGoogleApiClient == null){
            buildGoogleApiClient(this.API);
        }

        return mGoogleApiClient;
    }

    /**
     * Google Client 연결 상태 리턴
     * @return true : 연결  false : 비연결 상태
     */
    public boolean isConnected(){
        return mGoogleApiClient.isConnected();
    }

    /**
     * Google API 연결
     */
    public void connect(){

        if(mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    /**
     * Google API 연결 해제
     */
    public void disConnect(){
        if(mGoogleApiClient != null)
            mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG,"GoogleAPIClient 연결");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG,"GoogleAPIClient Suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG,"GoogleAPIClient 연결 실패");
    }
}
