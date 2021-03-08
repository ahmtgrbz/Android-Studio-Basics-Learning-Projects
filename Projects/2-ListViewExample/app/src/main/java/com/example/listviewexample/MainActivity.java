package com.example.listviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnArrayAdapter = findViewById(R.id.button_ArrayAdapter);

        btnArrayAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ArrayAdapterActivity.class);
                startActivity(intent);
            }
        });

        Button btnCustomAdapter = findViewById(R.id.button_CustomAdapter);
        btnCustomAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomAdapterActivity.class);
                startActivity(intent);

            }
        });



    }
}