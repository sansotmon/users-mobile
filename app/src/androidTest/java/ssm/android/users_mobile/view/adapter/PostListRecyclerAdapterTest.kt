package ssm.android.users_mobile.view.adapter

import androidx.test.espresso.Espresso
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
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
import ssm.android.users_mobile.view.activity.UserDetailActivity

@RunWith(AndroidJUnit4::class)
class PostListRecyclerAdapterTest{
    private val ITEM_POSITION = 2

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<UserDetailActivity> =
        ActivityScenarioRule(UserDetailActivity::class.java)

    @Test(expected = PerformException::class)
    fun validateItemNotExist() {
        Espresso.onView(withId(R.id.postRecycler))
            .perform(
                RecyclerViewActions.scrollTo<PostListRecyclerAdapter.ViewHolder>(
                    ViewMatchers.hasDescendant(ViewMatchers.withText("not in the list"))
                )
            )
    }

    @Test(expected = PerformException::class)
    fun scrollToBelow() {
        Espresso.onView(withId(R.id.postRecycler))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<PostListRecyclerAdapter.ViewHolder>(
                    ITEM_POSITION, ViewActions.click()
                )
            )
    }

    @Test
    fun validateIsScrollAsRecycler() {
        isRecyclable()
    }

    private fun isRecyclable(): Matcher<PostListRecyclerAdapter.ViewHolder?> {
        return object : TypeSafeMatcher<PostListRecyclerAdapter.ViewHolder?>() {
            override fun matchesSafely(item: PostListRecyclerAdapter.ViewHolder?): Boolean {
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