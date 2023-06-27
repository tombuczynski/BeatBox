package com.bignerdranch.android.beatbox.ui;

import com.bignerdranch.android.beatbox.data.BeatBox;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Tom Buczynski on 08.06.2023.
 */
public class BeatBoxViewModel extends ViewModel {
    private BeatBox mBeatBox;

    private MutableLiveData<Integer> mRate = new MutableLiveData<>(50);

    public MutableLiveData<Integer> getRate() {
        return mRate;
    }

    public BeatBox getBeatBox() {
        return mBeatBox;
    }
    public void setBeatBox(BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     * <p>
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    @Override
    protected void onCleared() {
        super.onCleared();

        if (mBeatBox != null)
            mBeatBox.release();
    }
}
