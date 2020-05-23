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

public class HindiFragment extends Fragment {
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

    public HindiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_song, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Songs> hindi = new ArrayList<>();

        hindi.add(new Songs(getString(R.string.instruments_guitar), "1", R.drawable.instruments_guitar, R.raw.instruments_guitar));
        hindi.add(new Songs(getString(R.string.instruments_piano), "2", R.drawable.instruments_piano, R.raw.instruments_piano));
        hindi.add(new Songs(getString(R.string.instruments_saxophone), "3", R.drawable.instruments_saxophone, R.raw.instruments_saxophon));
        hindi.add(new Songs(getString(R.string.instruments_tambourine), "3", R.drawable.instruments_tamburine, R.raw.instruments_tambourine));
        hindi.add(new Songs(getString(R.string.instruments_violin), "4", R.drawable.instruments_violin, R.raw.instruments_violin));
        hindi.add(new Songs(getString(R.string.instruments_drums), "5", R.drawable.instruments_dums, R.raw.instruments_dums));
        hindi.add(new Songs(getString(R.string.instruments_trumpet), "6", R.drawable.instruments_trumpet, R.raw.instruments_trumpets));
        hindi.add(new Songs(getString(R.string.instruments_harp), "7", R.drawable.instruments_harp, R.raw.instruments_harp));


        HindiAdaptor hindiAdaptor = new HindiAdaptor(getActivity(), hindi);

        ListView listView = rootView.findViewById(R.id.list_numbers);

        listView.setAdapter(hindiAdaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Songs currentAlbum = hindi.get(position);
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
