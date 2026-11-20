package com.example.novaplayer;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class MediaStoreRepository {

    private final ContentResolver resolver;
    public MediaStoreRepository(Context context){
        resolver = context.getContentResolver();
    }

    public List<Media> obtenirVideo(){
        List<Media> videos = new ArrayList<>();

        Uri uriVideo = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] colonnes = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME
        };

        Cursor curseur = resolver.query(
                uriVideo,
                colonnes,
                null,
                null,
                null
        );

        try{
            int colonneId = curseur.getColumnIndexOrThrow(
                    MediaStore.Video.Media._ID
            );
            int colonneNom = curseur.getColumnIndexOrThrow(
                    MediaStore.Video.Media.DISPLAY_NAME
            );

            while (curseur.moveToNext()){
                long id = curseur.getLong(colonneId);
                String nom = curseur.getString(colonneNom);
                Uri uri = ContentUris.withAppendedId(uriVideo,id);

                Media video = new Media(nom,"video",uri,true);
                videos.add(video);
            }
        }finally {
            curseur.close();
        }
        return videos;
    }
}
