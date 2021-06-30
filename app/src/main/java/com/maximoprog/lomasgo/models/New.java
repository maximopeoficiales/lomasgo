
package com.maximoprog.lomasgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class New implements Parcelable {
    @Override
    public String toString() {
        return "New{" +
                "content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", id=" + id +
                ", image=" + image +
                ", publishedAt='" + publishedAt + '\'' +
                ", summary='" + summary + '\'' +
                ", title='" + title + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    @Expose
    private String content;
    @SerializedName("created_at")
    private String createdAt;
    @Expose
    private Long id;
    @Expose
    private Image image;
    @SerializedName("published_at")
    private String publishedAt;
    @Expose
    private String summary;
    @Expose
    private String title;
    @SerializedName("updated_at")
    private String updatedAt;

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public Image getImage() {
        return image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeString(this.createdAt);
        dest.writeValue(this.id);
        dest.writeParcelable(this.image, flags);
        dest.writeString(this.publishedAt);
        dest.writeString(this.summary);
        dest.writeString(this.title);
        dest.writeString(this.updatedAt);
    }

    public void readFromParcel(Parcel source) {
        this.content = source.readString();
        this.createdAt = source.readString();
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.image = source.readParcelable(Image.class.getClassLoader());
        this.publishedAt = source.readString();
        this.summary = source.readString();
        this.title = source.readString();
        this.updatedAt = source.readString();
    }

    protected New(Parcel in) {
        this.content = in.readString();
        this.createdAt = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.image = in.readParcelable(Image.class.getClassLoader());
        this.publishedAt = in.readString();
        this.summary = in.readString();
        this.title = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<New> CREATOR = new Parcelable.Creator<New>() {
        @Override
        public New createFromParcel(Parcel source) {
            return new New(source);
        }

        @Override
        public New[] newArray(int size) {
            return new New[size];
        }
    };
}
