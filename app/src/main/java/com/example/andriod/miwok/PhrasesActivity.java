package com.example.andriod.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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
        setTitle(R.string.category_phrases);
        setContentView(R.layout.activity_phrases);

        ArrayList<Word> words =new ArrayList<Word>();

        words.add(new Word("Where are you going?", "weṭeṭṭi",R.raw.number_one));
        words.add(new Word("What is your name?", "chokokki",R.raw.number_one));
        words.add(new Word("My name is...", "ṭakaakki",R.raw.number_one));
        words.add(new Word("How are you feeling?", "ṭopoppi",R.raw.number_one));
        words.add(new Word("I’m feeling good.", "kululli",R.raw.number_one));
        words.add(new Word("Are you coming?", "kelelli",R.raw.number_one));
        words.add(new Word("Yes, I’m coming.", "ṭopiisә",R.raw.number_one));
        words.add(new Word("I’m coming.", "chiwiiṭә",R.raw.number_one));
        words.add(new Word("Let’s go.", "chiwiiṭә",R.raw.number_one));
        words.add(new Word("Come here.", "chiwiiṭә",R.raw.number_one));

        WordAdapter adapter  = new WordAdapter(this, words,R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter );

        listView.setOnItemClickListener((parent, view, position, id) -> {

            releaseMediaPlayer();

            Word word = words.get(position);
            mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudio());
            mMediaPlayer.start();

            mMediaPlayer.setOnCompletionListener(mCompletionListener);
        });
    }
}