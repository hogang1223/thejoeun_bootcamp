package com.javalec.function;

public class Calc3 {
	public int num1, num2, result;
	public String message;
	public double dResult;
	
	public Calc3() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Calc3(int num1, int num2) {
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.result = result;
		this.message = message;
	}


	//　method
	public void addCalc3() {
		result = num1 + num2;
		System.out.println(result);
	}
	public void substractCalc3() {
		result = num1 - num2;
		System.out.println(result);
	}
	public void multiplyCalc3() {
		result = num1 * num2;
		System.out.println(result);
	}
	public void divideCalc3() {
		if(num1 == 0 || num2 == 0) {
			System.out.println("0은 나눌 수 없습니다. 다른 숫자를 입력해주세요.");
		}else {
			dResult = (double)num1 / num2;
			System.out.println(String.format("%.2f", dResult));
		}
	}
	
	
	
	
}
