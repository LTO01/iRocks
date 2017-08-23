package rubicon.iky.com.ikyrocks.UI;

import android.support.annotation.NonNull;

/**
 * Created by obake on 8/21/2017.
 */

class ColorGroupCalculator {

    @NonNull
    public ColorGroup getColorGroup(float hue) {
        for (ColorGroup group : ColorGroup.values()) {
            if (group.containsHue((int) hue)) {
                return group;
            }
        }

        throw new NullPointerException("Could not classify hue into Color Group!");
    }
}