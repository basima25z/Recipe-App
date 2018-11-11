package com.quiroga.shoppinglist;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
import static org.hamcrest.Matchers.allOf;

//User story #10
//As a user who likes being able to go from one feature of the app to another, I want to be able to
// tap a button within any of the main screens and pull up a navigation menu, so that I can quickly
// change which feature I'm looking at.
//
//Given that the user is on the ingredients screen,
// when the user presses a button that looks like a hamburger,
// then a navigation menu will slide onscreen from the side.

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavTest1 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void navTest1() {
        ViewInteraction toolbarButton = onView(
                allOf(withId(R.id.toolbar),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                1)),
                                0),
                        isDisplayed()));
        toolbarButton.perform(click());

        DrawerLayout drawerLayout = mActivityTestRule.getActivity().findViewById(R.id.drawer_layout);
        assert drawerLayout.isDrawerOpen(GravityCompat.START);
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
