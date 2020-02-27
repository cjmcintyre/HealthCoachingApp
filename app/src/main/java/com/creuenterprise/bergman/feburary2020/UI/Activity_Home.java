package com.creuenterprise.bergman.feburary2020.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.creuenterprise.bergman.feburary2020.R;
import com.creuenterprise.bergman.feburary2020.UI.DB.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Activity_Home extends AppCompatActivity implements View.OnClickListener{

    EditText editTextName;
    TextView textEmail,textPhoneNumber,textEmailVerified;
    ProgressBar progressBar;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__home);

        editTextName = findViewById(R.id.edit_text_name);
        textEmail = findViewById(R.id.text_email);
        //textPhoneNumber = findViewById(R.id.text_phone);
        textEmailVerified = findViewById(R.id.text_not_verified);
        progressBar = findViewById(R.id.progressbar);

        findViewById(R.id.text_not_verified).setOnClickListener(this);
        findViewById(R.id.button_save).setOnClickListener(this);

        if(user!=null)
        {
            editTextName.setText(user.getDisplayName());
            textEmail.setText(user.getEmail());
            //textPhoneNumber.setText((user.getPhoneNumber().isEmpty()) ? "Add Number" : user.getPhoneNumber());

            if(user.isEmailVerified())
                textEmailVerified.setVisibility(View.INVISIBLE);
            else
                textEmailVerified.setVisibility(View.VISIBLE);
        }

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
            case (R.id.button_save):
                save();
                break;
            case (R.id.text_not_verified):
                sendEmailConfirmation();
                break;
        }
    }

    private void sendEmailConfirmation()
    {
        progressBar.setVisibility(View.VISIBLE);
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(Activity_Home.this,
                            "Verification Email Sent", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(Activity_Home.this,
                            "Something Went Wrong! " + task.getException(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void save()
    {
        try {
            String name = editTextName.getText().toString().trim();
            if (name.isEmpty()) {
                editTextName.setError("Name Required");
                editTextName.requestFocus();
                return;
            }


            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build();

            progressBar.setVisibility(View.VISIBLE);
            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Activity_Home.this,
                                "Profile Updated", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    } else {
                        Toast.makeText(Activity_Home.this,
                                "Something Went Wrong! " + task.getException(), Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }catch(Exception e) {}
    }

    /*********************************
     * Creates top menu / checks touch
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_my_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            //LOGOUT
            case R.id.action_add_contact:
                FirebaseAuth.getInstance().signOut();

                Intent i = new Intent(this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                overridePendingTransition(0,0);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
