
package com.maximoprog.lomasgo.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class New {

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

    public static class Builder {

        private String content;
        private String createdAt;
        private Long id;
        private Image image;
        private String publishedAt;
        private String summary;
        private String title;
        private String updatedAt;

        public New.Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public New.Builder withCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public New.Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public New.Builder withImage(Image image) {
            this.image = image;
            return this;
        }

        public New.Builder withPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
            return this;
        }

        public New.Builder withSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public New.Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public New.Builder withUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public New build() {
            New new = new New();
            new.content = content;
            new.createdAt = createdAt;
            new.id = id;
            new.image = image;
            new.publishedAt = publishedAt;
            new.summary = summary;
            new.title = title;
            new.updatedAt = updatedAt;
            return new;
        }

    }

}
