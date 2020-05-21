package com.example.mymp3;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MarathiSongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        ArrayList<Songs> marathi = new ArrayList<>();

        marathi.add(new Songs(getString(R.string.marathi_Dagdichal), "1", R.drawable.nature_forest));
        marathi.add(new Songs(getString(R.string.marathi_fandri), "2", R.drawable.nature_jungle));
        marathi.add(new Songs(getString(R.string.marathi_farzand), "3", R.drawable.nature_night));
        marathi.add(new Songs(getString(R.string.marathi_nal), "3", R.drawable.nature_ocean));
        marathi.add(new Songs(getString(R.string.marathi_sairat), "4", R.drawable.nature_thunderstorm));
        marathi.add(new Songs(getString(R.string.marathi_zapatlela), "5", R.drawable.nature_wave));
        marathi.add(new Songs(getString(R.string.marathi_fandri), "6", R.drawable.nature_wind));

        MarathiAdaptor marathiAdaptor = new MarathiAdaptor(this, marathi);

        ListView listView = findViewById(R.id.list_numbers);

        listView.setAdapter(marathiAdaptor);
    }
}
