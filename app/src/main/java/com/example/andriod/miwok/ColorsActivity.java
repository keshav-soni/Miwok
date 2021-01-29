package com.example.andriod.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            mMediaPlayer.release();

            mMediaPlayer = null;
        }
    }

    private MediaPlayer.OnCompletionListener mCompletionListener = mediaPlayer -> {
        // Now that the sound file has finished playing, release the media player resources.
        releaseMediaPlayer();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.category_colors);
        setContentView(R.layout.activity_colors);

        ArrayList<Word> words =new ArrayList<Word>();

        words.add(new Word("red", "weṭeṭṭi",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("green", "chokokki",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("brown", "ṭakaakki",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("gray", "ṭopoppi",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("black", "kululli",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("white", "kelelli",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("dusty yellow", "ṭopiisә",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("mustard yellow", "chiwiiṭә",R.drawable.number_one,R.raw.number_one));

        WordAdapter adapter  = new WordAdapter(this, words,R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudio());
                mMediaPlayer.start();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {

            releaseMediaPlayer();

            Word word = words.get(position);
            mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudio());
            mMediaPlayer.start();

            mMediaPlayer.setOnCompletionListener(mCompletionListener);
        });
    }
}