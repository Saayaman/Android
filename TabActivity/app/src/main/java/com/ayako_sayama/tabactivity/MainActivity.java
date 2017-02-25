package com.ayako_sayama.tabactivity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends ActivityGroup{

    TabHost host;

    // Tab index.
    public static int FIRST_TAB = 0;
    public static int SECOND_TAB = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        host = (TabHost)findViewById(R.id.originalTab);


        host.setup();


        // Creating the First tab.

//        TabHost.TabSpec spec;
//        spec = host.newTabSpec("Tab1")
//                .setIndicator("Home", ContextCompat.getDrawable(this, R.drawable.ic_home_white_36dp))
//                .setContent(R.id.content2);
//        spec = host.newTabSpec("one")
//                .setIndicator("First Results")
//                .setContent(R.);
//        host.addTab(spec);

//        TabHost.TabSpec tabSpec1 = host.newTabSpec("tab1");
//        TabHost.TabSpec tabSpec2 = host.newTabSpec("tab2");
//
//        Intent intent = new Intent().setClass(this, FirstActivity.class);
//        tabSpec1.setIndicator("tab1");
//        tabSpec1.setContent(intent);
//        host.addTab(tabSpec1);
//
//        Intent intent2 = new Intent().setClass(this, SecondActivity.class);
//        tabSpec2.setIndicator("tab2");
//        tabSpec2.setContent(intent2);
//        host.addTab(tabSpec2);

        //ContextとFragmentManagerと、FragmentがあたるViewのidを渡してセットアップ


//        View view1 = getLocalActivityManager().startActivity("ArchiveActivity",
//                new Intent(this, FirstActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();


//
        TabHost.TabSpec tabSpec2 = host.newTabSpec("tab2");
        tabSpec2.setIndicator("Button2");
        Intent intent2 = new Intent(this,SecondActivity.class);
        getLocalActivityManager().startActivity("FisrtActivity", intent2);
        tabSpec2.setContent(intent2);
        host.addTab(tabSpec2);

        TabHost.TabSpec tabSpec1 = host.newTabSpec("tab1");
        tabSpec1.setIndicator("Button1");
        Bundle bundle1 = new Bundle();
        bundle1.putString("name", "Tab1");
        Intent intent1 = new Intent(this,SecondActivity.class);
        tabSpec1.setContent(intent1);
        host.addTab(tabSpec1);



    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        host.addTab(host.newTabSpec("one")
//                .setIndicator("First Results")
//                .setContent(new Intent(this, FirstActivity.class)));
//
//        host.addTab(host.newTabSpec("two")
//                .setIndicator("Second Results")
//                .setContent(new Intent(this, SecondActivity.class)));
//    }

    //
//    public static class SampleFragment extends Fragment {
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//            TextView textView = new TextView(getActivity());
//            textView.setGravity(Gravity.CENTER);
//            textView.setText(getArguments().getString("name"));
//
//            return textView;
//        }
//
//    }
}
