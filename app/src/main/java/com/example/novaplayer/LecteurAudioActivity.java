
package com.example.novaplayer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class LecteurAudioActivity extends AppCompatActivity {

    private ImageView imageAudio;
    private TextView titreAudio;
    private TextView artisteAudio;
    private TextView tempsActuel;
    private TextView tempsTotal;
    private SeekBar seekbar;
    private Button boutonLecture;
    private Button boutonPause;
    private Button boutonArret;
    private Button boutonAvancer;
    private Button boutonReculer;
    private SwitchCompat optionRepetition;
    private SwitchCompat optionEcranAllume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lecteur_audio);

        initialiserVues();
    }

    private void initialiserVues() {

        imageAudio = findViewById(R.id.image_audio);

        titreAudio = findViewById(R.id.titre_audio);
        artisteAudio = findViewById(R.id.artiste_audio);

        tempsActuel = findViewById(R.id.temps_actuel_audio);
        tempsTotal = findViewById(R.id.temps_total_audio);

        seekbar = findViewById(R.id.seekbar_audio);

        boutonLecture = findViewById(R.id.bouton_lecture_audio);
        boutonPause = findViewById(R.id.bouton_pause_audio);
        boutonArret = findViewById(R.id.bouton_arret_audio);
        boutonAvancer = findViewById(R.id.bouton_avancer_audio);
        boutonReculer = findViewById(R.id.bouton_reculer_audio);

        optionRepetition = findViewById(R.id.option_repetition_audio);
        optionEcranAllume = findViewById(R.id.option_ecran_allume_audio);
    }
}
