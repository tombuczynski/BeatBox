package com.bignerdranch.android.beatbox.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Tom Buczynski on 02.06.2023.
 */
public class StringMatcher extends TypeSafeMatcher<String> {
    private final String mArg;
    private final String mExcChars;

    /**
     * The default constructor for simple sub types
     */
    public StringMatcher(String arg, String excChars) {
        mArg = arg;
        mExcChars = excChars;
    }

    /**
     * Subclasses should implement this. The item will already have been checked for
     * the specific type and will never be null.
     */
    @Override
    protected boolean matchesSafely(String item) {
        if (mArg == null || mArg.length() != item.length())
            return false;

        for (int i = 0; i < item.length(); i++) {
            char cI = item.charAt(i);
            char cA = mArg.charAt(i);
            if (cI != cA && ! isExcludedChar(cI) && ! isExcludedChar(cA)) {
                return false;
            }
        }

        return true;
    }

    private boolean isExcludedChar(char c) {
        return mExcChars.indexOf(c) >= 0;
    }

    /**
     * Generates a description of the object.  The description may be part of a
     * a description of a larger object of which this is just a component, so it
     * should be worded appropriately.
     *
     * @param description The description to be built or appended to.
     */
    @Override
    public void describeTo(Description description) {
       description.appendText(mArg);
    }

    public static Matcher<String> isEqualWithExceptionOf(String arg, String excChars) {
        return new StringMatcher(arg, excChars);
    }
}
