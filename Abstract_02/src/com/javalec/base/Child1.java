package com.javalec.base;

public class Child1 extends LunchMenu {

	// field
	
	// constructor
	
	public Child1() {
		// TODO Auto-generated constructor stub
	}
	
	public Child1(int rice, int bulgogi, int banana, int milk, int almond) {
		super(rice, bulgogi, banana, milk, almond);
	}


	// method

	@Override
	public int calc() {
		// TODO Auto-generated method stub
		return rice + bulgogi + banana;
	}

}
