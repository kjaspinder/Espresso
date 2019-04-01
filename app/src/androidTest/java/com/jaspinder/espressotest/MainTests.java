package com.jaspinder.espressotest;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyBelow;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MainTests
{
	@Rule
	public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
			MainActivity.class);

	@Before
	public void setUp() throws Exception
	{
		//Register idlying resource
		IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
	}

	//check id exist on screen, and displayed completely
	@Test
	public void emailDisplayed()
	{
		onView(allOf(withId(R.id.email),isCompletelyDisplayed()));
	}

	@Test
	public void passwordDisplayed()
	{
		onView(allOf(withId(R.id.password),isCompletelyDisplayed()));
	}

	@Test
	public void loginClickable()
	{
		onView(allOf(withId(R.id.login),isClickable()));
	}

	@Test
	public void signupClickable()
	{
		onView(allOf(withId(R.id.signup),isClickable()));
	}

	//test login and the text in home screen fits 2 lines

	@Test
	public void loginTest()
	{
		onView(withId(R.id.email)).perform(typeText("test@xyz.com"));
		onView(withId(R.id.password)).perform(typeText("test1234"));
		onView(withText("Login")).perform(click());
		onView(withId(R.id.home)).check(matches(CustomMatcher.isTextInLines(3)));
	}

	//check text is center horizontal positioned

	@Test
	public void textCentrallyPositioned()
	{
		onView(withId(R.id.email)).perform(typeText("test@xyz.com"));
		onView(withId(R.id.password)).perform(typeText("test1234"));
		onView(withText("Login")).perform(click());
		onView(withId(R.id.home)).check(matches(CustomMatcher.isCenteredHorizontally()));
	}

	@Test
	public void clickSignup()
	{
		onView(withText("Sign Up")).perform(click());
		onView(allOf(withId(R.id.name),isCompletelyDisplayed()));
	}



	@Test
	public void verifySignupWithNoDetails()
	{
		onView(withText("Sign Up")).perform(click());
		onView(withText("Sign Up")).perform(click());
		onView(withText(R.string.fill_all_fields)).check(matches(isDisplayed()));
	}

	@Test
	public void verifySignupWithWrongPassword()
	{
		onView(withText("Sign Up")).perform(click());

		onView(withId(R.id.name)).perform(typeText("test"));
		onView(withId(R.id.email)).perform(typeText("test@gmail.com"));
		onView(withId(R.id.password)).perform(typeText("123456"));
		onView(withId(R.id.confirmPass)).perform(typeText("1234567"));

		onView(withText("Sign Up")).perform(click());
		onView(withText(R.string.password_mismatch)).check(matches(isDisplayed()));
	}

	@Test
	public void viewsDoNotOverlap()
	{
		onView(withText("Sign Up")).perform(click());

		onView(withId(R.id.confirmPass)).check(isCompletelyBelow(withId(R.id.password)));
		onView(withId(R.id.password)).check(isCompletelyBelow(withId(R.id.email)));
		onView(withId(R.id.email)).check(isCompletelyBelow(withId(R.id.name)));

	}



	public void tearDown() throws Exception
	{
		//unregister idling resource
		IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
	}


}
