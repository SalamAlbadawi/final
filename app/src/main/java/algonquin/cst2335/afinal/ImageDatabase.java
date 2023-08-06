package algonquin.cst2335.afinal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ImageInfo.class}, version = 1)
public abstract class ImageDatabase extends RoomDatabase {
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_WIDTH = "width";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_PATH = "path";
    private static ImageDatabase instance;

    public abstract ImageDao imageInfoDao();

    public static synchronized ImageDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ImageDatabase.class, "image_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
