package algonquin.cst2335.afinal;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageInfo implements Parcelable {
    private int id;
    private String url;
    private int width;
    private int height;
    private String path;

    // Constructor
    public ImageInfo(int id, String url, int width, int height, String path) {
        this.id = id;
        this.url = url;
        this.width = width;
        this.height = height;
        this.path = path;
    }

    // Parcelable implementation
    protected ImageInfo(Parcel in) {
        id = in.readInt();
        url = in.readString();
        width = in.readInt();
        height = in.readInt();
        path = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(url);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(path);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImageInfo> CREATOR = new Creator<ImageInfo>() {
        @Override
        public ImageInfo createFromParcel(Parcel in) {
            return new ImageInfo(in);
        }

        @Override
        public ImageInfo[] newArray(int size) {
            return new ImageInfo[size];
        }
    };

    // Getter methods
    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getPath() {
        return path;
    }
}
