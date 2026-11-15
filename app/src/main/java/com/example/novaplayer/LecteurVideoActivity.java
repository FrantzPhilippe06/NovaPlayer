package com.example.novaplayer;

import android.os.Bundle;
import android.widget.VideoView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

/**
 * Activité responsable de l'interface du lecteur vidéo.
 *
 * Cette classe prépare les composants graphiques
 * du lecteur sans lancer la lecture.
 */
public class LecteurVideoActivity extends AppCompatActivity {

    // Composants de l'interface
    private VideoView vueVideo;
    private SeekBar seekbar;
    private TextView titreVideo;
    private TextView tempsActuel;
    private TextView tempsTotal;
    private Button boutonLecture;
    private Button boutonPause;
    private Button boutonArret;
    private Button boutonAvancer;
    private Button boutonReculer;
    private SwitchCompat optionRepetition;
    private SwitchCompat optionEcranAllumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lecteur_video);

        initialiserVues();
    }

    private void initialiserVues() {

        vueVideo = findViewById(R.id.vue_video);
        seekbar = findViewById(R.id.seekbar);

        titreVideo = findViewById(R.id.titre_video);
        tempsActuel = findViewById(R.id.temps_actuel);
        tempsTotal = findViewById(R.id.temps_total);

        boutonLecture = findViewById(R.id.bouton_lecture);
        boutonPause = findViewById(R.id.bouton_pause);
        boutonArret = findViewById(R.id.bouton_arret);
        boutonAvancer = findViewById(R.id.bouton_avancer);
        boutonReculer = findViewById(R.id.bouton_reculer);

        optionRepetition = findViewById(R.id.option_repetition);
        optionEcranAllumer = findViewById(R.id.option_ecran_allumer);
    }
}
