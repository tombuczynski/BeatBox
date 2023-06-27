package com.bignerdranch.android.beatbox.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;

/**
 * Created by Tom Buczynski on 17.05.2023.
 */
public class Sound {
    private final String mAssetsFilePath;
    private final String mFileName;
    private Integer mSoundId;


    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }


    public Sound(String assetsFilePath) {
        mAssetsFilePath = assetsFilePath;
        mFileName = extractFileNameFromPath(assetsFilePath);
        mSoundId = null;
    }

    @Nullable
    private static String extractFileNameFromPath(String assetsFilePath) {
        Pattern pattern = Pattern.compile("([^/]+?)(\\.[^.]*)?$");
        Matcher m = pattern.matcher(assetsFilePath);

        if (m.find()) {
            return m.group(1);
        } else {
            return null;
        }
    }

    public String getAssetsFilePath() {
        return mAssetsFilePath;
    }

    public String getFileName() {
        return mFileName;
    }
}
