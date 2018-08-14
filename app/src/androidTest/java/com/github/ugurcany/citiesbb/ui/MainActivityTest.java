package com.github.ugurcany.citiesbb.ui;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.ugurcany.citiesbb.R;
import com.github.ugurcany.citiesbb.ui.util.RecyclerViewItemCountAssertion;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule
            = new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        mainActivityTestRule.launchActivity(new Intent());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInitialState() {
        Espresso.closeSoftKeyboard();

        //ASSERT
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_search))
                .check(ViewAssertions.matches(ViewMatchers.withText("")));
    }

    @Test
    public void testSearch_valid_short() {
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_search))
                .perform(ViewActions.typeText("Aliaga"));

        Espresso.closeSoftKeyboard();

        //ASSERT
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_cities))
                .check(new RecyclerViewItemCountAssertion(3));

        Espresso.onView(ViewMatchers.withId(R.id.text_view_empty))
                .check(ViewAssertions.matches(
                        CoreMatchers.not(ViewMatchers.isDisplayed())));
    }

    @Test
    public void testSearch_valid_long() {
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_search))
                .perform(ViewActions.typeText("Row"));

        Espresso.closeSoftKeyboard();

        //ASSERT
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_cities))
                .check(new RecyclerViewItemCountAssertion(25));

        Espresso.onView(ViewMatchers.withId(R.id.text_view_empty))
                .check(ViewAssertions.matches(
                        CoreMatchers.not(ViewMatchers.isDisplayed())));
    }

    @Test
    public void testSearch_invalid() {
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_search))
                .perform(ViewActions.typeText("izmir"));

        Espresso.closeSoftKeyboard();

        //ASSERT
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_cities))
                .check(new RecyclerViewItemCountAssertion(0));

        Espresso.onView(ViewMatchers.withId(R.id.text_view_empty))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testCitiesToolbar() {
        //ASSERT
        Espresso.onView(ViewMatchers.withText(R.string.app_name))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withContentDescription(R.string.abc_action_bar_up_description))
                .check(ViewAssertions.doesNotExist());
    }

    @Test
    public void testMapToolbar() {
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_search))
                .perform(ViewActions.typeText("Hannover"));

        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_cities))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        //ASSERT
        Espresso.onView(ViewMatchers.withText("Hannover, DE"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withContentDescription(R.string.abc_action_bar_up_description))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testComeBackFromMap() {
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_search))
                .perform(ViewActions.typeText("Hannover"));

        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_cities))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        Espresso.pressBack();

        //ASSERT
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_search))
                .check(ViewAssertions.matches(ViewMatchers.withText("Hannover")));

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_cities))
                .check(new RecyclerViewItemCountAssertion(3));
    }

}