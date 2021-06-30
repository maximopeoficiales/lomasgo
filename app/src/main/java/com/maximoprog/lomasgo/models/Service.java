
package com.maximoprog.lomasgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Service implements Parcelable {

    @SerializedName("created_at")
    private String createdAt;
    @Expose
    private String description;
    @Expose
    private Long id;
    @Expose
    private Image image;
    @Expose
    private String latitude;
    @Expose
    private String longitude;
    @Expose
    private String name;
    @SerializedName("published_at")
    private String publishedAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createdAt);
        dest.writeString(this.description);
        dest.writeValue(this.id);
        dest.writeParcelable(this.image, flags);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeString(this.name);
        dest.writeString(this.publishedAt);
        dest.writeString(this.updatedAt);
    }

    public void readFromParcel(Parcel source) {
        this.createdAt = source.readString();
        this.description = source.readString();
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.image = source.readParcelable(Image.class.getClassLoader());
        this.latitude = source.readString();
        this.longitude = source.readString();
        this.name = source.readString();
        this.publishedAt = source.readString();
        this.updatedAt = source.readString();
    }

    public Service() {
    }

    protected Service(Parcel in) {
        this.createdAt = in.readString();
        this.description = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.image = in.readParcelable(Image.class.getClassLoader());
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.name = in.readString();
        this.publishedAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<Service> CREATOR = new Parcelable.Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel source) {
            return new Service(source);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }
    };
}
