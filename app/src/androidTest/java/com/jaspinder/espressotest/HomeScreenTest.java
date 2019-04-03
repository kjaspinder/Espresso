package com.jaspinder.espressotest;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.jaspinder.espressotest.CustomMatcher.withRecyclerView;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeScreenTest
{

	@Rule
	public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule<>(
			HomeActivity.class);

	@Before
	public void setUp() throws Exception
	{
		//Register idlying resource
		IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
	}

	@Test
	public void AlarmSettingButtonDisplayed()
	{
		onView(allOf(withId(R.id.alarms_screen), isCompletelyDisplayed()));
	}

	@Test
	public void checkButtonIsCenterHorizontalOnScreen()
	{
		onView(withId(R.id.alarms_screen)).check(matches(CustomMatcher.isCenteredHorizontally()));
	}

	public void openAlarmSettingScreen()
	{
		onView(withId(R.id.alarms_screen)).perform(click());
	}

	@Test
	public void checkSettingScreen()
	{
		openAlarmSettingScreen();
		onView(withId(R.id.topText)).check(matches(isDisplayed()));
	}


	@Test
	public void checkTopTextFitTwoLines()
	{
		openAlarmSettingScreen();
		onView(withId(R.id.topText)).check(matches(CustomMatcher.isTextInLines(2)));
	}

	@Test
	public void checkRecyclerViewHighAlarmIsOn()
	{
		openAlarmSettingScreen();
		onView(withRecyclerView(R.id.alarmMenuList).atPosition(1))
				.check(matches(hasDescendant(withText("High Glucose Alarm"))));
		onView(withRecyclerView(R.id.alarmMenuList).atPosition(1))
				.check(matches(hasDescendant(withText("ON"))));
	}

	@Test
	public void checkLowAlarmClickShowsDesiredContent()
	{
		openAlarmSettingScreen();
		onView(withRecyclerView(R.id.alarmMenuList).atPosition(1)).perform(click());

		onView(withText("High Glucose Alarm")).check(matches(isDisplayed()));

	}

	@Test
	public void learnMoreShowsRightContent()
	{
		openAlarmSettingScreen();
		onView(withId(R.id.learnMore)).perform(click());

		onView(withText("Learn More")).check(matches(isDisplayed()));

	}

	@Test
	public void checkNoTwoViewsOverlap()
	{
		openAlarmSettingScreen();
		onView(withRecyclerView(R.id.alarmMenuList).
				atPositionOnView(0, R.id.textMenuName)).
				check(isCompletelyAbove(withRecyclerView(R.id.alarmMenuList).
						atPositionOnView(0, R.id.textGlucoseAlarm)));
	}


	public void tearDown() throws Exception
	{
		//unregister idling resource
		IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
	}


}
