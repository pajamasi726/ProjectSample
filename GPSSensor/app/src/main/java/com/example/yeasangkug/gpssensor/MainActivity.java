package com.example.yeasangkug.gpssensor;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeasangkug.gpssensor.GPS.GooglePlayClient;
import com.example.yeasangkug.gpssensor.GPS.GooglePlayGPS;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


public class MainActivity extends AppCompatActivity implements LocationListener{

    private TextView tv;

    private Button btn_showLocation, btn_StartLocationUpdate;

    private GooglePlayClient    mGooglePlayClient;
    private GooglePlayGPS       mGooglePlayGPS;

    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tv_latlon);

        btn_showLocation = (Button)findViewById(R.id.btn_ShowLocation);

        init();
    }

    private void init(){
        mGooglePlayClient = new GooglePlayClient(this, LocationServices.API);
        mGooglePlayGPS = new GooglePlayGPS(this, this);
    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;

        Toast.makeText(getApplicationContext(), "Location changed!",
                Toast.LENGTH_SHORT).show();

        displayLocation();
    }



    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_ShowLocation :
                mGooglePlayGPS.locationUpdateStart(mGooglePlayClient.getGoogleApiClient());
            break;
        }
    }

    private void displayLocation() {
        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            tv.setText(latitude + ", " + longitude);

        } else {
            tv.setText("(Couldn't get the location. Make sure location is enabled on the device)");
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        if(mGooglePlayClient != null){
            mGooglePlayClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mGooglePlayClient.isConnected()) {
            mGooglePlayGPS.locationUpdateStart(mGooglePlayClient.getGoogleApiClient());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGooglePlayGPS.locationUpdateStop(mGooglePlayClient.getGoogleApiClient());
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mGooglePlayClient.isConnected()){
            mGooglePlayClient.disConnect();
        }
    }
}
