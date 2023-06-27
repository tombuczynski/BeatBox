package com.bignerdranch.android.beatbox.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bignerdranch.android.beatbox.data.BeatBox;
import com.bignerdranch.android.beatbox.data.Sound;
import com.bignerdranch.android.beatbox.databinding.SoundButtonItemBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Tom Buczynski on 05.05.2023.
 */
public class SoundButtonRecViewAdapter extends RecyclerView.Adapter<SoundButtonRecViewAdapter.SoundButtonViewHolder> {

    private BeatBox mBeatBox;

    public SoundButtonRecViewAdapter(BeatBox beatBox) {

        mBeatBox = beatBox;
    }

    @NonNull
    @Override
    public SoundButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SoundButtonItemBinding binding = SoundButtonItemBinding.inflate(LayoutInflater.from(parent.getContext()));

        return new SoundButtonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SoundButtonViewHolder holder, int position) {
        holder.bind(mBeatBox.getSounds().get(position));
    }

    @Override
    public int getItemCount() {
        return mBeatBox.getSounds().size();
    }

    public class SoundButtonViewHolder extends RecyclerView.ViewHolder {

        public final SoundButtonItemBinding b;
        private final SoundViewModel mModel;

        public SoundButtonViewHolder(@NonNull SoundButtonItemBinding itemBinding) {
            super(itemBinding.getRoot());
            b = itemBinding;
            b.setModel(mModel = new SoundViewModel(mBeatBox));
        }

        public void bind(Sound sound) {
            mModel.setSound(sound);
            b.executePendingBindings();
        }
    }
}
