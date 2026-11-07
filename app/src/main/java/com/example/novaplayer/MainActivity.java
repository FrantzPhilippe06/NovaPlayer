package com.example.novaplayer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.*;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);

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

        DrawerLayout tiroir = findViewById(R.id.tiroir);
        MaterialToolbar barre = findViewById(R.id.barre_outils);
        barre.setNavigationOnClickListener(
                v -> tiroir.openDrawer(GravityCompat.START)
        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}