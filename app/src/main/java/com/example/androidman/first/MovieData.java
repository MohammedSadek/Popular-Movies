package com.example.androidman.first;

public class MovieData {

    String imageSource;
    String title;
    String overview;
    String vote;
    String key;
    String id;
    String relaseDate;
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRelaseDate() {
        return relaseDate;
    }

    public void setRelaseDate(String relaseDate) {
        this.relaseDate = relaseDate;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote() {
        return vote;
    }
    public void setVote(String vote) {
        this.vote = vote;
    }
    public String getImageSource() {
        return imageSource;
    }
    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
}
