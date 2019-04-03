package com.jaspinder.espressotest.CalculatorModule;

public class CalculatorImplementaion implements CalculatorInterface.CalculatorOperations
{

	private CalculatorInterface.CalculatorViewActions mCalculatorViewActions;

	private double resultValue;

	public CalculatorImplementaion(CalculatorInterface.CalculatorViewActions viewActions)
	{
		this.mCalculatorViewActions = viewActions;
	}
	@Override
	public void add(int a, int b)
	{
		mCalculatorViewActions.updateResult(a+b);
		resultValue = a+b;
	}
	@Override
	public void subtract(int a, int b)
	{
		mCalculatorViewActions.updateResult(a-b);
		resultValue = a-b;
	}
	@Override
	public void multiply(int a, int b)
	{
		mCalculatorViewActions.updateResult(a*b);
		resultValue = a*b;
	}
	@Override
	public void divide(int a, int b)
	{
		mCalculatorViewActions.updateResult(a/b);
		resultValue = a/b;
	}



	public double getResultValue()
	{
		return resultValue;
	}
}
