package com.example.umeed;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class RecordFound extends AppCompatActivity {
    ImageView imgPictures;
    static LatLng location;
    int finalFlag=0;
    public void ToMap(View view)
    {
        Intent intent = new Intent(getApplicationContext(),MapsActivity2.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_record_found);
        LottieAnimationView lottieAnimationView = (LottieAnimationView)findViewById(R.id.lottieAnimationView1);
        lottieAnimationView.animate().alpha(1).setDuration(1500);
        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag", 2);
      //  Toolbar toolbar = findViewById(R.id.toolbar);
        byte[] image_found;
        imgPictures = (ImageView) findViewById(R.id.imgPictures);

        //setSupportActionBar(toolbar);
        try {

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("TEST_new5", MODE_PRIVATE, null);
            Cursor C;
            if (flag == 0) {
                String str = Searching_Activity.first_inputName;
                String[] arrOfStr = str.split(" ");
                C = myDatabase.rawQuery("SELECT * FROM test_new7 where first_name='" + arrOfStr[0] + "'", null);
                int firstNameIndex = C.getColumnIndex("first_name");
                int lastNameIndex = C.getColumnIndex("last_name");
                int ageIndex = C.getColumnIndex("age");
                int dateIndex = C.getColumnIndex("Date");
                int locationIndex = C.getColumnIndex("Location");
                int imageIndex = C.getColumnIndex("image");


                C.moveToFirst();
                while (!C.isAfterLast()) {
                    Log.i("Result fIRST Name found ", C.getString(firstNameIndex));
                    Log.i("Result LAST NAME", C.getString(lastNameIndex));
                    Log.i("Result AGE", Integer.toString(C.getInt(ageIndex)));
                    Log.i("Result  Date", C.getString(dateIndex));
                    Log.i("Result  Location", C.getString(locationIndex));

                    TextView textView = (TextView) findViewById(R.id.NameInput);
                    textView.setEnabled(false);
                    textView.setText(C.getString(firstNameIndex));
                    textView = (TextView) findViewById(R.id.LastName);
                    textView.setText(C.getString(lastNameIndex));
                    textView.setEnabled(false);

                    textView = (TextView) findViewById(R.id.AgeInput);
                    textView.setText(Integer.toString(C.getInt(ageIndex)));
                    textView.setEnabled(false);
                    textView = (TextView) findViewById(R.id.DateInput);
                    String date = C.getString(dateIndex);
                    SimpleDateFormat input = new SimpleDateFormat("dd/MM/yy");
                    SimpleDateFormat output = new SimpleDateFormat("dd MMM yyyy");
                    // parse input
                    textView.setText(output.format(input.parse(date)));   // format output

                    textView.setEnabled(false);


                        String[] latlong = C.getString(locationIndex).split(",");
                        double latitude =Double.parseDouble(latlong[0]);
                        double longitude =Double.parseDouble(latlong[1]);
                        location = new LatLng(latitude,longitude);
                        textView.setEnabled(false);


                    image_found = C.getBlob(imageIndex);
                    Bitmap image = BitmapFactory.decodeByteArray(image_found, 0, image_found.length);
                    imgPictures.setImageBitmap(image);

                    finalFlag=1;
                    C.moveToNext();
                }
            } else {

                C = myDatabase.rawQuery("SELECT * FROM test_new7 ", null);


                int firstNameIndex = C.getColumnIndex("first_name");
                int lastNameIndex = C.getColumnIndex("last_name");
                int ageIndex = C.getColumnIndex("age");
                int dateIndex = C.getColumnIndex("Date");
                int locationIndex = C.getColumnIndex("Location");
                int imageIndex = C.getColumnIndex("image");


                C.moveToFirst();
                while (!C.isAfterLast()) {
                    Log.i("Result fIRST Name found ", C.getString(firstNameIndex));
                    Log.i("Result LAST NAME", C.getString(lastNameIndex));
                    Log.i("Result AGE", Integer.toString(C.getInt(ageIndex)));
                    Log.i("Result  Date", C.getString(dateIndex));
                    Log.i("Result  Location", C.getString(locationIndex));

                    image_found = C.getBlob(imageIndex);
                    Bitmap image_got = BitmapFactory.decodeByteArray(image_found, 0, image_found.length);
                    Bitmap imageRecieved = BitmapFactory.decodeByteArray(Searching_Activity.recieved_image_byte, 0, Searching_Activity.recieved_image_byte.length);


                    Boolean flagImageCheck = image_got.sameAs(imageRecieved);


                    if (flagImageCheck) {
                        finalFlag=1;
                        TextView textView = (TextView) findViewById(R.id.NameInput);
                        textView.setEnabled(false);
                        textView.setText(C.getString(firstNameIndex));
                        textView = (TextView) findViewById(R.id.LastName);
                        textView.setText(C.getString(lastNameIndex));
                        textView.setEnabled(false);

                        textView = (TextView) findViewById(R.id.AgeInput);
                        textView.setText(Integer.toString(C.getInt(ageIndex)));
                        textView.setEnabled(false);
                        textView = (TextView) findViewById(R.id.DateInput);
                        String date = C.getString(dateIndex);
                        SimpleDateFormat input = new SimpleDateFormat("dd/MM/yy");
                        SimpleDateFormat output = new SimpleDateFormat("dd MMM yyyy");
                        // parse input
                        textView.setText(output.format(input.parse(date)));   // format output

                        textView.setEnabled(false);

                        String[] latlong = C.getString(locationIndex).split(",");
                        double latitude =Double.parseDouble(latlong[0]);
                        double longitude =Double.parseDouble(latlong[1]);
                        location = new LatLng(latitude,longitude);

                        textView.setEnabled(false);






                        imgPictures.setImageBitmap(image_got);

                    }
                    //else {
                       // TextView textView = (TextView) findViewById(R.id.NameInput);
                      //  textView.setEnabled(false);
                       // textView.setText(("NOOOPE"));
                       // NotFound();
                    //}
                    C.moveToNext();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();

        }
        if(finalFlag==0) NotFound();
    }

    public void NotFound()
    {
        LottieAnimationView lottieAnimationView = (LottieAnimationView)findViewById(R.id.lottieAnimationView1);
        lottieAnimationView.setVisibility(View.GONE);
        TextView textView = (TextView) findViewById(R.id.NameInput);
        textView.setVisibility(View.GONE);
        textView = (TextView) findViewById(R.id.AgeInput);
        textView.setVisibility(View.GONE);
        textView = (TextView) findViewById(R.id.LastName);
        textView.setVisibility(View.GONE);
        textView = (TextView) findViewById(R.id.DateInput);
        textView.setVisibility(View.GONE);
        textView = (TextView) findViewById(R.id.textView7);
        textView.setVisibility(View.GONE);
        textView = (TextView) findViewById(R.id.phoneText2);
        textView.setVisibility(View.GONE);
        textView = (TextView) findViewById(R.id.ageText);
        textView.setVisibility(View.GONE);
        ImageView imageView = (ImageView)findViewById(R.id.imgPictures);
        imageView.setVisibility(View.GONE);
        Button button = (Button)findViewById(R.id.button2);
        button.setVisibility(View.GONE);
        lottieAnimationView = (LottieAnimationView)findViewById(R.id.lottieAnimationView);
        lottieAnimationView.animate().alpha(1).setDuration(500);
    }
}
