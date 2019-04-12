package com.precious.foodrecipe;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import com.precious.foodrecipe.app.LandingPageActivity;
import com.precious.foodrecipe.app.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LandingPageActivityTest {

    @Rule
  public   ActivityTestRule<LandingPageActivity> mActivityTestRule =
            new ActivityTestRule<>(LandingPageActivity.class);
//
//    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule = new
//            ActivityTestRule<>(MainActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource(){
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();

        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @Test
    public void testSearchView(){
        onView(withId(R.id.searchView)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText("Food"), pressImeActionButton());

//        onView(withId(R.id.recipeRecyclerView)).perform(
//                RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }

    @After
    public void unregisterIdlingResource(){
        if(mIdlingResource != null){
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

}
