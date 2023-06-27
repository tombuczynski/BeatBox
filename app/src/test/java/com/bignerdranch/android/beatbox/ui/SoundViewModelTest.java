package com.bignerdranch.android.beatbox.ui;

import com.bignerdranch.android.beatbox.data.BeatBox;
import com.bignerdranch.android.beatbox.data.Sound;

import org.junit.Before;
import org.junit.Test;

import static com.bignerdranch.android.beatbox.matchers.StringMatcher.isEqualWithExceptionOf;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Tom Buczynski on 29.05.2023.
 */
public class SoundViewModelTest {
    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {
        mBeatBox = mock(BeatBox.class);

        mSound = new Sound("dir/Sound_File.vaw");
        mSubject = new SoundViewModel(mBeatBox);
        mSubject.setSound(mSound);
    }

    @Test
    public void getName() {
        assertThat(mSubject.getName(), isEqualWithExceptionOf(mSound.getFileName(), "_"));
    }

    @Test
    public void onButtonClicked() {
        mSubject.onButtonClicked();
        verify(mBeatBox).playSound(mSound);
    }
}