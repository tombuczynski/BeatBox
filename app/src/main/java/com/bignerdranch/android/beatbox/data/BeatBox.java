package com.bignerdranch.android.beatbox.data;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by Tom Buczynski on 11.05.2023.
 */
public class BeatBox {
   private static final String TAG = "BeatBox";
   private static final String SOUND_FILES_FOLDER = "sample_sounds";
   private static final int MAX_SOUNDS = 5;
   private final AssetManager mAssets;
   private final SoundPool mSoundPool;

   private final List<Sound> mSounds = new ArrayList<>();

   private float mRate;

   public List<Sound> getSounds() {
      return mSounds;
   }

   public void setRate(float rate) {
      mRate = rate;
   }

   public BeatBox(AssetManager assets) {
      mAssets = assets;
      mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
   }

   public void loadSounds() {
      try {
         String[] listOfFiles = mAssets.list(SOUND_FILES_FOLDER);
         Log.i(TAG, String.format("%d files(s) found", listOfFiles.length));

         mSounds.clear();
         for (String file : listOfFiles) {
            Sound s = new Sound(SOUND_FILES_FOLDER + '/' + file);
            loadSoundMedia(s);
            mSounds.add(s);
         }

      } catch (IOException e) {
         Log.e(TAG, e.getMessage());
      }
   }

   public void playSound(@NonNull Sound sound) {
      Integer soundId = sound.getSoundId();
      if (soundId != null)
         mSoundPool.play(soundId, 1f, 1f, 1, 0, mRate);
   }

   public void release() {
      mSoundPool.release();
   }

   private void loadSoundMedia(@NonNull Sound sound) throws IOException {
      AssetFileDescriptor fileDescriptor = mAssets.openFd(sound.getAssetsFilePath());
      sound.setSoundId(mSoundPool.load(fileDescriptor, 1));
   }


}
