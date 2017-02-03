package com.ayako_sayama.service_notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    EditText editText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = (EditText) findViewById(R.id.editText);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    startService();
                }
                else {
                    stopService();
                }
            }
        });

    }


    private void stopService() {
        startService(new Intent(this, MyService.class));

//        String sendText = editText.getText().toString();
//        intent = new Intent("action", Uri.parse(sendText));
//        startService(intent);

    }

    private void startService() {
        stopService(new Intent(this, MyService.class));
    }
}
