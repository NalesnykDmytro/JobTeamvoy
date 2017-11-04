package com.example.dima.jobteamvoy;

import java.util.Date;

public class PhotoInfo {

    String name;
    Date date;
    int like;
    int unlike;
    int photoId;

    PhotoInfo(String name, int like, int unlike, Date date, int photoId){
        this.name = name;
        this.like = like;
        this.unlike = unlike;
        this.date = date;
        this.photoId = photoId;
    }

}
