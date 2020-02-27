package com.creuenterprise.bergman.feburary2020.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.creuenterprise.bergman.feburary2020.R;
import com.creuenterprise.bergman.feburary2020.UI.DB.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class activity_createclient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createclient);

        /*******************************
         * Bottom Nav Bar
         */
        //Initialize List and set value
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext()
                                , DashboardActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , LoginActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.clients:
                        startActivity(new Intent(getApplicationContext()
                                ,Activity_clientlist.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
