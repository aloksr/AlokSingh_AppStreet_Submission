package com.appstreettaskapplication.model;

import android.os.Parcel;
import android.os.Parcelable;



public class ListResponseModel implements Parcelable {
    private String username;
    private String name;
    private String type;
    private String url;
    private String avatar;
    private RepoModel repo;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRepo(RepoModel repo) {
        this.repo = repo;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getAvatar() {
        return avatar;
    }

    public RepoModel getRepo() {
        return repo;
    }

    public ListResponseModel(){

    }

    protected ListResponseModel(Parcel in) {
        username = in.readString();
        name = in.readString();
        type = in.readString();
        url = in.readString();
        avatar = in.readString();
        repo = in.readParcelable(RepoModel.class.getClassLoader());
    }

    public static final Creator<ListResponseModel> CREATOR = new Creator<ListResponseModel>() {
        @Override
        public ListResponseModel createFromParcel(Parcel in) {
            return new ListResponseModel(in);
        }

        @Override
        public ListResponseModel[] newArray(int size) {
            return new ListResponseModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(name);
        parcel.writeString(type);
        parcel.writeString(url);
        parcel.writeString(avatar);
        parcel.writeParcelable(repo, i);
    }
}
