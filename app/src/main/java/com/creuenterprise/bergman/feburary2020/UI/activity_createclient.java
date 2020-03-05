package com.creuenterprise.bergman.feburary2020.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.creuenterprise.bergman.feburary2020.R;
import com.creuenterprise.bergman.feburary2020.UI.DB.LoginActivity;
import com.creuenterprise.bergman.feburary2020.UTILS.Client;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class activity_createclient extends AppCompatActivity implements View.OnClickListener{

    EditText fname,lname,fitnesslink;
    Button create;
    ProgressBar progressBar;
    DatabaseReference databaseClients;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createclient);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseClients = FirebaseDatabase.getInstance().getReference("clients").child(user.getUid());

        progressBar = findViewById(R.id.progressbar);
        fname = findViewById(R.id.text_firstname);
        lname = findViewById(R.id.text_lastname);
        fitnesslink = findViewById(R.id.text_myfitnesspallink);
        create = findViewById(R.id.button_save);
        findViewById(R.id.button_save).setOnClickListener(this);

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

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case(R.id.button_save):
                progressBar.setVisibility(View.VISIBLE);
                saveClient();
                break;
        }
    }

    public void saveClient()
    {
        String firstname = fname.getText().toString().trim();
        String lastname = lname.getText().toString().trim();
        String myfitnesslink = fitnesslink.getText().toString().trim();

        if(!TextUtils.isEmpty(firstname) && !TextUtils.isEmpty(lastname)){

            String id = databaseClients.push().getKey(); //create a unique string ID
            Client client = new Client(id,firstname,lastname,myfitnesslink);
            databaseClients.child(id).setValue(client);

            Toast.makeText(this,"Client Added", Toast.LENGTH_LONG).show();

        }else if(TextUtils.isEmpty(firstname)){
            fname.setError("first name required");
            fname.requestFocus();
        }
        else if(TextUtils.isEmpty(lastname)){
            lname.setError("last name required");
            lname.requestFocus();
        }

        progressBar.setVisibility(View.INVISIBLE);
    }
}
