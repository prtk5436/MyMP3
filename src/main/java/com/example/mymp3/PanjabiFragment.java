package com.example.mymp3;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PanjabiFragment extends Fragment {
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

    public PanjabiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_song, container, false);
        final ArrayList<Songs> panjabi = new ArrayList<>();

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        panjabi.add(new Songs(getString(R.string.animals_bear), "1", R.drawable.animals_bear, R.raw.animals_bear));
        panjabi.add(new Songs(getString(R.string.animals_bird), "2", R.drawable.animals_bird, R.raw.animals_bird));
        panjabi.add(new Songs(getString(R.string.animals_cat), "3", R.drawable.animals_cat, R.raw.animals_cat));
        panjabi.add(new Songs(getString(R.string.animals_chicken), "3", R.drawable.animals_chicken, R.raw.animals_chicken));
        panjabi.add(new Songs(getString(R.string.animals_cow), "4", R.drawable.animals_cow, R.raw.animals_cow));
        panjabi.add(new Songs(getString(R.string.animals_cricket), "5", R.drawable.animals_cricket, R.raw.animals_cricket));
        panjabi.add(new Songs(getString(R.string.animals_dog), "6", R.drawable.animals_dog, R.raw.animals_dog));
        panjabi.add(new Songs(getString(R.string.animals_eagle), "7", R.drawable.animals_eagle, R.raw.animals_eagle));
        panjabi.add(new Songs(getString(R.string.animals_frog), "8", R.drawable.animals_frog, R.raw.animals_frog));
        panjabi.add(new Songs(getString(R.string.animals_horses), "9", R.drawable.animals_horses, R.raw.animals_horses));
        panjabi.add(new Songs(getString(R.string.animals_monkey), "10", R.drawable.animals_monkey, R.raw.music_old_mcdolnad));
        panjabi.add(new Songs(getString(R.string.animals_owl), "11", R.drawable.animals_owl, R.raw.animals_owl));
        panjabi.add(new Songs(getString(R.string.animals_pig), "12", R.drawable.animals_pig, R.raw.animals_pig));

        PanjabiAdaptor panjabiAdaptor = new PanjabiAdaptor(getActivity(), panjabi);

        ListView listView = rootView.findViewById(R.id.list_numbers);

        listView.setAdapter(panjabiAdaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
                Songs currentAlbum = panjabi.get(position);
                String songNo = currentAlbum.getmSongNo();
                String currentAlbumName = currentAlbum.getmSong();
                Toast.makeText(getActivity(), "playing " + songNo + currentAlbumName, Toast.LENGTH_LONG).show();
                int result = mAudioManager.requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                Log.v("PanjabiSongActivity", "Current album : " + currentAlbum);
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
