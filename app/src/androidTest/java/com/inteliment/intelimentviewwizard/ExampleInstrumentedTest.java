package com.inteliment.intelimentviewwizard;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.inteliment.intelimentviewwizard.scenario1.DashboardActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest extends ActivityTestRule<DashboardActivity> {

    @Rule
    public ActivityTestRule<DashboardActivity> mActivityRule = new ActivityTestRule<>(
            DashboardActivity.class);

    public ExampleInstrumentedTest() {
        super(DashboardActivity.class);
    }

    /**
     * Test recyclerview item
     * @throws Exception
     */
    @Test
    public void testRecyclerView() throws Exception {

        onView(withRecyclerView(R.id.list_item)
                .atPositionOnView(0, R.id.tv_item))
                .check(matches(withText("Item 1")));
    }

    /**
     * Test recyclerview click
     * @throws Exception
     */
    @Test
    public void testRecyclerViewClick() throws Exception {

        onView(withId(R.id.list_item)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.tvSelectedItem))
                .check(matches(withText("Item 1")));
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {

        return new RecyclerViewMatcher(recyclerViewId);
    }
}
