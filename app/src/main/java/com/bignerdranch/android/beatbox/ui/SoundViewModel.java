package com.bignerdranch.android.beatbox.ui;

import com.bignerdranch.android.beatbox.BR;
import com.bignerdranch.android.beatbox.data.BeatBox;
import com.bignerdranch.android.beatbox.data.Sound;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Created by Tom Buczynski on 17.05.2023.
 */
public class SoundViewModel extends BaseObservable {
   private Sound mSound;
   private final BeatBox mBeatBox;

   public SoundViewModel(BeatBox beatBox) {
      mBeatBox = beatBox;
   }

   @Bindable
   public String getName() {
      return mSound.getFileName().replaceAll("_", " ");
   }

   public void setSound(Sound sound) {
      mSound = sound;
      notifyPropertyChanged(BR.name);
   }

   public void onButtonClicked() {
      mBeatBox.playSound(mSound);
   }
}
