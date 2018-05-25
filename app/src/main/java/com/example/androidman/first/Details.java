package com.example.androidman.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Details extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView Title=(TextView)findViewById(R.id.textView2);
        TextView vote=(TextView)findViewById(R.id.textView4);
        TextView overview=(TextView)findViewById(R.id.textView8);
        TextView release_date=(TextView)findViewById(R.id.textView3);
        ImageView image=(ImageView)findViewById(R.id.imageView2);


        Intent intent=getIntent();
        Title.setText(intent.getStringExtra("title"));
        Picasso.with(this).load("http://image.tmdb.org/t/p/w342/"+ intent.getStringExtra("image") +"").into(image);
        overview.setText(intent.getStringExtra("overview"));
        release_date.setText(intent.getStringExtra("date_relase"));
        vote.setText(intent.getStringExtra("vote"));

    }
}
