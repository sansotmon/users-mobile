package ssm.android.users_mobile.view.activity

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import ssm.android.users_mobile.R

@RunWith(AndroidJUnit4::class)
class UserDetailActivityTest {

    @get:Rule
    var mActivityRule: ActivityTestRule<UserDetailActivity> = ActivityTestRule(
        UserDetailActivity::class.java
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
        Espresso.onView(ViewMatchers.withId(R.id.postRecycler)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.emptyText))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.activity_user_list_empty)))
        Espresso.onView(ViewMatchers.withId(R.id.postLabel))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.activity_user_detail_title)))
    }

    @Test
    fun validateExtraIntentUser() {
        mActivityRule.launchActivity(Intent())
        Intents.intended(IntentMatchers.hasComponent(UserDetailActivity::class.java.name))
    }
}