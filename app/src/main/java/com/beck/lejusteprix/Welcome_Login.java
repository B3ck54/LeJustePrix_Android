package com.beck.lejusteprix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Welcome_Login extends Activity implements View.OnClickListener {

    Button bLogin;
    Button bDejaEnregistre;
    EditText etUsername;
    Button scoreResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        bLogin = (Button) findViewById(R.id.bLogin);
        scoreResult = (Button) findViewById(R.id.scoreFinal);

        bLogin.setOnClickListener(this);
        scoreResult.setOnClickListener(this);

    }

    @Override
    public void onClick (View v){
        if (bLogin == v) {
                SharedPreferencesUtils.sauvegarderNom(this,etUsername.getText().toString());
                Toast.makeText(this,"Nom Sauvegardé", Toast.LENGTH_SHORT).show();
                openGame();
            } else {
            quitGame();
        }

    }

    private void openGame() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    public void quitGame(){
        finish();
    }


//    public void result() {
//        startActivity(new Intent(this, Result.class));
//    }
}




