package com.knowwhatwoueat.kwue;


import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.widget.ImageView;

import com.knowwhatwoueat.kwue.Activities.ConsumptionHistoryActivity;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 22.12.2016.
 */

public class ConsumptionHistoryUnitTest {
    static IdlingResource idlingResource;
    @Rule
    public ActivityTestRule<ConsumptionHistoryActivity> mA = new ActivityTestRule<>(ConsumptionHistoryActivity.class);


    @BeforeClass
    public static void classStart() throws NoSuchFieldException {
        ConsumptionHistoryActivity.showMoreButton.performClick();
        Instrumentation instrumentation
                = InstrumentationRegistry.getInstrumentation();

    }


    @AfterClass
    public static void tearDown() throws Exception {
        Espresso.unregisterIdlingResources(idlingResource);
    }

    @Test
    public void titleTest() {
        onView(allOf(withId(R.id.toolbar), withChild(withText(R.string.title_activity_consumption_history)))).check(matches(isDisplayed()));
    }

    @Test
    public void errorTest() {
        onView(withId(R.id.showMoreNutritions)).perform(click());

        onView(withId(R.id.consumption_history_title)).perform(replaceText("aaa"));
        onView(withId(R.id.consumption_list)).perform(click());


        onView(withId(R.id.title)).perform(replaceText(""));
        onView(withId(R.id.all_consumption_history)).perform(replaceText("aaa"));

    }


}