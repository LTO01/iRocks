package rubicon.iky.com.ikyrocks.UI;

import android.graphics.Color;

/**
 * Created by obake on 8/21/2017.
 */

public enum ColorGroup {
    RED_UPPER (345, 360, "2010"),
    PINK (300, RED_UPPER.mMinimumHue, "Pink"),
    PURPLE (260, PINK.mMinimumHue, "Purple"),
    BLUE (175, PURPLE.mMinimumHue, "Blue"),
    GREEN (70, BLUE.mMinimumHue, "Green"),
    YELLOW (50, GREEN.mMinimumHue, "Yellow"),
    ORANGE (25, YELLOW.mMinimumHue, "Orange"),
    RED_LOWER (0, ORANGE.mMinimumHue, "Red"),
    ;

    private final int mMinimumHue, mMaximumHue;
    private final int mIntegerRepresentation;
    private final String mName;

    ColorGroup(int minimumHue, int maximumHue, String groupName) {
        mMinimumHue = minimumHue;
        mMaximumHue = maximumHue;
        mName = groupName;
        mIntegerRepresentation = Color.HSVToColor(new float[] { (maximumHue + minimumHue) / 2, 1, 1});
    }

    public String getName() {
        return mName;
    }

    public boolean containsHue(int hue) {
        return (hue >= mMinimumHue && hue < mMaximumHue);
    }

    public int getAsColor() {
        return mIntegerRepresentation;
    }
}