package com.jaspinder.espressotest.CalculatorModule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaspinder.espressotest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculatorViewOperations extends AppCompatActivity implements CalculatorInterface.CalculatorViewActions, View.OnClickListener
{
	@BindView(R.id.result)
	TextView result;

	@BindView(R.id.add)
	Button add;

	@BindView(R.id.subtract)
	Button subtract;

	@BindView(R.id.multiply)
	Button multiply;

	@BindView(R.id.divide)
	Button divide;

	@BindView(R.id.a)
	EditText a;

	@BindView(R.id.b)
	EditText b;

	private CalculatorImplementaion mCalculatorImplementaion;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		ButterKnife.bind(this);
		mCalculatorImplementaion = new CalculatorImplementaion(this);

		add.setOnClickListener(this);
		subtract.setOnClickListener(this);
		multiply.setOnClickListener(this);
		divide.setOnClickListener(this);


	}

	private void add(int a, int b)
	{
		mCalculatorImplementaion.add(a,b);
	}

	private void subtract(int a, int b)
	{
		mCalculatorImplementaion.subtract(a,b);
	}

	private void multiply(int a, int b)
	{
		mCalculatorImplementaion.multiply(a,b);
	}

	private void divide(int a,int b)
	{
		mCalculatorImplementaion.divide(a,b);
	}
	@Override
	public void updateResult(double res)
	{
		result.setText(String.valueOf(res));
	}
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.add:
				add(Integer.valueOf(a.getText().toString()),Integer.valueOf(b.getText().toString()));
				break;

			case R.id.subtract:
				subtract(Integer.valueOf(a.getText().toString()),Integer.valueOf(b.getText().toString()));
				break;

			case R.id.multiply:
				multiply(Integer.valueOf(a.getText().toString()),Integer.valueOf(b.getText().toString()));
				break;

			case R.id.divide:
				divide(Integer.valueOf(a.getText().toString()),Integer.valueOf(b.getText().toString()));
				break;
		}
	}
}
