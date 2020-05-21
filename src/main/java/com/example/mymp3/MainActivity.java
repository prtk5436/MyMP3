package com.example.mymp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button hindi = findViewById(R.id.hindi);
        hindi.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                Intent instrumentIntent = new Intent(MainActivity.this, HindiSongActivity.class);
                startActivity(instrumentIntent);
            }
        });
        Button marathi = findViewById(R.id.Marathi);
        marathi.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                Intent instrumentIntent = new Intent(MainActivity.this, MarathiSongsActivity.class);
                startActivity(instrumentIntent);
            }
        });
        Button Panjabi = findViewById(R.id.Panjabi);
        Panjabi.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                Intent instrumentIntent = new Intent(MainActivity.this, PanjabiSongsActivity.class);
                startActivity(instrumentIntent);
            }
        });
        Button English = findViewById(R.id.english);
        English.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                Intent instrumentIntent = new Intent(MainActivity.this, EnglishSongActivity.class);
                startActivity(instrumentIntent);
            }
        });

    }
}
