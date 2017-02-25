package com.ayako_sayama.tablayout;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTabHost host = (FragmentTabHost)findViewById(android.R.id.tabhost);
        //ContextとFragmentManagerと、FragmentがあたるViewのidを渡してセットアップ
        host.setup(this, getSupportFragmentManager(), R.id.content);

        TabHost.TabSpec tabSpec1 = host.newTabSpec("tab1");
        tabSpec1.setIndicator("Button1");
        Bundle bundle1 = new Bundle();
        bundle1.putString("name", "Tab1");
        host.addTab(tabSpec1, SampleFragment.class, bundle1);

        TabHost.TabSpec tabSpec2 = host.newTabSpec("tab2");
        tabSpec2.setIndicator("Button2");
        Bundle bundle2 = new Bundle();
        bundle2.putString("name", "Tab2");
        host.addTab(tabSpec2, SampleFragment2.class, bundle2);
    }

}
