package com.example.umeed;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AddingRecord extends AppCompatActivity {


   /* public void ToHome(View view)
    {
        Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
        startActivity(intent);
        finish();
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent intent = getIntent();
        String flag = intent.getStringExtra("flag");
        int temp = Integer.parseInt(flag);
        //Toast.makeText(getApplicationContext(), flag, Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_adding_record);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        TextView textView = (TextView)findViewById(R.id.AddText);
                        textView.animate().alpha(0).setDuration(500);
                        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottieAnimationView6);
                        lottieAnimationView.animate().alpha(0).setDuration(500);
                        //Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                        LottieAnimationView lottieAnimationView1 = (LottieAnimationView) findViewById(R.id.lottieAnimationView);
                        lottieAnimationView1.animate().alpha(0).setDuration(500);
                        LottieAnimationView lottieAnimationView2 ;
                        if(temp==1)
                        {
                            lottieAnimationView2 = (LottieAnimationView) findViewById(R.id.lottieAnimationView8);
                            TextView textView1 = (TextView)findViewById(R.id.addText2);
                            textView1.animate().alpha(1).setDuration(750);
                            TextView textView2 = (TextView)findViewById(R.id.addText3);
                            textView2.animate().alpha(1).setDuration(750);
                            TextView textView3 = (TextView)findViewById(R.id.Point1);
                            textView3.animate().alpha(1).setDuration(750);
                            TextView textView4 = (TextView)findViewById(R.id.point);
                            textView4.animate().alpha(1).setDuration(750);
                            TextView textView5 = (TextView)findViewById(R.id.point2);
                            textView5.animate().alpha(1).setDuration(750);
                            TextView textView7 = (TextView)findViewById(R.id.point4);
                            textView7.animate().alpha(1).setDuration(750);
                            TextView textView6 = (TextView)findViewById(R.id.editText2);
                            textView6.animate().alpha(1).setDuration(750);
                            textView6.setCursorVisible(false);
                            textView6.setKeyListener(null);

                        }
                        else
                        {
                            lottieAnimationView2 = (LottieAnimationView) findViewById(R.id.lottieAnimationView7);
                            TextView textView1 = (TextView)findViewById(R.id.addText);
                            textView1.animate().alpha(1).setDuration(750);
                        }
                        lottieAnimationView2.animate().alpha(1).setDuration(750);
                        //lottieAnimationView2.setClickable(true);
                    }
                },
                5000
        );
    }
}
