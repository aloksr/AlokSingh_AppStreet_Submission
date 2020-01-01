package com.appstreettaskapplication.model;


import android.os.Parcel;
import android.os.Parcelable;

public class RepoModel implements Parcelable {
    private String name;
    private String description;

    private String url;

    protected RepoModel(Parcel in) {
        name = in.readString();
        description = in.readString();
        url = in.readString();
    }

    public static final Creator<RepoModel> CREATOR = new Creator<RepoModel>() {
        @Override
        public RepoModel createFromParcel(Parcel in) {
            return new RepoModel(in);
        }

        @Override
        public RepoModel[] newArray(int size) {
            return new RepoModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(url);
    }
}
