package com.example.umeed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.umeed.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class FoundRecord extends AppCompatActivity {

    public static final int IMAGE_GALLERY_RESULT = 1;
    ImageView imgPictures;
    static LatLng FoundLocation;
    static String FoundAddress=" ";
    static byte[] image_byte;
    int flag2=0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_found_record);
        imgPictures= (ImageView)findViewById(R.id.imgPictures);
        // if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        //         == PackageManager.PERMISSION_DENIED){
        //      ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.CAMERA}, requestCode);
        // }

    }
    public void ToMap(View view)
    {
        Button button = (Button)findViewById(R.id.button2);
        button.animate().alpha(.5f).setDuration(1000);
        flag2=1;
        Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
        startActivity(intent);
    }
    public void addRecord(View view)
    {
        String Transfer="0";
        String first_name=null,last_name=null;
        int Age=0;
        int flag=1;
        String Date=null;
        TextView textView = (TextView) findViewById(R.id.NameInput);
        first_name =  textView.getText().toString();
        textView = (TextView) findViewById(R.id.LastName);
        last_name =  textView.getText().toString();
        textView = (TextView) findViewById(R.id.AgeInput);
        if(textView.getText().toString().length() == 0)
        {
            flag=0;
            //Toast.makeText(getApplicationContext(), "Enter Age of the Person", Toast.LENGTH_LONG).show();
        }
        else
        {
            Age = Integer.parseInt(textView.getText().toString());}
        textView = (TextView) findViewById(R.id.DateInput);
        Date = textView.getText().toString();
        textView = (TextView) findViewById(R.id.AgeInput);
        if(first_name.length()==0 || last_name.length() ==0)
        {
            flag=0;
            Toast.makeText(getApplicationContext(), "Enter Name of the Person", Toast.LENGTH_SHORT).show();
        }
        else if(textView.getText().toString().length() == 0)
        {
            Toast.makeText(getApplicationContext(), "Enter Age of the Person", Toast.LENGTH_SHORT).show();
        }
        else if(Date.length()==0)
        {
            flag=0;
            Toast.makeText(getApplicationContext(), "Enter the Date of Complaint", Toast.LENGTH_SHORT).show();
        }
        else if(FoundAddress.equals(" ") || flag2 == 0)
        {
            flag=0;
            Toast.makeText(getApplicationContext(), "Pin Location", Toast.LENGTH_SHORT).show();
        }
        if(flag==1)
        {
            String location=String.valueOf(FoundLocation.latitude + "," + FoundLocation.longitude);
            try {
                SQLiteDatabase mydatabase = this.openOrCreateDatabase("TEST_new5", MODE_PRIVATE, null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS test_new7 (first_name VARCHAR,last_name VARCHAR,age INT(3),Date VARCHAR,Location VARCHAR,image BLOB)");
                String sql ="INSERT INTO test_new7 (first_name, last_name, age,Date,Location,image) VALUES (?,?,?,?,?,?)";
                SQLiteStatement statement = mydatabase.compileStatement(sql);
                statement.bindString(1,first_name);
                statement.bindString(2,last_name);
                statement.bindString(3, String.valueOf(Age));
                statement.bindString(4,Date);
                statement.bindString(5,location);
                statement.bindBlob(6,image_byte);

                statement.execute();




                Cursor C = mydatabase.rawQuery("SELECT * FROM test_new7 ", null);



                int firstNameIndex = C.getColumnIndex("first_name");
                int lastNameIndex = C.getColumnIndex("last_name");
                int ageIndex = C.getColumnIndex("age");
                int dateIndex = C.getColumnIndex("Date");
                int locationIndex = C.getColumnIndex("Location");
                int imageIndex = C.getColumnIndex("image");


                C.moveToFirst();
                while (!C.isAfterLast()) {
                    Log.i("Result fIRST Name", C.getString(firstNameIndex));
                    Log.i("Result LAST NAME", C.getString(lastNameIndex));
                    Log.i("Result AGE", Integer.toString(C.getInt(ageIndex)));
                    Log.i("Result  Date", C.getString(dateIndex));
                    Log.i("Result  Location", C.getString(locationIndex));


                    C.moveToNext();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
                Transfer = "1";
            }

            Intent intent = new Intent(getApplicationContext(),AddingRecord.class);
            intent.putExtra("flag",Transfer);
            startActivity(intent);
            finish();
            // Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
        }

    }

    public void ImageClick(View view)
    {
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        String pictureDirectoryPath = pictureDirectory.getPath();

        Uri data = Uri.parse(pictureDirectoryPath);

        photoPickerIntent.setDataAndType(data,"image/*");
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_RESULT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_RESULT) {
                Uri imageUri = data.getData();

                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG,0,stream);
                    image_byte= stream.toByteArray();

                    imgPictures.setImageBitmap(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to open", Toast.LENGTH_SHORT).show();
                }


            }
        }
    }
}
