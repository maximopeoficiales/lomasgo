
package com.maximoprog.lomasgo.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Info {

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

}
