package com.example.listviewexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.listviewexample.ArrayAdapterActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterActivity extends AppCompatActivity {
final List<Animal> animals = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter);

        animals.add(new Animal("Cat",R.mipmap.ic_cat));
        animals.add(new Animal("Cat",R.mipmap.ic_butterfly));

        ListView listView = findViewById(R.id.listview);
        AnimalAdapter adapter = new AnimalAdapter(animals,this);
        listView.setAdapter(adapter);

    }
}