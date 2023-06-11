package com.example.mini_projet_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView tv_detailsFistName, tv_detailsLastName, tv_detailsGender, tv_detailsCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv_detailsFistName = findViewById(R.id.tv_detailsFirstName);
        tv_detailsLastName = findViewById(R.id.tv_detailsLastName);
        tv_detailsGender = findViewById(R.id.tv_detailsGender);
        tv_detailsCity = findViewById(R.id.tv_detailsCity);

        String fn = getIntent().getStringExtra("firstName");
        String ln = getIntent().getStringExtra("lastName");
        String gender = getIntent().getStringExtra("gender");
        String city = getIntent().getStringExtra("city");

        tv_detailsFistName.setText(fn);
        tv_detailsLastName.setText(ln);
        tv_detailsGender.setText(gender);
        tv_detailsCity.setText(city);
    }
}