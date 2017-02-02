package com.ayako_sayama.calculator;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButtonView extends Button {

    public CustomButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButtonView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/Baloo-Regular.ttf");
        setTypeface(tf);
    }
}