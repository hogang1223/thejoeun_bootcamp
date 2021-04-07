package com.javalec.function;

public class Calc4 {

	int num1 = 0, num2 = 0 ;
	String result = "";
	String[] arrResult = new String[4];
	
	public Calc4() {
		// TODO Auto-generated constructor stub
	}

	public Calc4(int num1, int num2) {
		super();
		this.num1 = num1;
		this.num2 = num2;
	}
	
	private String addCalc4(){
		arrResult[0] = Integer.toString(num1 + num2);
		return arrResult[0];
	}
	private String substractCalc4(){
		arrResult[1] = Integer.toString(num1 - num2);
		return arrResult[1];
	}
	private String multiplyCalc4(){
		arrResult[2] = Integer.toString(num1 * num2);
		return arrResult[2];
	}
	private String divideCalc4(){
		if(num1==0 || num2 ==0) {
			arrResult[3] = "0을 제외한 숫자를 입력하세요";
		}else {
			arrResult[3] = Double.toString((num1*1.0) / num2);
		}
		return arrResult[3];
	}
	
	public void print() {
		System.out.println(">>>>>>>결과<<<<<<<");
		System.out.println("덧셈  :" + addCalc4());
		System.out.println("뺄셈  :" + substractCalc4());
		System.out.println("곱셈  :" + multiplyCalc4());
		System.out.println("나눗셈 :" + divideCalc4());
	}
	
	public void print2() {
		String[] calc = {"덧셈  ","뺄셈  ","곱셈  ","나눗셈 "};

		System.out.println(">>>>>>>결과<<<<<<<");
		
		for(int i=0; i<arrResult.length; i++) {
			System.out.println(calc[i] + ":" + arrResult[i]);
		}
	}
	
	public void print3(String arrResult[]) {
		for(int i=0; i<arrResult.length; i++) {
			switch(i) {
			case 0 :
				arrResult[i] = Integer.toString(num1 + num2);
				break;
			case 1 :
				arrResult[i] = Integer.toString(num1 - num2);
				break;
			case 2 :
				arrResult[i] = Integer.toString(num1 * num2);
				break;
			case 3 :
				if(num1==0 || num2==0) {
					arrResult[i] = "0은 나눌 수 없습니다. 다른 숫자를 입력해 주세요.";
				}else {
					arrResult[i] = Double.toString((num1*1.0) / num2);
					break;
				}
			}
		}
	}
}
