package com.javalec.function;


public class Calc2 {

	public int num1, num2;
	public String result;
	
	public Calc2() {
		// TODO Auto-generated constructor stub
	}
	public Calc2(int num1, int num2) {
		super();
		this.num1 = num1;
		this.num2 = num2;
	}
	
	// 계산 method
	public String addCalc2(){
		result = num1 + " + " + num2 + " = " + Integer.toString(num1 + num2);
		return result;
	}
	public String subtractCalc2() {
		result = num1 + " - " + num2 + " = " + Integer.toString(num1 - num2);
		return result;
	}
	public String multiplyCalc2() {
		result = num1 + " x " + num2 + " = " + Integer.toString(num1 * num2);
		return result;
	}
	public String divideCalc2() {
		if(num1==0 || num2==0) {
			result = "0은 나눌 수 없습니다. 다른 숫자를 입력해 주세요";
		}else {
			
			result = Double.toString(num1 / (num2 * 1.0));
		}
		return result;
	}
	
//	// 출력 method
//	public void print() {
//		if(num1/num2==0)
//		System.out.println(result);
//	}
	
	
	
}
