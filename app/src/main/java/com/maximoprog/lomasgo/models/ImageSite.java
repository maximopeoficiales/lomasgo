
package com.maximoprog.lomasgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ImageSite implements Parcelable {

    @Expose
    private String alternativeText;
    @Expose
    private String caption;
    @SerializedName("created_at")
    private String createdAt;
    @Expose
    private String ext;
    @Expose
    private Formats formats;
    @Expose
    private String hash;
    @Expose
    private Long height;
    @Expose
    private Long id;
    @Expose
    private String mime;
    @Expose
    private String name;
    @Expose
    private String previewUrl;
    @Expose
    private String provider;
    @SerializedName("provider_metadata")
    private String providerMetadata;
    @Expose
    private Double size;
    @SerializedName("updated_at")
    private String updatedAt;
    @Expose
    private String url;
    @Expose
    private Long width;

    public String getAlternativeText() {
        return alternativeText;
    }

    public void setAlternativeText(String alternativeText) {
        this.alternativeText = alternativeText;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Formats getFormats() {
        return formats;
    }

    public void setFormats(Formats formats) {
        this.formats = formats;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderMetadata() {
        return providerMetadata;
    }

    public void setProviderMetadata(String providerMetadata) {
        this.providerMetadata = providerMetadata;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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
        dest.writeString(this.alternativeText);
        dest.writeString(this.caption);
        dest.writeString(this.createdAt);
        dest.writeString(this.ext);
        dest.writeParcelable(this.formats, flags);
        dest.writeString(this.hash);
        dest.writeValue(this.height);
        dest.writeValue(this.id);
        dest.writeString(this.mime);
        dest.writeString(this.name);
        dest.writeString(this.previewUrl);
        dest.writeString(this.provider);
        dest.writeString(this.providerMetadata);
        dest.writeValue(this.size);
        dest.writeString(this.updatedAt);
        dest.writeString(this.url);
        dest.writeValue(this.width);
    }

    public void readFromParcel(Parcel source) {
        this.alternativeText = source.readString();
        this.caption = source.readString();
        this.createdAt = source.readString();
        this.ext = source.readString();
        this.formats = source.readParcelable(Formats.class.getClassLoader());
        this.hash = source.readString();
        this.height = (Long) source.readValue(Long.class.getClassLoader());
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.mime = source.readString();
        this.name = source.readString();
        this.previewUrl = source.readString();
        this.provider = source.readString();
        this.providerMetadata = source.readString();
        this.size = (Double) source.readValue(Double.class.getClassLoader());
        this.updatedAt = source.readString();
        this.url = source.readString();
        this.width = (Long) source.readValue(Long.class.getClassLoader());
    }

    public ImageSite() {
    }

    protected ImageSite(Parcel in) {
        this.alternativeText = in.readString();
        this.caption = in.readString();
        this.createdAt = in.readString();
        this.ext = in.readString();
        this.formats = in.readParcelable(Formats.class.getClassLoader());
        this.hash = in.readString();
        this.height = (Long) in.readValue(Long.class.getClassLoader());
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.mime = in.readString();
        this.name = in.readString();
        this.previewUrl = in.readString();
        this.provider = in.readString();
        this.providerMetadata = in.readString();
        this.size = (Double) in.readValue(Double.class.getClassLoader());
        this.updatedAt = in.readString();
        this.url = in.readString();
        this.width = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<ImageSite> CREATOR = new Parcelable.Creator<ImageSite>() {
        @Override
        public ImageSite createFromParcel(Parcel source) {
            return new ImageSite(source);
        }

        @Override
        public ImageSite[] newArray(int size) {
            return new ImageSite[size];
        }
    };
}
