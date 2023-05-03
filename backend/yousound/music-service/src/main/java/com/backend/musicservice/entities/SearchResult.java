package com.backend.musicservice.entities;

public class SearchResult {

    private String name;
    private String picture;

    private String type;

    public SearchResult(String name, String picture, String type) {
        this.name = name;
        this.picture = picture;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
