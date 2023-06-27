package com.bignerdranch.android.beatbox.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.beatbox.R;
import com.bignerdranch.android.beatbox.data.BeatBox;

import org.jetbrains.annotations.Contract;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ButtonGridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonGridFragment extends Fragment {

    private BeatBoxViewModel mBoxViewModel;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ButtonGridFragment.
     */
    @NonNull
    @Contract(" -> new")
    public static ButtonGridFragment newInstance() {
        return new ButtonGridFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setRetainInstance(true);

        mBoxViewModel = new ViewModelProvider(requireActivity()).get(BeatBoxViewModel.class);

        BeatBox box = mBoxViewModel.getBeatBox();
        if (box == null) {
            box = new BeatBox(requireContext().getAssets());
            box.loadSounds();
            mBoxViewModel.setBeatBox(box);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_button_grid, container, false);
    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        //mBox.release();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewButtons);

        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        recyclerView.setAdapter(new SoundButtonRecViewAdapter(mBoxViewModel.getBeatBox()));

    }
}