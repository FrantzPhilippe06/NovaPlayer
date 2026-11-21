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

    public List<Media> obtenirVideos(){
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

        if (curseur == null) {
            return videos;
        }

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

    public List<Media> obtenirAudios(){
        List<Media> audios = new ArrayList<>();

        Uri uriAudio = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] colonnes = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME
        };

        Cursor curseur = resolver.query(uriAudio, colonnes, null, null, null);

        if (curseur == null){
            return audios;
        }

        try{
            int colonneId = curseur.getColumnIndexOrThrow(
                    MediaStore.Audio.Media._ID
            );
            int colonneNom = curseur.getColumnIndexOrThrow(
                    MediaStore.Audio.Media.DISPLAY_NAME
            );

            while (curseur.moveToNext()){
                long id = curseur.getLong(colonneId);
                String nom = curseur.getString(colonneNom);
                Uri uri = ContentUris.withAppendedId(uriAudio,id);

                Media video = new Media(nom,"Audio",uri,false);
                audios.add(video);
            }
        }finally {
            curseur.close();
        }
        return audios;
    }
}
