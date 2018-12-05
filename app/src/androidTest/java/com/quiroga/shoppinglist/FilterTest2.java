package com.quiroga.shoppinglist;


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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FilterTest2 {
//When the user has no ingredients, then the filter button displays all of the recipes
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void filterTest2() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.RecipeSearchButton), withText("Recipe Search"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                1)),
                                7),
                        isDisplayed()));
        appCompatButton.perform(click());

        pressBack();

        ViewInteraction button = onView(
                allOf(withId(R.id.filterbutton), withText("FILTER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.inglistview),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        0)),
                        0),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));

        ViewInteraction relativeLayout2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.inglistview),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        0)),
                        1),
                        isDisplayed()));
        relativeLayout2.check(matches(isDisplayed()));

        ViewInteraction relativeLayout3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.inglistview),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        0)),
                        2),
                        isDisplayed()));
        relativeLayout3.check(matches(isDisplayed()));

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
