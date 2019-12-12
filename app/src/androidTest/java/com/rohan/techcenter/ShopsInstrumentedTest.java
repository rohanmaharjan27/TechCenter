package com.rohan.techcenter;

import android.app.Activity;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.rohan.techcenter.Activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class ShopsInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> shopTest = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void TestUI() throws Exception {
        onView(withText("Checkout Our Stores")).check(matches(isDisplayed()));

        onView(allOf(withText("TechCenter KTM"))).perform(click());

        onView(withText("TechCenter KTM")).check(matches(isDisplayed()));

        shopTest.finishActivity();
    }
}
