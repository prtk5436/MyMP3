package com.example.mymp3;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class EnglishFragment extends Fragment {

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

    public EnglishFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_song, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Songs> english = new ArrayList<>();

        english.add(new Songs(getString(R.string.nature_wave), "1", R.drawable.ic_play_circle_outline, R.raw.nature_wind));
        english.add(new Songs(getString(R.string.nature_jungle), "2", R.drawable.ic_play_circle_outline, R.raw.nature_jungle));
        english.add(new Songs(getString(R.string.nature_night), "3", R.drawable.ic_play_circle_outline, R.raw.nature_night));
        english.add(new Songs(getString(R.string.nature_ocean), "4", R.drawable.ic_play_circle_outline, R.raw.nature_breathing_ocean));
        english.add(new Songs(getString(R.string.nature_thunderstorm), "5", R.drawable.ic_play_circle_outline, R.raw.nature_thunderstorm));
        english.add(new Songs(getString(R.string.nature_wave), "6", R.drawable.ic_play_circle_outline, R.raw.nature_wave));


        EnglishAdaptor englishAdaptor = new EnglishAdaptor(getActivity(), english);

        ListView listView = rootView.findViewById(R.id.list_numbers);

        listView.setAdapter(englishAdaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Songs currentAlbum = english.get(position);
                String songNo = currentAlbum.getmSongNo();
                String currentAlbumName = currentAlbum.getmSong();
                Toast.makeText(getActivity(), "playing " + songNo + currentAlbumName, Toast.LENGTH_LONG).show();
                int result = mAudioManager.requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), currentAlbum.getmSound());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }


    @Override
    public void onStop() {
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
