package com.jaspinder.espressotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jaspinder.espressotest.Interfaces.OnClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AlarmSettingActivity extends AppCompatActivity implements OnClickListener, View.OnClickListener
{

	@BindView(R.id.alarmMenuList)
	RecyclerView alarmMenuList;

	@BindView(R.id.learnMore)
	Button learnMore;

	private AlarmsMenuAdapter mAlarmMenuAdapter;

	List<AlarmMenuItemViewModel> mMenuList = new ArrayList<>();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_alarm_settings);

		ButterKnife.bind(this);

		setUpAlarmList();

		learnMore.setOnClickListener(this);
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		prepareSettingList();
	}

	private void prepareSettingList()
	{
		List<String> menuItems = Arrays.asList(getResources().getStringArray(R.array.alarmMenu));
		mMenuList.clear();

		for (String item : menuItems)
		{
			mMenuList.add(new AlarmMenuItemViewModel(item, "ON", "Below 3.9 mmol/L"));
		}

		mAlarmMenuAdapter.notifyDataSetChanged();
	}
	private void setUpAlarmList()
	{
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
		alarmMenuList.setLayoutManager(mLayoutManager);
		alarmMenuList.addItemDecoration(new DividerItemDecoration(this,
				DividerItemDecoration.VERTICAL));
		mAlarmMenuAdapter = new AlarmsMenuAdapter(mMenuList, this);
		alarmMenuList.setAdapter(mAlarmMenuAdapter);
	}
	@Override
	public void onClick(int position)
	{
		Intent i = new Intent(AlarmSettingActivity.this, AlarmDetailActivity.class);
		i.putExtra("alarm_details", mMenuList.get(position));
		startActivity(i);
	}
	@Override
	public void onClick(View v)
	{
		if (v.getId() == R.id.learnMore)
		{
			AlarmMenuItemViewModel model = new AlarmMenuItemViewModel("Learn More","On","x");
			Intent i = new Intent(AlarmSettingActivity.this, AlarmDetailActivity.class);
			i.putExtra("alarm_details", model);
			startActivity(i);
		}
	}
}
