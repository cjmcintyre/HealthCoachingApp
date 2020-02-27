package com.creuenterprise.bergman.feburary2020.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.creuenterprise.bergman.feburary2020.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity_clientlist extends AppCompatActivity {

    ListView clientList;
    Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientlist);

        //Initialize
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        clientList = findViewById(R.id.clientlistview);
        myIntent = new Intent(this, ClientDashboardActivity.class);

        /*******************************
         * Bottom Nav Bar
         */
        bottomNavigationView.setSelectedItemId(R.id.clients);
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
                                ,Activity_Home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.clients:
                        return true;
                }
                return false;
            }
        });

        /*******************************
         * ListView
         */
        String[ ] myData = {"Visual Basic .NET", "Java", "Android", "C# .NET",
                "PHP", "C++", "Scala", "Ruby on Rails", "Javascript", "HTML", "Python", "Swift"
                ,"other","other2","other3","other4"};

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, myData );
        clientList.setAdapter(myAdapter);
        clientList.setOnItemClickListener( listClick );
    }

    /*********************************
     * Creates top menu / checks touch
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_my_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.action_add_contact:
                startActivity(new Intent(this, activity_createclient.class));
                overridePendingTransition(0,0);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*******************************
     *
     */
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener ()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            String itemValue = (String) clientList.getItemAtPosition( position );
            myIntent.putExtra("COURSE_SELECTED", itemValue); //key, data
            startActivity(myIntent);
            overridePendingTransition(0,0);
        }
    };
}
