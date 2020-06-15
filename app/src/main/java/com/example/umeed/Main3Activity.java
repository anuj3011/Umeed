package com.example.umeed;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Main3Activity extends AppCompatActivity
{

    private long backPressedTime;
    private Toast backToast;
    public String str=null;
    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        }else
        {
           backToast= Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
           backToast.show();;
        }
        backPressedTime = System.currentTimeMillis();
    }

    public void ToWeb(View view)
    {
        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.google.com"));
        startActivity(browserIntent);
        //Log.i("Button","Pressed");
    }
    public void AddRecord(View view)
    {
        if(str.equals("Guest"))
        {
            Toast.makeText(this, "Login as User to access this feature ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(),LostFound.class);
            startActivity(intent);
            Log.i("Logged In as",str);
        }
    }
    public void UpdateRecord(View view)
    {
        if(str.equals("Guest"))
        {
            Toast.makeText(this, "Login as User to access this feature ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(),Searching_Activity.class);
            intent.putExtra("act", 2);

            startActivity(intent);

            Log.i("Logged In as",str);
        }
    }
    public void SearchRecord(View view)
    {
        Intent intent = new Intent(getApplicationContext(),Searching_Activity.class);
        intent.putExtra("act", 1);

        startActivity(intent);
        Log.i("Logged In as",str);
    }
    public void AboutApp(View view)
    {
        Intent intent = new Intent(getApplicationContext(),AboutApp.class);
        startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.TYPE_STATUS_BAR);
       // g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_main3);
        TextView textView = (TextView)findViewById(R.id.WelcomeText);
        Intent intent = getIntent();
        str = intent.getStringExtra("UserType");
        if(str.equals("Guest"))
        {
            String temp = "Logged in as Guest";
            textView.setText(temp);
            LottieAnimationView button = (LottieAnimationView) findViewById(R.id.lottieAnimationView3);
            button.animate().alpha(.4f).setDuration(1000);
            LottieAnimationView button1 = (LottieAnimationView) findViewById(R.id.lottieAnimationView2);
            button1.animate().alpha(.4f).setDuration(1000);
        }
        else
        {
            String temp = str;
            textView.setText(temp);
        }
    }
}
