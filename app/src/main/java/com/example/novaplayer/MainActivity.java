package com.example.novaplayer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private final List<Media> videos = new ArrayList<>();
    private final List<Media> audios = new ArrayList<>();
    private final List<Media> mediasAffiches = new ArrayList<>();
    private MediaAdapter adaptateur;
    private RecyclerView listMedias;
    private TextView textAucunMedia;


    private final ActivityResultLauncher<String[]>
            demandePermissions = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(),
            resultat -> {
                Boolean autorise = null;
                for (String permission : resultat.keySet()) {
                    autorise = resultat.get(permission);

                    Log.d("Novaplayer", permission + " : " + autorise);
                }

                if (accesVideosAutorise()) {
                    TabLayout onglets = findViewById(R.id.barre_onglets);

                    if (onglets.getSelectedTabPosition() == 1) {
                        chargerVideos();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);

        //recuperation des ids
        textAucunMedia = findViewById(R.id.texte_aucun_media);
        listMedias = findViewById(R.id.liste_medias);



        // Création de l'adaptateur
        adaptateur = new MediaAdapter(mediasAffiches);

        // Organisation verticale de la liste
        listMedias.setLayoutManager(
                new LinearLayoutManager(this)
        );

        // Association de l'adaptateur au RecyclerView
        listMedias.setAdapter(adaptateur);
        textAucunMedia.setText(R.string.aucun_recent);
        textAucunMedia.setVisibility(View.VISIBLE);
        listMedias.setVisibility(View.GONE);

        // Ajoute les trois onglets à l'écran
        TabLayout barreOnglets = findViewById(R.id.barre_onglets);
        barreOnglets.addTab(
                barreOnglets.newTab().setText("Récents")
        );
        barreOnglets.addTab(
                barreOnglets.newTab().setText("Vidéos")
        );
        barreOnglets.addTab(
                barreOnglets.newTab().setText("Audios")
        );

        // La detection du changement d'onglet
        barreOnglets.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int position = tab.getPosition();

                        switch (position) {
                            case 0:
                                textAucunMedia.setText(R.string.aucun_recent);
                                listMedias.setVisibility(View.GONE);
                                textAucunMedia.setVisibility(View.VISIBLE);
                                break;
                            case 1:
                                textAucunMedia.setText(R.string.aucune_video);

                                if (accesVideosAutorise()) {
                                    chargerVideos();
                                } else {
                                    textAucunMedia.setVisibility(View.GONE);
                                    listMedias.setVisibility(View.VISIBLE);
                                    demandePermissions();
                                }
                                break;
                            case 2:
                                textAucunMedia.setText(R.string.aucun_audio);

                                if (accesAudiosAutorise()) {
                                    chargerAudios();
                                } else {
                                    textAucunMedia.setVisibility(View.GONE);
                                    listMedias.setVisibility(View.VISIBLE);
                                    demandePermissions();
                                }
                                break;
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                }
        );

        // le tiroire du menu burgeur
        DrawerLayout tiroir = findViewById(R.id.tiroir);
        MaterialToolbar barre = findViewById(R.id.barre_outils);
        barre.setNavigationOnClickListener(
                v -> tiroir.openDrawer(GravityCompat.START)
        );

        // Gestion des marges système
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    /*
        Intent intent = new Intent(
                MainActivity.this,
                LecteurVideoActivity.class
               // LecteurAudioActivity.class
        );

        startActivity(intent);
    */
        demandePermissions();
    }

    private void demandePermissions() {
        demandePermissions.launch(new String[]{
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_AUDIO
        });
    }

    private void chargerVideos() {

        MediaStoreRepository repository =
                new MediaStoreRepository(this);

        List<Media> videosTrouvees = repository.obtenirVideos();

        videos.clear();
        videos.addAll(videosTrouvees);

        mediasAffiches.clear();
        mediasAffiches.addAll(videos);

        adaptateur.notifyDataSetChanged();

        if (videos.isEmpty()) {
            listMedias.setVisibility(View.GONE);
            textAucunMedia.setVisibility(View.VISIBLE);
        } else {
            listMedias.setVisibility(View.VISIBLE);
            textAucunMedia.setVisibility(View.GONE);
        }
    }

    private void chargerAudios(){
        MediaStoreRepository repository =
                new MediaStoreRepository(this);

        List<Media> audioTrouvees = repository.obtenirAudios();

        audios.clear();;
        audios.addAll(audioTrouvees);

        mediasAffiches.clear();
        mediasAffiches.addAll(audios);

        adaptateur.notifyDataSetChanged();

        if (audios.isEmpty()){
            listMedias.setVisibility(View.GONE);
            textAucunMedia.setVisibility(View.VISIBLE);
        } else {
            listMedias.setVisibility(View.VISIBLE);
            textAucunMedia.setVisibility(View.GONE);
        }

    }

    private boolean accesVideosAutorise(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED)
            return true;

        return false;
    }

    private boolean accesAudiosAutorise(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED)
            return true;

        return false;
    }
}