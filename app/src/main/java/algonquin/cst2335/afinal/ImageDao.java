package algonquin.cst2335.afinal;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ImageDao {

    @Insert
    long insertImage(ImageInfo image);

    @Query("DELETE FROM images WHERE id = :id")
    int deleteImage(long id);

    @Query("SELECT * FROM images")
    List<ImageInfo> getAllImages();
}
