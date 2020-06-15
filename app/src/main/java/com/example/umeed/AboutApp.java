package com.example.umeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class AboutApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.TYPE_STATUS_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        TextView editText = (TextView)findViewById(R.id.editText6);
        TextView editText1 = (TextView)findViewById(R.id.editText4);
        TextView editText2 = (TextView)findViewById(R.id.editText5);
        //editText.setFocusable(false);
        //editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText1.setCursorVisible(false);
        editText1.setKeyListener(null);
        editText2.setCursorVisible(false);
        editText2.setKeyListener(null);
    }
    public void ToVideo(View view)
    {
        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.youtube.com/watch?v=VnJxdVTXc0E&list=PLS1dBDdv8Dre4OjSWHSgwrCObT7JYHJIf&index=59"));
        startActivity(browserIntent);
    }
}
