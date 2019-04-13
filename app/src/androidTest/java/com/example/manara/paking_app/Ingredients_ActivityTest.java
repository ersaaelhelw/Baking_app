package com.example.manara.paking_app;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class Ingredients_ActivityTest{

    private IdlingResource mIdlingResource;
    String introduction ;

    @Rule
    public ActivityTestRule<MainActivity> mActivity = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initValidString() {
        introduction = "Recipe Introduction";
        mIdlingResource = mActivity.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }


    @Test
    public void testing(){

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.main_gridView))
                .perform(actionOnItemAtPosition(0, click()));


        onView(withId(R.id.video))
                .perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.ingredient)).check(matches(isDisplayed()));


//        onView(withId(R.id.text)).check(matches(withText(introduction)));
    }

    @After
    public void afterTest(){
        if(mIdlingResource != null){
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
