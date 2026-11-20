package com.example.novaplayer;

import android.net.Uri;

public class Media {
     private final String titre;
     private final String detail;
     private final Uri uri;
     private final boolean video;

    public Media(String titre, String detail, Uri uri, boolean video) {
        this.titre = titre;
        this.detail = detail;
        this.uri = uri;
        this.video = video;
    }

}
