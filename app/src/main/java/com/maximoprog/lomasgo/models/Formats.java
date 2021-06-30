
package com.maximoprog.lomasgo.models;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Formats implements Parcelable {

    @Expose
    private Large large;
    @Expose
    private Medium medium;
    @Expose
    private Small small;
    @Expose
    private Thumbnail thumbnail;

    public Large getLarge() {
        return large;
    }

    public void setLarge(Large large) {
        this.large = large;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Small getSmall() {
        return small;
    }

    public void setSmall(Small small) {
        this.small = small;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.large, flags);
        dest.writeParcelable(this.medium, flags);
        dest.writeParcelable(this.small, flags);
        dest.writeParcelable(this.thumbnail, flags);
    }

    public void readFromParcel(Parcel source) {
        this.large = source.readParcelable(Large.class.getClassLoader());
        this.medium = source.readParcelable(Medium.class.getClassLoader());
        this.small = source.readParcelable(Small.class.getClassLoader());
        this.thumbnail = source.readParcelable(Thumbnail.class.getClassLoader());
    }

    public Formats() {
    }

    protected Formats(Parcel in) {
        this.large = in.readParcelable(Large.class.getClassLoader());
        this.medium = in.readParcelable(Medium.class.getClassLoader());
        this.small = in.readParcelable(Small.class.getClassLoader());
        this.thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
    }

    public static final Parcelable.Creator<Formats> CREATOR = new Parcelable.Creator<Formats>() {
        @Override
        public Formats createFromParcel(Parcel source) {
            return new Formats(source);
        }

        @Override
        public Formats[] newArray(int size) {
            return new Formats[size];
        }
    };
}
