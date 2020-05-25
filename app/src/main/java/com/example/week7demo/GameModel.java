package com.example.week7demo;

public class GameModel {

    String category,title,website,score;
    String image;

    public GameModel(String category, String title, String website, String score, String image) {
        this.category = category;
        this.title = title;
        this.website = website;
        this.score = score;
        this.image = image;
    }

    public GameModel() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
