package com.li.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.li.watchdog.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogConfiguration.configuration(this.getApplicationContext());
        Logger.d("test");
    }
}
