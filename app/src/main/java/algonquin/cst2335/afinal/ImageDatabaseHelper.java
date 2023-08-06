package algonquin.cst2335.afinal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.afinal.ImageInfo;

public class ImageDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "image_database";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "images";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_WIDTH = "width";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_PATH = "path";

    public ImageDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_URL + " TEXT, " +
                COLUMN_WIDTH + " INTEGER, " +
                COLUMN_HEIGHT + " INTEGER, " +
                COLUMN_PATH + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert a new image into the database
    public long insertImage(ImageInfo imageInfo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_URL, imageInfo.getUrl());
        values.put(COLUMN_WIDTH, imageInfo.getWidth());
        values.put(COLUMN_HEIGHT, imageInfo.getHeight());
        values.put(COLUMN_PATH, imageInfo.getPath());

        long insertedRowId = db.insert(TABLE_NAME, null, values);
        db.close();

        return insertedRowId;
    }

    // Delete an image from the database by ID
    public int deleteImage(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return deletedRows;
    }

    // Get all images from the database
    public List<ImageInfo> getAllImages() {
        List<ImageInfo> imageList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex(COLUMN_URL));
                @SuppressLint("Range") int width = cursor.getInt(cursor.getColumnIndex(COLUMN_WIDTH));
                @SuppressLint("Range") int height = cursor.getInt(cursor.getColumnIndex(COLUMN_HEIGHT));
                @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(COLUMN_PATH));

                ImageInfo imageInfo = new ImageInfo( url, width, height, path);
                imageList.add(imageInfo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return imageList;
    }
}
