package com.quiroga.shoppinglist;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.quiroga.shoppinglist", appContext.getPackageName());
    }
}
//@LargeTest
//public class HelloWorldEspressoTest {
//
//    @Rule
//    public ActivityTestRule<MainActivity> mActivityRule =
//            new ActivityTestRule<>(MainActivity.class);
//
//    @Test
//    public void listGoesOverTheFold() {
//        onView(withText("Hello world!")).check(matches(isDisplayed()));
//    }
//
//
//}
