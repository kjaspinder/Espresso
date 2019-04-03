package com.jaspinder.espressotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jaspinder.espressotest.CalculatorModule.CalculatorViewOperations;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener
{

	@BindView(R.id.alarms_screen)
	Button alarms_screen;

	@BindView(R.id.calculator)
	Button graph_screen;

	@BindView(R.id.notes_screen)
	Button notes_screen;



	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);

		ButterKnife.bind(this);

		alarms_screen.setOnClickListener(this);
		graph_screen.setOnClickListener(this);
		notes_screen.setOnClickListener(this);
	}
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.alarms_screen:
				Intent i = new Intent(HomeActivity.this, AlarmSettingActivity.class);
				startActivity(i);
				break;
			case R.id.calculator:
				Intent i1 = new Intent(HomeActivity.this, CalculatorViewOperations.class);
				startActivity(i1);
				break;
			case R.id.notes_screen:

				break;
		}
	}
}
