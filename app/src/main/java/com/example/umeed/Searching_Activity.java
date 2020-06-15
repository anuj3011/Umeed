package com.example.umeed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;

public class Searching_Activity extends AppCompatActivity {

    static int flag=0;
    static String first_inputName=null;
    public static final int IMAGE_GALLERY_RESULTNEW = 1;
    LottieAnimationView lottieAnimationView2;
    static byte[] recieved_image_byte;
    Intent intent;
    int act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_searching_);
        lottieAnimationView2= findViewById(R.id.lottieAnimationView3);
        intent= getIntent();
        act = intent.getIntExtra("act", 0);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        LottieAnimationView lottieAnimationView = (LottieAnimationView)findViewById(R.id.lottieAnimationView);
                        TextView textView = (TextView)findViewById(R.id.editText);
                        lottieAnimationView.animate().alpha(0.25f).setDuration(100);
                        textView.animate().alpha(1).setDuration(100);
                    }
                },
                1000
        );
    }
    public void AddImage(View view)
    {
        flag=2;
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        String pictureDirectoryPath = pictureDirectory.getPath();

        Uri data = Uri.parse(pictureDirectoryPath);

        photoPickerIntent.setDataAndType(data,"image/*");
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_RESULTNEW);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_RESULTNEW) {
                Uri imageUri = data.getData();

                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG,0,stream);
                    recieved_image_byte= stream.toByteArray();


                    lottieAnimationView2.setImageBitmap(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to open", Toast.LENGTH_SHORT).show();
                }


            }
        }}


    public void Searching(View view)
    {
        Random d = new Random();
        int i = 3+ d.nextInt(5);
        TextView textView3 = (TextView) findViewById(R.id.editText);
        TextView textView5 = (TextView) findViewById(R.id.textView6);
        LottieAnimationView lottieAnimationView2;
        LottieAnimationView lottieAnimationView5 = (LottieAnimationView)findViewById(R.id.lottieAnimationView5);
        if(textView3.length()!=0)
        {
            flag=1;
        }
        if(flag!=0) {
            TextView textView = (TextView) findViewById(R.id.textView4);
            TextView textView2 = (TextView) findViewById(R.id.textView5);
            Button button = (Button) findViewById(R.id.button3);
            LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottieAnimationView);
            LottieAnimationView lottieAnimationView3 = (LottieAnimationView) findViewById(R.id.lottieAnimationView3);
            if(flag==1)
            {
                lottieAnimationView2 = (LottieAnimationView) findViewById(R.id.lottieAnimationView4);
            }else{
                lottieAnimationView2 = (LottieAnimationView) findViewById(R.id.lottieAnimationView2);
            }
            textView.animate().alpha(0).setDuration(500);
            textView2.animate().alpha(0).setDuration(500);
            textView3.animate().alpha(0).setDuration(500);
            lottieAnimationView.animate().alpha(0).setDuration(500);
            lottieAnimationView3.animate().alpha(0).setDuration(500);
            button.animate().alpha(0).setDuration(500);
            lottieAnimationView2.animate().alpha(1).setDuration(500);
            lottieAnimationView5.animate().alpha(1).setDuration(500);
            textView5.animate().alpha(1).setDuration(500);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                           // Toast.makeText(Searching_Activity.this, i+"Seconds have passed", Toast.LENGTH_SHORT).show();
                            //Code which will run after Begin search is pressed
                            //Intent intent = new ...;
                            //startActivity();
                            //Abhi ke liye mein kuch random seconds bhaad animation rokh raha hu,
                            //jab apna code likhyo toh isse delete kar diyo....
                            TextView textView3 = (TextView) findViewById(R.id.editText);
                            first_inputName =  textView3.getText().toString();
                            Intent intent1;
                            if(act==1) {
                                intent1 = new Intent(getApplicationContext(), RecordFound.class);
                            }
                            else{
                                intent1 = new Intent(getApplicationContext(), UpdateRecord.class);
                            }
                            if(first_inputName.length()==0) {
                                intent1.putExtra("flag", 1);
                                startActivity(intent1);
                                finish();
                            }
                            else
                            {
                                intent1.putExtra("flag", 0);
                                startActivity(intent1);
                                finish();
                            }


                        }
                    },
                    1000*i
            );
        }else
        {
            Toast.makeText(this, "Enter Name or Upload Image!", Toast.LENGTH_SHORT).show();
        }
    }

}
