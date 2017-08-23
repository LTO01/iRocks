package rubicon.iky.com.ikyrocks.UI;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import xyz.danoz.recyclerviewfastscroller.sectionindicator.title.SectionTitleIndicator;

/**
 * Created by obake on 8/21/2017.
 */

public class ColorGroupSectionTitleIndicator extends SectionTitleIndicator<ColorGroup> {

    public ColorGroupSectionTitleIndicator(Context context) {
        super(context);
    }

    public ColorGroupSectionTitleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorGroupSectionTitleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setSection(ColorGroup colorGroup) {
        // Example of using a single character
        setTitleText(colorGroup.getName().charAt(0) + "");

        // Example of using a longer string
        // setTitleText(colorGroup.getName());

        setIndicatorTextColor(colorGroup.getAsColor());
    }

}
