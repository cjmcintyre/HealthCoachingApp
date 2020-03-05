package com.creuenterprise.bergman.feburary2020.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.le.AdvertiseData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.creuenterprise.bergman.feburary2020.R;
import com.creuenterprise.bergman.feburary2020.UTILS.Client;
import com.creuenterprise.bergman.feburary2020.UTILS.ClientList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activity_clientlist extends AppCompatActivity {

    ListView listview_clientList;
    Intent myIntent;
    DatabaseReference databaseClients;
    List <Client> ListOfClients;
    private FirebaseUser user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientlist);

        //Initialize
        user = FirebaseAuth.getInstance().getCurrentUser();
        ListOfClients = new ArrayList<>();
        databaseClients = FirebaseDatabase.getInstance().getReference("clients").child(user.getUid());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        listview_clientList = findViewById(R.id.clientlistview);
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



       // ArrayAdapter<String> myAdapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1);
       // listview_clientList.setAdapter(myAdapter);
       // listview_clientList.setOnItemClickListener( listClick );
    }


    @Override
    protected void onStart()
    {
        super.onStart();

        databaseClients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ListOfClients.clear();

                //Iterate through all the objects in the database
                for(DataSnapshot clientSnapshot : dataSnapshot.getChildren())
                {
                    Client client = clientSnapshot.getValue(Client.class);
                    ListOfClients.add(client);
                }

                ClientList adapter = new ClientList(Activity_clientlist.this,ListOfClients);
                listview_clientList.setAdapter(adapter);
                //listview_clientList.setOnItemClickListener( listClick );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
            String itemValue = (String) listview_clientList.getItemAtPosition( position );
            myIntent.putExtra("COURSE_SELECTED", itemValue); //key, data
            startActivity(myIntent);
            overridePendingTransition(0,0);
        }
    };
}
