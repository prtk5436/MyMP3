package com.example.mymp3;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PanjabiSongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        ArrayList<Panjabi> panjabi = new ArrayList<Panjabi>();

        panjabi.add(new Panjabi(getString(R.string.animals_bear), "1", R.drawable.animals_bear));
        panjabi.add(new Panjabi(getString(R.string.animals_bird), "2", R.drawable.animals_bird));
        panjabi.add(new Panjabi(getString(R.string.animals_cat), "3", R.drawable.animals_cat));
        panjabi.add(new Panjabi(getString(R.string.animals_chicken), "3", R.drawable.animals_chicken));
        panjabi.add(new Panjabi(getString(R.string.animals_cow), "4", R.drawable.animals_cow));
        panjabi.add(new Panjabi(getString(R.string.animals_cricket), "5", R.drawable.animals_cricket));
        panjabi.add(new Panjabi(getString(R.string.animals_dog), "6", R.drawable.animals_dog));
        panjabi.add(new Panjabi(getString(R.string.animals_eagle), "7", R.drawable.animals_eagle));
        panjabi.add(new Panjabi(getString(R.string.animals_frog), "8", R.drawable.animals_frog));
        panjabi.add(new Panjabi(getString(R.string.animals_horses), "9", R.drawable.animals_horses));
        panjabi.add(new Panjabi(getString(R.string.animals_monkey), "10", R.drawable.animals_monkey));
        panjabi.add(new Panjabi(getString(R.string.animals_owl), "11", R.drawable.animals_owl));
        panjabi.add(new Panjabi(getString(R.string.animals_pig), "12", R.drawable.animals_pig));

        PanjabiAdaptor panjabiAdaptor = new PanjabiAdaptor(this, panjabi);

        ListView listView = findViewById(R.id.list_numbers);

        listView.setAdapter(panjabiAdaptor);
    }
}
