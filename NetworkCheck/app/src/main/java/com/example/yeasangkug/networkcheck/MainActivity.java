package com.example.yeasangkug.networkcheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeasangkug.networkcheck.Network.NetworkUtil;

public class MainActivity extends AppCompatActivity {

    private TextView tv_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_state = (TextView)findViewById(R.id.tv_NetworkState);

    }

    public void onClick(View v)
    {
        NetworkUtil network = new NetworkUtil(this);

        boolean state = network.getNetworkState();

        tv_state.setText("네트워크 사용 가능 여부 : "+state);
        Toast.makeText(this,"네트워크 사용 가능 여부 : "+state, Toast.LENGTH_SHORT).show();
    }



}
