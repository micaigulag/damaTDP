package com.example.dama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView nav = findViewById(R.id.navigation);
        nav.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_24);
        drawerLayout = findViewById(R.id.drawerLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_bar, menu);
        return true;
    }

    // risposta ai clic del menu sulla barra dell'app (in alto)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ( item.getItemId() == android.R.id.home ) {
            drawerLayout.openDrawer(Gravity.LEFT);
        } else if (item.getItemId() == R.id.settings) {
            startActivity( new Intent(this, settings.class));
        }

        return true;
    }

    // risposta ai clic sul drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {



        if(menuItem.getItemId() == R.id.info){
            startActivity(new Intent(this, info.class));

        }


        return true;
    }

    public void creaClk(View v) {
        Intent intent = new Intent(this, crea.class);
        startActivity(intent);
    }

    public void partecipaClk(View v) {
        Intent intent = new Intent(this, partecipa.class);
        startActivity(intent);
    }
    public void creaCampoPartita(View v) {
        Intent intent = new Intent(this, gameActivity.class);
        startActivity(intent);
    }




}
