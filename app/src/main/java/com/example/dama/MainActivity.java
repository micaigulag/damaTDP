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
import android.widget.Toast;

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
        }
        return true;
    }

    // risposta ai clic sul drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
       /*  trasfore in if
        switch (menuItem.getItemId()) {
            case R.id.area1:
                startActivity( new Intent(this,Activity2.class));
                break;
            case R.id.area2:
                startActivity( new Intent(this,Activity2.class));
                break;
            case R.id.area3:
                startActivity( new Intent(this,Activity2.class));
                break;
        }*/


        return true;
    }

}
