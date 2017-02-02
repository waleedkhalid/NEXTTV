package com.example.waleed.firebasestartup;

/**
 * Created by Waleed on 27/01/2017.
 */

public class ListItem {

    private String header;
    private String desc;
    private String imageUrl;


    public ListItem(String header, String desc, String imageUrl) {
        this.header = header;
        this.desc = desc;
        this.imageUrl = imageUrl;
    }

    public String getHeader() {
        return header;
    }

    public String  getDesc() {
        return desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
