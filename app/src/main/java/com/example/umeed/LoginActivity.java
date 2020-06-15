package com.example.umeed;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class LoginActivity extends AppCompatActivity
{

    private long backPressedTime;
    private Toast backToast;
    int flag=0;

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
    public void LoginOptions(View view)
    {
        Button button = (Button)findViewById(R.id.GuestButton);
        LottieAnimationView lottieAnimationView = (LottieAnimationView)findViewById(R.id.lottieAnimationView1);
        if(flag == 0) {
            button.animate().alpha(1).setDuration(500);
            lottieAnimationView.animate().alpha(0).setDuration(500);
            flag=1;
        }
        else
        {
            button.animate().alpha(0).setDuration(500);
            lottieAnimationView.animate().alpha(1).setDuration(500);
            flag=0;
        }
    }
    public void ToHomeGuest(View view)
    {
        Button button = (Button)findViewById(R.id.GuestButton);
        Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
        String str = button.getText().toString();
        intent.putExtra("UserType",str);
        startActivity(intent);
        finish();
    }
    public void ToHome(View view)
    {
        try
        {
            TextView user = (TextView) findViewById(R.id.Username);
            TextView pass = (TextView) findViewById(R.id.Password);
            String str_1 = user.getText().toString();
            String str_2 = pass.getText().toString();
            if (str_1.equals("Admin") && str_2.equals("Admin"))
            {
                Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
                String str = "Admin";
                intent.putExtra("UserType",str);
                startActivity(intent);
                finish();
                //Log.i("Tag", "Button Pressed");
            }
            else if(str_1.length()!=0 && str_2.length()!=0)
            {
                Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if((str_1.length()==0 && str_2.length()==0))
                {
                    Toast.makeText(this, "Enter Username & Password", Toast.LENGTH_SHORT).show();
                }
                else if((str_1.length()==0))
                {
                    Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error!!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_login);
    }
}
