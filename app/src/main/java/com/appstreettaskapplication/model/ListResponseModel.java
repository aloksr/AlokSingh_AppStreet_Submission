package com.appstreettaskapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListResponseModel {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("repo")
    @Expose
    private RepoModel repo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public RepoModel getRepo() {
        return repo;
    }

    public void setRepo(RepoModel repo) {
        this.repo = repo;
    }
}
