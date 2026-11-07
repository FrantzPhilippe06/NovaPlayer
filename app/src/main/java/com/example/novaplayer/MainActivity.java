package com.example.novaplayer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.*;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);

        //recuperation des ids
        TextView textAucunMedia = findViewById(R.id.texte_aucun_media);
        RecyclerView listMedias = findViewById(R.id.liste_medias);


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

                        switch (position){
                            case 0:
                                textAucunMedia.setText(R.string.aucun_recent);
                                break;
                            case 1:
                                textAucunMedia.setText(R.string.aucune_video);
                                break;
                            case 2:
                                textAucunMedia.setText(R.string.aucun_audio);
                                break;
                        }
                        listMedias.setVisibility(View.GONE);
                        textAucunMedia.setVisibility(View.VISIBLE);
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
    }
}