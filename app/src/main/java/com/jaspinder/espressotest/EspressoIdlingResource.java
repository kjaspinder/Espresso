package com.jaspinder.espressotest;



import com.jaspinder.espressotest.SimpleCountingIdlingResource;

import android.support.test.espresso.IdlingResource;

public class EspressoIdlingResource {

	private static final String RESOURCE = "GLOBAL";

	private static SimpleCountingIdlingResource mCountingIdlingResource =
			new SimpleCountingIdlingResource(RESOURCE);

	public static void increment() {
		mCountingIdlingResource.increment();
	}

	public static void decrement() {
		mCountingIdlingResource.decrement();
	}

	public static IdlingResource getIdlingResource() {
		return mCountingIdlingResource;
	}
}
