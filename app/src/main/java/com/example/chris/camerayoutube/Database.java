package com.example.chris.camerayoutube;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    public static final String TABLE_IMAGES = "images";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_DETAILS = "details";
    public static final String COLUMN_COMMENT = "COMMENT";
    public static final String TABLE_COMMENTS = "COMMENT";
    private static final String DATABASE_NAME = "image_details";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE =
            "create table " + TABLE_IMAGES
                    + "(" + COLUMN_ID + "image id "
                    + COLUMN_IMAGE + "images"
                    + COLUMN_DETAILS + "details)";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Database.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);
        onCreate(db);
    }

    private static void insertDetails(SQLiteDatabase db, String name,
                                      String description, int resourceId) {
     //   SQLiteDatabase database = this.getWritableDatabase();
        ContentValues imageValues = new ContentValues();
        imageValues.put("NAME", name);
        imageValues.put("DESCRIPTION", description);
        imageValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("IMAGE", null, imageValues);


    }

    public Cursor getImageDB(){
        return(getReadableDatabase().rawQuery("SELECT" + COLUMN_IMAGE + "FROM"+ DATABASE_NAME + ";", null));
    }

    public void insert (byte Byte[]){
        ContentValues cv = new ContentValues();
        cv.put("",Byte);
        Log.e("inserted","testing");
        getWritableDatabase().insert("image", "imagedet",cv);


    }
public byte[] getAll(Cursor c){

    return (c.getBlob(1));

}

    public Cursor getAllData(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("SELECT * FROM" + TABLE_IMAGES,null);
        return result;
    }




}
