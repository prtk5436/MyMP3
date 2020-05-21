package com.example.mymp3;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MarathiSongsActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Songs> marathi = new ArrayList<>();

        marathi.add(new Songs(getString(R.string.marathi_Dagdichal), "1", R.drawable.nature_forest, R.raw.nature_jungle));
        marathi.add(new Songs(getString(R.string.marathi_fandri), "2", R.drawable.nature_jungle, R.raw.nature_jungle));
        marathi.add(new Songs(getString(R.string.marathi_farzand), "3", R.drawable.nature_night, R.raw.nature_night));
        marathi.add(new Songs(getString(R.string.marathi_nal), "3", R.drawable.nature_ocean, R.raw.nature_breathing_ocean));
        marathi.add(new Songs(getString(R.string.marathi_sairat), "4", R.drawable.nature_thunderstorm, R.raw.nature_thunderstorm));
        marathi.add(new Songs(getString(R.string.marathi_zapatlela), "5", R.drawable.nature_wave, R.raw.nature_wave));
        marathi.add(new Songs(getString(R.string.marathi_fandri), "6", R.drawable.nature_wind, R.raw.nature_wind));

        MarathiAdaptor marathiAdaptor = new MarathiAdaptor(this, marathi);

        ListView listView = findViewById(R.id.list_numbers);

        listView.setAdapter(marathiAdaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Songs currentAlbum = marathi.get(position);
                String songNo = currentAlbum.getmSongNo();
                String currentAlbumName = currentAlbum.getmSong();
                Toast.makeText(MarathiSongsActivity.this, "playing " + songNo + currentAlbumName, Toast.LENGTH_LONG).show();
                int result = mAudioManager.requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(MarathiSongsActivity.this, currentAlbum.getmSound());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;

            mAudioManager.abandonAudioFocus(mAudioFocusChange);
        }
    }
}
