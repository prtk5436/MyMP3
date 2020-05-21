package com.example.mymp3;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EnglishSongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        ArrayList<English> english = new ArrayList<English>();

        english.add(new English(getString(R.string.nature_forest), "1", R.drawable.ic_play_circle_outline));
        english.add(new English(getString(R.string.nature_jungle), "2", R.drawable.ic_play_circle_outline));
        english.add(new English(getString(R.string.nature_night), "3", R.drawable.ic_play_circle_outline));
        english.add(new English(getString(R.string.nature_ocean), "4", R.drawable.ic_play_circle_outline));
        english.add(new English(getString(R.string.nature_thunderstorm), "5", R.drawable.ic_play_circle_outline));
        english.add(new English(getString(R.string.nature_wave), "6", R.drawable.ic_play_circle_outline));


        EnglishAdaptor englishAdaptor = new EnglishAdaptor(this, english);

        ListView listView = findViewById(R.id.list_numbers);

        listView.setAdapter(englishAdaptor);
    }
}

