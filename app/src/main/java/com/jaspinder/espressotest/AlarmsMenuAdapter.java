package com.jaspinder.espressotest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
public class AlarmsMenuAdapter extends RecyclerView.Adapter<AlarmsMenuAdapter.ViewHolder>
{

	private List<AlarmMenuItemViewModel> mMenuList;


	AlarmsMenuAdapter(List<AlarmMenuItemViewModel> menuList)
	{
		this.mMenuList = menuList;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_alarm_setting_list_item, parent, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position)
	{
		AlarmMenuItemViewModel alarmMenuItemViewModel = mMenuList.get(position);
		if(alarmMenuItemViewModel != null)
		{
			viewHolder.textMenuName.setText(alarmMenuItemViewModel.getMenuName());
			viewHolder.textGlucoseAlarm.setText(alarmMenuItemViewModel.getMenuGlucoseAlarm());
			viewHolder.textState.setText(alarmMenuItemViewModel.getAlarmStatus());
		}
	}

	@Override
	public int getItemCount()
	{
		return mMenuList.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		TextView textMenuName;
		TextView textGlucoseAlarm;
		TextView textState;

		public ViewHolder(View v)
		{
			super(v);
			textMenuName = v.findViewById(R.id.textMenuName);
			textGlucoseAlarm = v.findViewById(R.id.textGlucoseAlarm);
			textState = v.findViewById(R.id.textState);

		}

	}


}


