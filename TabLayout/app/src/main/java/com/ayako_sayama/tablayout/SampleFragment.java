package com.ayako_sayama.tablayout;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ayako_sayama on 2017/02/24.
 */
public class SampleFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        TextView textView = new TextView(getActivity());
//        textView.setGravity(Gravity.CENTER);
//        textView.setText(getArguments().getString("name"));
//
//        return textView;

        return inflater.inflate(R.layout.fragment_one, container, false);

    }
}
