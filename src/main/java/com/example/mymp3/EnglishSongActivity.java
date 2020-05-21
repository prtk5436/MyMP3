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

public class EnglishSongActivity extends AppCompatActivity {


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

        final ArrayList<Songs> english = new ArrayList<>();

        english.add(new Songs(getString(R.string.nature_wave), "1", R.drawable.ic_play_circle_outline, R.raw.nature_wind));
        english.add(new Songs(getString(R.string.nature_jungle), "2", R.drawable.ic_play_circle_outline, R.raw.nature_jungle));
        english.add(new Songs(getString(R.string.nature_night), "3", R.drawable.ic_play_circle_outline, R.raw.nature_night));
        english.add(new Songs(getString(R.string.nature_ocean), "4", R.drawable.ic_play_circle_outline, R.raw.nature_breathing_ocean));
        english.add(new Songs(getString(R.string.nature_thunderstorm), "5", R.drawable.ic_play_circle_outline, R.raw.nature_thunderstorm));
        english.add(new Songs(getString(R.string.nature_wave), "6", R.drawable.ic_play_circle_outline, R.raw.nature_wave));


        EnglishAdaptor englishAdaptor = new EnglishAdaptor(this, english);

        ListView listView = findViewById(R.id.list_numbers);

        listView.setAdapter(englishAdaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Songs currentAlbum = english.get(position);
                String songNo = currentAlbum.getmSongNo();
                String currentAlbumName = currentAlbum.getmSong();
                Toast.makeText(EnglishSongActivity.this, "playing " + songNo + currentAlbumName, Toast.LENGTH_LONG).show();
                int result = mAudioManager.requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(EnglishSongActivity.this, currentAlbum.getmSound());
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
