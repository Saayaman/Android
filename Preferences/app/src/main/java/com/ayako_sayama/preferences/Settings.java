package com.ayako_sayama.preferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import static com.ayako_sayama.preferences.MainActivity.SHARED_PREF2;

public class Settings extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences colorsPref;

    SharedPreferences.Editor editor;
    SharedPreferences.Editor editorColor;
    CheckBox checkBox;
    CheckBox checkBox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences(MainActivity.SHARED_PREF, MODE_PRIVATE);
        colorsPref = getSharedPreferences(SHARED_PREF2, MODE_PRIVATE);


        checkBox = (CheckBox)findViewById(R.id.dark_theme);
        checkBox.setChecked(preferences.getBoolean("dark_theme", false));

        checkBox2 = (CheckBox)findViewById(R.id.dark_theme);
        checkBox2.setChecked(colorsPref.getBoolean("dark_theme2", false));


        editor = preferences.edit();
        editorColor = colorsPref.edit();

    }


    public void backToTop(View view){


        boolean isDarkTheme = checkBox.isChecked();
        editor.putBoolean("dark_theme", isDarkTheme);
        editor.commit();

        boolean isDarkTheme2 = checkBox.isChecked();
        editorColor.putBoolean("dark_theme2", isDarkTheme2);
        editorColor.commit();

        finish();
    }


}
