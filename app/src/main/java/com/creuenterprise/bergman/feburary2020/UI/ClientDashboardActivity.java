package com.creuenterprise.bergman.feburary2020.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.creuenterprise.bergman.feburary2020.R;

public class ClientDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_dashboard);

        Intent secondIntent = getIntent( );
        String message = "Selected course is " + secondIntent.getStringExtra("COURSE_SELECTED");
        TextView myText = findViewById(R.id.textView);
        myText.setText(message);
    }
}
