package com.example.umeed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.umeed.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddRecord extends AppCompatActivity {

    public static final int IMAGE_GALLERY_RESULT = 1;
    ImageView imgPictures;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_add_record);
        imgPictures= (ImageView)findViewById(R.id.imgPictures);
        // if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        //         == PackageManager.PERMISSION_DENIED){
        //      ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.CAMERA}, requestCode);
        // }

    }
    public void addRecord(View view)
    {
        String first_name=null,last_name=null;
        int Age=0;
        long Phone=0;
        int flag=1;
        String Address=null,Date=null;
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
        textView = (TextView) findViewById(R.id.PhoneInput);
        if(textView.getText().toString().length() == 0)
        {
            flag=0;
            //Toast.makeText(getApplicationContext(), "Enter Phone No of Complainant", Toast.LENGTH_LONG).show();
        }
        else{
        Phone = Long.parseLong(textView.getText().toString());}
        textView = (TextView) findViewById(R.id.AddressInput);
        Address = textView.getText().toString();
        textView = (TextView) findViewById(R.id.Address2);
        Address = Address+textView.getText().toString();
        textView = (TextView) findViewById(R.id.DateInput);
        Date = textView.getText().toString();
        textView = (TextView) findViewById(R.id.AgeInput);
        TextView textView1 = (TextView) findViewById(R.id.PhoneInput);
        if(first_name.length()==0 || last_name.length() ==0)
        {
            flag=0;
            Toast.makeText(getApplicationContext(), "Enter Name of the Person", Toast.LENGTH_LONG).show();
        }
        else if(textView.getText().toString().length() == 0)
        {
            Toast.makeText(getApplicationContext(), "Enter Age of the Person", Toast.LENGTH_LONG).show();
        }
        else if(Address.length()==0)
        {
            flag=0;
            Toast.makeText(getApplicationContext(), "Enter Address of Complainant", Toast.LENGTH_LONG).show();
        }
        else if(textView1.getText().toString().length() == 0)
        {
            Toast.makeText(getApplicationContext(), "Enter Phone No of Complainant", Toast.LENGTH_LONG).show();
        }
        else if(Date.length()==0)
        {
            flag=0;
            Toast.makeText(getApplicationContext(), "Enter the Date of Complaint", Toast.LENGTH_LONG).show();
        }
        if(flag==1)
        {
            Intent intent = new Intent(getApplicationContext(),AddingRecord.class);
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

                    imgPictures.setImageBitmap(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to open", Toast.LENGTH_LONG).show();
                }


            }
        }
    }
}
