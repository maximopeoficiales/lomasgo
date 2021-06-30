
package com.maximoprog.lomasgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Info implements Parcelable {

    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("email_creator")
    private String emailCreator;
    @SerializedName("history_content")
    private String historyContent;
    @Expose
    private Long id;
    @SerializedName("image_site")
    private ImageSite imageSite;
    @Expose
    private String latitude;
    @Expose
    private String longitude;
    @SerializedName("name_site")
    private String nameSite;
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

    public String getEmailCreator() {
        return emailCreator;
    }

    public void setEmailCreator(String emailCreator) {
        this.emailCreator = emailCreator;
    }

    public String getHistoryContent() {
        return historyContent;
    }

    public void setHistoryContent(String historyContent) {
        this.historyContent = historyContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImageSite getImageSite() {
        return imageSite;
    }

    public void setImageSite(ImageSite imageSite) {
        this.imageSite = imageSite;
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

    public String getNameSite() {
        return nameSite;
    }

    public void setNameSite(String nameSite) {
        this.nameSite = nameSite;
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
        dest.writeString(this.emailCreator);
        dest.writeString(this.historyContent);
        dest.writeValue(this.id);
        dest.writeParcelable(this.imageSite, flags);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeString(this.nameSite);
        dest.writeString(this.publishedAt);
        dest.writeString(this.updatedAt);
    }

    public void readFromParcel(Parcel source) {
        this.createdAt = source.readString();
        this.emailCreator = source.readString();
        this.historyContent = source.readString();
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.imageSite = source.readParcelable(ImageSite.class.getClassLoader());
        this.latitude = source.readString();
        this.longitude = source.readString();
        this.nameSite = source.readString();
        this.publishedAt = source.readString();
        this.updatedAt = source.readString();
    }

    public Info() {
    }

    protected Info(Parcel in) {
        this.createdAt = in.readString();
        this.emailCreator = in.readString();
        this.historyContent = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.imageSite = in.readParcelable(ImageSite.class.getClassLoader());
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.nameSite = in.readString();
        this.publishedAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<Info> CREATOR = new Parcelable.Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel source) {
            return new Info(source);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
}
