package com.beck.lejusteprix;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import static com.beck.lejusteprix.SharedPreferencesUtils.CleNom;
import static com.beck.lejusteprix.SharedPreferencesUtils.GAME_DATA;


public class Game extends AppCompatActivity {

    //DBManager myDb;

    private EditText txtNumber;
    private TextView txtIndication;
    private TextView txtHistorique;
    private TextView txtNbCoup;
    private Chronometer chronometer;
    private Button btnValider;

    private int valeurAtrouver;
    private int score;

    private String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des pointeurs //
        txtNumber = (EditText) findViewById(R.id.txtNumber);
        btnValider = (Button) findViewById(R.id.btnValider);
        txtIndication = (TextView) findViewById(R.id.txtIndication);
        txtHistorique = (TextView) findViewById(R.id.txtHistorique);
        txtNbCoup = (TextView) findViewById(R.id.txtNbCoup);
        chronometer = (Chronometer)findViewById(R.id.chronometer);


        btnValider.setOnClickListener(btnValiderListener);

        init();
    }


    // Initialisation de la partie //
    private void init() {
        score = 0;
        valeurAtrouver = 1 + (int) (Math.random() * 100);
        Log.i("DEBUG", "Valeur cherchée ++++++++++++++++++++++++++++" + valeurAtrouver);

        txtNumber.setText("");
        txtIndication.setText("");
        txtHistorique.setText("");
        txtNbCoup.setText("0");
        txtNumber.requestFocus();
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

    }


    private void congratulations(){
        txtIndication.setText(getString(R.string.strGagne));
        // Récupération du temps du chronometre
        long time = SystemClock.elapsedRealtime()-chronometer.getBase();
        int timer = (int) time / 1000;
        chronometer.stop();

        // Boite de dialogue pour recommencer une partie //
        //recupération du nom dans le GameData
        name = SharedPreferencesUtils.recupererNom(this);

        Log.i("DEBUG", "Valeur cherchée ++++++++++++++++++++++++++++" + name);

        final AlertDialog retryAlert = new AlertDialog.Builder(this).create();
        //construit un builder à partir de notre activité (this)
        retryAlert.setTitle(R.string.app_name);

        retryAlert.setMessage(getString(R.string.strMessage,name,score,timer));
        //%d injecte score à cet endroit

        // Ajout des 2 boutons
        retryAlert.setButton(AlertDialog.BUTTON_POSITIVE,"RECOMMENCER",
                new AlertDialog.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                init(); // si ok on redémarre l'appli
            }
        });

        retryAlert.setButton(AlertDialog.BUTTON_NEGATIVE,"QUITTER",
                new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // si non on ferme l'appli
            }
        });
        retryAlert.show();
    }


    // Ecoute bouton //
    private View.OnClickListener btnValiderListener = new View.OnClickListener() { // classe anonyme pour produire une seule et même instance
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            Log.i("DEBUG", "Bouton cliqué ++++++++++++++++++++++++++++++++++++++++++");


            // Récupération de la valeur saisie //
            String strNumber = txtNumber.getText().toString(); //récupération de la valeur saisie


            if (strNumber.equals(""))return;

                txtHistorique.append(strNumber + " ");
                // retour de la valeur saisie dans l'historique
            Log.i("DEBUG", "MON HISTORIQUE ++++++++++++++++++++++++++++" + txtHistorique);

            score++;


            int valeurSaisie = Integer.parseInt(strNumber);
            // parseInt de la valeur que nous avons renseignée

            if (valeurSaisie == valeurAtrouver) {
                congratulations();
            } else if (valeurSaisie < valeurAtrouver) {
                txtIndication.setText(getString(R.string.Greater));
            } else {
                txtIndication.setText(getString(R.string.Lower));
            }


            txtNumber.setText("");// remettre à blanc la zone de saisie à chaque saisie
            txtNumber.requestFocus();
            txtNbCoup.setText("" + score); // ajouter le score/nb de coup dans textView : txtScore
            chronometer.start();
        }
    };





}
