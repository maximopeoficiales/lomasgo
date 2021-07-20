
package com.maximoprog.lomasgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Medium implements Parcelable {

    @Expose
    private String ext;
    @Expose
    private String hash;
    @Expose
    private Long height;
    @Expose
    private String mime;
    @Expose
    private String name;
    @Expose
    private String path;
    @Expose
    private Double size;
    @Expose
    private String url;
    @Expose
    private Long width;

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ext);
        dest.writeString(this.hash);
        dest.writeValue(this.height);
        dest.writeString(this.mime);
        dest.writeString(this.name);
        dest.writeString(this.path);
        dest.writeValue(this.size);
        dest.writeString(this.url);
        dest.writeValue(this.width);
    }

    public void readFromParcel(Parcel source) {
        this.ext = source.readString();
        this.hash = source.readString();
        this.height = (Long) source.readValue(Long.class.getClassLoader());
        this.mime = source.readString();
        this.name = source.readString();
        this.path = source.readString();
        this.size = (Double) source.readValue(Double.class.getClassLoader());
        this.url = source.readString();
        this.width = (Long) source.readValue(Long.class.getClassLoader());
    }

    public Medium() {
    }

    protected Medium(Parcel in) {
        this.ext = in.readString();
        this.hash = in.readString();
        this.height = (Long) in.readValue(Long.class.getClassLoader());
        this.mime = in.readString();
        this.name = in.readString();
        this.path = in.readString();
        this.size = (Double) in.readValue(Double.class.getClassLoader());
        this.url = in.readString();
        this.width = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Medium> CREATOR = new Parcelable.Creator<Medium>() {
        @Override
        public Medium createFromParcel(Parcel source) {
            return new Medium(source);
        }

        @Override
        public Medium[] newArray(int size) {
            return new Medium[size];
        }
    };
}
