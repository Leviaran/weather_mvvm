package ran.singletondev.weather_mvvm

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ran.singletondev.weather_mvvm.dashboard.ForecastActivity

/**
 * Created by ran on 5/22/18.
 */



@RunWith(AndroidJUnit4::class)
class EsspressoTesting {

    @Rule @JvmField
    var forecastActivity = ActivityTestRule<ForecastActivity>(ForecastActivity::class.java)


    @Test
    fun ensureChangesWork(){

        onView(withId(R.id.big_temp)).check(matches(isDisplayed()))
        onView(withId(R.id.location)).check(matches(isDisplayed()))
    }
}