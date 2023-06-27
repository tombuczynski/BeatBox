package com.bignerdranch.android.beatbox;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.bignerdranch.android.beatbox.databinding.ActivityFragmentContainerBinding;
import com.bignerdranch.android.beatbox.ui.BeatBoxViewModel;
import com.bignerdranch.android.beatbox.ui.ButtonGridFragment;

public class MainActivity extends FragmentContainerActivity {

    private BeatBoxViewModel mBoxViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBoxViewModel = new ViewModelProvider(this).get(BeatBoxViewModel.class);

        ActivityFragmentContainerBinding b = DataBindingUtil.setContentView(this, R.layout.activity_fragment_container);
        b.setModel(mBoxViewModel);
        b.setLifecycleOwner(this);

        mBoxViewModel.getRate().observe(this, rate -> {
            float r = (rate + 50) / 100f;
            b.textViewPlaybackRate.setText(getString(R.string.playback_rate, r));
            mBoxViewModel.getBeatBox().setRate(r);
        });
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return ButtonGridFragment.class;
    }
}