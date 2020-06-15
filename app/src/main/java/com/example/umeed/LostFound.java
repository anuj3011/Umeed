package com.example.umeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class LostFound extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_lost_found);
    }
    public void ToAdd(View view)
    {
        Intent intent = new Intent(getApplicationContext(),AddRecord.class);
        startActivity(intent);
        finish();
    }
    public void ToAdd2(View view)
    {
        Intent intent = new Intent(getApplicationContext(),FoundRecord.class);
        startActivity(intent);
        finish();
    }
}
