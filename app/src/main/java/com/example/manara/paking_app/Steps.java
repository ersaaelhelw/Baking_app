package com.example.manara.paking_app;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable {

    String shortshortDescription,description,videoURL;
    String image;

    public Steps() {

    }


    protected Steps(Parcel in) {
        shortshortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shortshortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortshortDescription() {
        return shortshortDescription;
    }


    public void setShortshortDescription(String shortshortDescription) {
        this.shortshortDescription = shortshortDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
