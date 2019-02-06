package com.example.rafaelmarra.useless_people

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.rafaelmarra.useless_people.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AppNavigationTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun checkLoadOfInitialFragment(){

        onView(withId(R.id.txtUserCounter)).check(matches(withText("1")))
        Thread.sleep(2000)
        onView(withId(R.id.fragmentLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPagesNavigation(){

        onView(withId(R.id.buttonNext)).perform(click())
        onView(withId(R.id.txtUserCounter)).check(matches(withText("2")))
        Thread.sleep(2000)
        onView(withId(R.id.fragmentLayout)).check(matches(isDisplayed()))

        onView(withId(R.id.buttonPrevious)).perform(click())
        onView(withId(R.id.txtUserCounter)).check(matches(withText("1")))
        Thread.sleep(2000)
        onView(withId(R.id.fragmentLayout)).check(matches(isDisplayed()))
    }
}