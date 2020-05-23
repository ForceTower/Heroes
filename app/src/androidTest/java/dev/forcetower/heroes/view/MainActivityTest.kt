package dev.forcetower.heroes.view

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import dev.forcetower.heroes.R
import dev.forcetower.heroes.TestApplication
import dev.forcetower.heroes.dagger.DaggerTestComponent
import dev.forcetower.heroes.testutils.MatcherUtils.isTextMaxLines
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val testRule = CountingTaskExecutorRule()

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var app: TestApplication

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        app = instrumentation.targetContext.applicationContext as TestApplication
        DaggerTestComponent.builder().application(app).build().inject(app)
    }

    @Test
    fun navigateTheWholeApp() {
        activityRule.launchActivity(null)
        // ensure start is fine
        Thread.sleep(100)

        onView(withId(R.id.characters_recycler))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        onView(withId(R.id.comic_details_description))
            .check(matches(isDisplayed()))
            .check(matches(isTextMaxLines(3)))

        onView(withId(R.id.btn_expensive))
            .perform(click())

        onView(withId(R.id.title))
            .check(matches(isDisplayed()))
            .check(matches(withText("#1 - Brown eyes green dragon")))

        onView(withId(R.id.description))
            .check(matches(isDisplayed()))
            .check(matches(isTextMaxLines(3)))
            .check(matches(withText("Funky description")))

        onView(withId(R.id.costs))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btn_back))
            .perform(click())

        onView(withId(R.id.btn_back))
            .perform(click())
    }
}