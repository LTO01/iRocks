package rubicon.iky.com.ikyrocks;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MapTest {

    @Rule
    public ActivityTestRule<Splash> mActivityTestRule = new ActivityTestRule<>(Splash.class);

    @Test
    public void mapTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.map),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.lblGeoLocation), withText("iRock"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.btnPrev),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.btnNext),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton3.perform(click());

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.btnPrev), isDisplayed()));
        imageButton4.perform(click());

        ViewInteraction imageButton5 = onView(
                allOf(withId(R.id.btnPrev), isDisplayed()));
        imageButton5.perform(click());

        ViewInteraction imageButton6 = onView(
                allOf(withId(R.id.btnPrev), isDisplayed()));
        imageButton6.perform(click());

        ViewInteraction imageButton7 = onView(
                allOf(withId(R.id.btnPrev), isDisplayed()));
        imageButton7.perform(click());

        ViewInteraction imageButton8 = onView(
                allOf(withId(R.id.btnPrev), isDisplayed()));
        imageButton8.perform(click());

        ViewInteraction imageButton9 = onView(
                allOf(withId(R.id.btnPrev), isDisplayed()));
        imageButton9.perform(click());

        ViewInteraction imageButton10 = onView(
                allOf(withId(R.id.btnPrev), isDisplayed()));
        imageButton10.perform(click());

        ViewInteraction imageButton11 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton11.perform(click());

        ViewInteraction imageButton12 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton12.perform(click());

        ViewInteraction imageButton13 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton13.perform(click());

        ViewInteraction imageButton14 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton14.perform(click());

        ViewInteraction imageButton15 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton15.perform(click());

        ViewInteraction imageButton16 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton16.perform(click());

        ViewInteraction imageButton17 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton17.perform(click());

        ViewInteraction imageButton18 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton18.perform(click());

        ViewInteraction imageButton19 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton19.perform(click());

        ViewInteraction imageButton20 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton20.perform(click());

        ViewInteraction imageButton21 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton21.perform(click());

        ViewInteraction imageButton22 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton22.perform(click());

        ViewInteraction imageButton23 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton23.perform(click());

        ViewInteraction imageButton24 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton24.perform(click());

        ViewInteraction imageButton25 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton25.perform(click());

        ViewInteraction imageButton26 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton26.perform(click());

        ViewInteraction imageButton27 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton27.perform(click());

        ViewInteraction imageButton28 = onView(
                allOf(withId(R.id.btnNext), isDisplayed()));
        imageButton28.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
