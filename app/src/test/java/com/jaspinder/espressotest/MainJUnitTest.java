package com.jaspinder.espressotest;

import com.jaspinder.espressotest.CalculatorModule.CalculatorImplementaion;
import com.jaspinder.espressotest.CalculatorModule.CalculatorInterface;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MainJUnitTest
{
	@Mock
	CalculatorInterface.CalculatorViewActions mCalculatorViewActions;

	CalculatorImplementaion mCalculatorImplementaion;


	@Before
	public void setUp()
	{
		mCalculatorImplementaion = new CalculatorImplementaion(mCalculatorViewActions);
	}

	@Test
	public void testAdd()
	{
		mCalculatorImplementaion.add(5,5);
		Assert.assertEquals(10,mCalculatorImplementaion.getResultValue(),0.01);
	}

	@Test
	public void testSubtract()
	{
		mCalculatorImplementaion.subtract(5,2);
		Assert.assertEquals(3,mCalculatorImplementaion.getResultValue(),0.01);
	}

	@Test
	public void testMultiply()
	{
		mCalculatorImplementaion.multiply(5,5);
		Assert.assertEquals(25,mCalculatorImplementaion.getResultValue(),0.01);
	}

	@Test
	public void testDivide()
	{
		mCalculatorImplementaion.divide(50,5);
		Assert.assertEquals(10,mCalculatorImplementaion.getResultValue(),0.01);
	}




}
