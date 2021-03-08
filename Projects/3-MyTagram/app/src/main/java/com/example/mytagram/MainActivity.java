package com.example.mytagram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Post> posts = new ArrayList<>();
    static final int POST_REQUEST = 1;
    ListView listView;
    Button btnPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        listView.setAdapter(new PostAdapter(this,posts));

        btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                startActivityForResult(intent, POST_REQUEST );

            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == POST_REQUEST && resultCode == Activity.RESULT_OK){
            Post post = new Post();
            post.setMessage(data.getCharSequenceExtra("msg").toString());
            post.setImage((Bitmap) data.getParcelableExtra("bitmap"));
            posts.add(post);
            ((PostAdapter) listView.getAdapter()).notifyDataSetChanged();
        }
    }
}