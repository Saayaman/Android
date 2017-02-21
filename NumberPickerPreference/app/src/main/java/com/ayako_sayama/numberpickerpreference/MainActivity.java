package com.ayako_sayama.numberpickerpreference;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "<NumberPicker> ";
    private NumberPicker picker;
    private Dialog dialog;
    TextView textview;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    int prefValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textview = (TextView)findViewById(R.id.showValue);
        prefs = getSharedPreferences(
                "com.ayako_sayama.numberpicker", Context.MODE_PRIVATE);

        setText();
    }

    private void setText() {
        prefValue = prefs.getInt("user", 20);
        textview.setText(prefValue + "");
    }


    public void showDialog(View view) {

        //setup dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        //setup numberpicker
        picker = new NumberPicker(this);
        picker.setMinValue(10);
        picker.setMaxValue(30);
        picker.setValue(prefValue);

        final FrameLayout parent = new FrameLayout(this);
        parent.addView(picker, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER));
        builder.setView(parent);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                int num = picker.getValue();

                editor = prefs.edit();
                editor.putInt("user", num);
                editor.commit();
                dialog.dismiss();
                setText();
            }
        });


        builder.setNegativeButton(R.string.cancel, null);

        //builderをcreateして、showしないといけない。
        //意味はわからなくてもいい、だだ実行しろ
        dialog = builder.create();
        dialog.show();

    }


}
