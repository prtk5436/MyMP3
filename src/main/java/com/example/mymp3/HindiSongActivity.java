package com.example.mymp3;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HindiSongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        ArrayList<Hindi> hindi = new ArrayList<Hindi>();

        hindi.add(new Hindi(getString(R.string.instruments_guitar), "1", R.drawable.instruments_guitar));
        hindi.add(new Hindi(getString(R.string.instruments_piano), "2", R.drawable.instruments_piano));
        hindi.add(new Hindi(getString(R.string.instruments_saxophone), "3", R.drawable.instruments_saxophone));
        hindi.add(new Hindi(getString(R.string.instruments_tambourine), "3", R.drawable.instruments_tamburine));
        hindi.add(new Hindi(getString(R.string.instruments_violin), "4", R.drawable.instruments_violin));
        hindi.add(new Hindi(getString(R.string.instruments_drums), "5", R.drawable.instruments_dums));
        hindi.add(new Hindi(getString(R.string.instruments_trumpet), "6", R.drawable.instruments_trumpet));
        hindi.add(new Hindi(getString(R.string.instruments_harp), "7", R.drawable.instruments_harp));


        HindiAdaptor hindiAdaptor = new HindiAdaptor(this, hindi);

        ListView listView = findViewById(R.id.list_numbers);

        listView.setAdapter(hindiAdaptor);
    }
}
