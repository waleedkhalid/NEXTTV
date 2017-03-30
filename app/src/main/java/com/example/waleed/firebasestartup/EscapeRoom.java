package com.example.waleed.firebasestartup;

/**
 * Created by aruffolo on 7/13/16.
 */
public class EscapeRoom {

    private String Title;
    private String Director;
    private String MovieUrl;
    private String ThumbUrl;



    public EscapeRoom() {

    }

    public EscapeRoom(String Title, String Director, String MovieUrl, String ThumbUrl){
        this.Title = Title;
        this.Director = Director;
        this.MovieUrl = MovieUrl;
        this.ThumbUrl = ThumbUrl;

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {this.Title = Title;}

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director= Director;
    }

    public String getMovieUrl() {
        return MovieUrl;
    }

    public void setMovieUrl(String MovieUrl) {
        this.MovieUrl = MovieUrl;
    }

    public String getThumbUrl() {
        return ThumbUrl;
    }

    public void setThumbUrl(String ThumbUrl) {
        this.ThumbUrl = ThumbUrl;
    }
}
