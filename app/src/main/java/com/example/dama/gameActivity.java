package com.example.dama;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class gameActivity extends AppCompatActivity  {
    private campoDiGioco campo;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.partita2);
        campo = (campoDiGioco) findViewById(R.id.GameField);
        campo.settingTextView( (TextView)findViewById(R.id.messaggiDiGioco) );
        campo.settingMessageView( (TextView)findViewById(R.id.indicatoreTurni) );
    }
}
