package com.ayako_sayama.tablayout;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTabHost host = (FragmentTabHost)findViewById(android.R.id.tabhost);
        //ContextとFragmentManagerと、FragmentがあたるViewのidを渡してセットアップ
        host.setup(this, getSupportFragmentManager(), R.id.content);

        TabHost.TabSpec tabSpec1 = host.newTabSpec("tab1");
        Button button1 = new Button(this);
        button1.setBackgroundResource(R.drawable.ic_brightness_2_black_24dp);
        tabSpec1.setIndicator(button1);
        Bundle bundle1 = new Bundle();
        bundle1.putString("name", "Tab1");
        host.addTab(tabSpec1, SampleFragment.class, bundle1);

        TabHost.TabSpec tabSpec2 = host.newTabSpec("tab2");
        Button button2 = new Button(this);
        button2.setBackgroundResource(R.drawable.ic_brightness_low_black_24dp);
        tabSpec2.setIndicator(button2);
        Bundle bundle2 = new Bundle();
        bundle2.putString("name", "Tab2");
        host.addTab(tabSpec2, SampleFragment.class, bundle2);

    }

    public static class SampleFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            textView.setText(getArguments().getString("name"));

            return textView;
        }

    }
}
