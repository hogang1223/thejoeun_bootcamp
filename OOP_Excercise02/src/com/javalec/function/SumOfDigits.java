package com.javalec.function;

public class SumOfDigits {

	public SumOfDigits() {
		// TODO Auto-generated constructor stub
	}
	
	public int sumCalc(int num) {
		int sum = 0;
		
		for(int i=num; i>0; i/=10) {
			sum += i%10;	
		}
		
		return sum;
	}
	
}
