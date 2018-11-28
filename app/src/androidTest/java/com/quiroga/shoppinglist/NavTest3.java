package com.quiroga.shoppinglist;



import android.support.test.espresso.Espresso;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

//User story #10
//As a user who likes being able to go from one feature of the app to another, I want to be able to
// tap a button within any of the main screens and pull up a navigation menu, so that I can quickly
// change which feature I'm looking at.
//
//Given that the user is on the ingredients screen,
// when the user presses the back button,
// the user will be put on the ingredients screen.

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavTest3 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void navTest3() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.ingredientsButton), withText("Ingredients"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                1)),
                                4),
                        isDisplayed()));
        appCompatButton.perform(click());

        Espresso.pressBack();

        // linearLayout2 is only on the shopping list screen, so if we can see it we're in the right
        // place.
        assert(isDisplayed().matches(allOf(withId(R.id.linearLayout2))));
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