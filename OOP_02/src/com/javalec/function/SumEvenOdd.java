package com.javalec.function;

public class SumEvenOdd {

	// Property (초기값)
	
	// Constructor (생성자)
	public SumEvenOdd() {
		// TODO Auto-generated constructor stub
	}
	
	// Method (함수)
	public int sumCalc(int num1, int num2) {
		int sum = 0;
		for(int i=num1; i<=num2; i++) {
			sum += i;
		}
		return sum;
	}
	public String evenOdd(int sum){
		String chk = "";
		if(sum%2==0) {
			chk = "짝수";
		}else {
			chk = "홀수";
		}
		return chk;
	}
	
}
