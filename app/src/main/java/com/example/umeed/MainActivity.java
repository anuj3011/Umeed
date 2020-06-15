package com.example.umeed;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
    public void ToLogin(View view)
    {
        //Log.i("Tag","Button Pressed!!");
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_main);
        Random d = new Random();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        LottieAnimationView lottieAnimationView = (LottieAnimationView)findViewById(R.id.lottieAnimationView);
                        lottieAnimationView.animate().alpha(1).setDuration(3000);
                        LottieAnimationView lottieAnimationView2 = (LottieAnimationView)findViewById(R.id.lottieAnimationView9);
                        lottieAnimationView2.animate().alpha(0).setDuration(1500);
                        Button start = (Button)findViewById(R.id.StartButton);
                        start.animate().alpha(1).setDuration(4000);
                        ImageView imageView = (ImageView)findViewById(R.id.UmeedImage);
                        imageView.animate().alpha(1).setDuration(3000);
                        TextView textView = (TextView)findViewById(R.id.textView11);
                        textView.animate().alpha(0).setDuration(1500);

                    }
                },
                3000
        );

    }
}
