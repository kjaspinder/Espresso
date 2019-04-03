package com.jaspinder.espressotest.CalculatorModule;

public class CalculatorInterface
{

	public interface CalculatorOperations
	{
		public void add(int a, int b);
		public void subtract(int a, int b);
		public void multiply(int a,int b);
		public void divide(int a, int b);
	}

	public interface CalculatorViewActions
	{
		public void updateResult(double result);
	}

}
