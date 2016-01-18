package com.example.chris.camerayoutube;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class MainActivity extends Activity {

    Bitmap bit;
    Button button;
    ImageView imageView;
    Button viewDB;
    Database db = new Database(this);
   public static final int CAM_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewAll();

        imageView = (ImageView) findViewById(R.id.image_view);
        button = (Button) findViewById((R.id.button));
        viewDB = (Button) findViewById(R.id.viewDB);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, CAM_REQUEST);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
               // bit =(Bitmap) data.getExtras().get("data");
             //  db.open();
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        });
            }
    private File getFile(){
        File folder = new File("root/sdcard/camera_app");
        if(!folder.exists())
        {
            folder.mkdir();
        }
        File image_file = new File(folder, "cam_image.jpeg");

        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    //    super.onActivityResult(reqeustCode, resultCode, data);

        Bitmap photo = (Bitmap) data.getExtras().get("data");
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), photo, "title", null);
        Uri imageURi = Uri.parse(path);
     //   String uriString = uriMyLogo.toString();

        Database help = new Database(this);

        if (requestCode == CAM_REQUEST) {
            Bitmap photos = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photos);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            help.insert(byteArray);
        }


        Cursor c= help.getImageDB();
        int i=0;
        if(c.getCount()>0)
        {
            Bitmap[]  array=new Bitmap[c.getCount()];
            c.moveToFirst();
            while(c.isAfterLast()==false)
            {

                byte[] bytes=c.getBlob(c.getColumnIndex("imageblob"));

                array[i]= BitmapFactory.decodeByteArray(bytes, 0, 0);
                c.moveToNext();
                i++;
            }
            Log.e("Bitmap length", "" + array.length);
        }

    }
        //store image to sd
        //  String path = "root/sdcard/camera_app/cam_image.jpg";
       // imageView.setImageDrawable(Drawable.createFromPath(path));


    public void viewAll(){
        viewDB.setOnClickListener(

                new View.OnClickListener(){
                @Override
                public void onClick(View v){


                    Cursor res =  db.getAllData();
                    if(res.getCount()==0) {
                        //error
                        showMessage("Error","No data found");
                    return;

                    }else{
                        //display result
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("id: "+ res.getString(0) + "\n");
                            buffer.append("Details: "+ res.getString(1) + "\n");
                            buffer.append("Image: "+ res.getString(2) + "\n\n");


                        }
                        //show data
                        showMessage("Data", buffer.toString());

                    }
                }


                }

        );
    }

    public void showMessage(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    }






