package ssm.android.users_mobile.view.adapter

import androidx.test.espresso.Espresso
import androidx.test.espresso.PerformException
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ssm.android.users_mobile.R
import ssm.android.users_mobile.view.activity.UserListActivity

@RunWith(AndroidJUnit4::class)
class UserListRecyclerAdapterTest{

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<UserListActivity> =
        ActivityScenarioRule(UserListActivity::class.java)

    @Test(expected = PerformException::class)
    fun validateItemNotExist() {
        Espresso.onView(ViewMatchers.withId(R.id.userRecycler))
            .perform(
                RecyclerViewActions.scrollTo<UserListRecyclerAdapter.ViewHolder>(
                    ViewMatchers.hasDescendant(ViewMatchers.withText("not in the list"))
                )
            )
    }

    @Test
    fun validateIsScrollAsRecycler() {
        isRecyclable()
    }

    private fun isRecyclable(): Matcher<UserListRecyclerAdapter.ViewHolder?> {
        return object : TypeSafeMatcher<UserListRecyclerAdapter.ViewHolder?>() {
            override fun matchesSafely(item: UserListRecyclerAdapter.ViewHolder?): Boolean {
                item?.let {
                    return it.isRecyclable
                }
                return false
            }

            override fun describeTo(description: Description) {
                description.appendText("is recyclable")
            }
        }
    }

}