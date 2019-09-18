package com.fedorrroff.tmdbportable.models.data;

public class MovieItem {

    private String description;
    private String title;
    private String avatar;

    public MovieItem(String description, String title, String avatar) {
        this.description = description;
        this.title = title;
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
