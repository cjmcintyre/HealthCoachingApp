package com.creuenterprise.bergman.feburary2020.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.creuenterprise.bergman.feburary2020.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        /*******************************
         * Bottom Nav Bar
         */
        //Initialize List and set value
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Set Dashboard Selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {
                    case R.id.dashboard:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,Activity_Home.class));
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
        /*********************************************************************/
    }
}
