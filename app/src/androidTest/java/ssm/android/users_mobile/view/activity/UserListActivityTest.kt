package ssm.android.users_mobile.view.activity

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import ssm.android.users_mobile.R.id.titleText
import ssm.android.users_mobile.R.id.userSearch
import ssm.android.users_mobile.R.string.activity_user_list_title


@RunWith(AndroidJUnit4::class)
class UserListActivityTest {

    @get:Rule
    var mActivityRule: ActivityTestRule<UserListActivity> = ActivityTestRule(
        UserListActivity::class.java
    )

    @Before
    fun setup() {
        Intents.init()
        Intents.intending(Matchers.not(IntentMatchers.isInternal())).respondWith(
            Instrumentation.ActivityResult(
                Activity.RESULT_OK,
                null
            )
        )
    }

    @After
    fun clean() {
        Intents.release()
    }

    @Test
    fun onCreate() {
        onView(withId(userSearch)).perform(click())
        onView(withId(titleText)).check(matches(withText(activity_user_list_title)))
    }

    @Test
    fun validateExtraIntentUser() {
        mActivityRule.launchActivity(Intent())
        Intents.intended(hasComponent(UserListActivity::class.java.name))
    }

}