
package com.maximoprog.lomasgo.models;

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
public class New {
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

}
