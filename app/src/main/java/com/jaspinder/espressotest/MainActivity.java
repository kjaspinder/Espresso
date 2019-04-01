package com.jaspinder.espressotest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
	@BindView(R.id.email)
	EditText email;

	@BindView(R.id.password)
	EditText password;

	@BindView(R.id.login)
	Button login;

	@BindView(R.id.signup)
	Button signup;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		login.setOnClickListener(this);
		signup.setOnClickListener(this);


	}
	@Override
	public void onClick(View v)
	{

		if (v.getId() == R.id.login)
		{
			if (!TextUtils.isEmpty(email.getText()) && !TextUtils.isEmpty(password.getText()))
			{

				new LongOperation().execute();

			}
		}
		else if (v.getId() == R.id.signup)
		{
			Intent i = new Intent(MainActivity.this, SignupActivity.class);
			startActivity(i);
			finish();
		}


	}


	private class LongOperation extends AsyncTask<Void, Void, Void>
	{
		@Override
		protected void onPostExecute(Void aVoid)
		{
			super.onPostExecute(aVoid);
			//line after network call
			EspressoIdlingResource.decrement();
			Intent i = new Intent(MainActivity.this, HomeActivity.class);
			startActivity(i);
			finish();

//
		}
		@Override
		protected Void doInBackground(Void... voids)
		{
			try
			{
				Thread.sleep(5000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			//line before network call
			EspressoIdlingResource.increment();
		}
	}


}
