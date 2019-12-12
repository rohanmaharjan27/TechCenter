package com.rohan.techcenter;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.rohan.techcenter.Activity.MainActivity;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsNot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class AddToWishlistInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> addToWishlistTest = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void TestUI() throws Exception {
        onView(withId(R.id.action_product)).perform(click());

        onView(withId(R.id.recyclerViewProducts)).perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.scrollViewProduct)).perform(swipeUp());
        onView(Matchers.allOf(ViewMatchers.withId(R.id.btn_wishlist), ViewMatchers.withText("Add to Wishlist")))
                .perform(ViewActions.click());

        onView(ViewMatchers.withText("Added to Wishlist!"))
                .inRoot(RootMatchers.withDecorView(IsNot.not(Matchers.is(addToWishlistTest.getActivity().getWindow().getDecorView()))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        addToWishlistTest.finishActivity();
    }

}
