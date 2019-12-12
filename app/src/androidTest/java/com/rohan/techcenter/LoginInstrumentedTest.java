package com.rohan.techcenter;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.rohan.techcenter.Activity.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class LoginInstrumentedTest {
    @Rule
    public ActivityTestRule<LoginActivity> loginTest=
            new ActivityTestRule<> (LoginActivity.class);

    //The user must be logged out of the app to perform this test
    @Test
    public void TestUI() throws Exception {
        onView(withId(R.id.la_email))
                .perform(typeText("rohanmaharjan123@hotmail.com"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.la_password))
                .perform(typeText("*rohan12345"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.la_btnLogin))
                .perform(click());

    }
}
