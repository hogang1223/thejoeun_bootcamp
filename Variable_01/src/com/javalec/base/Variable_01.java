package com.javalec.base;

public class Variable_01 {

	public static void main(String[] args) {

		// 변수(Variable)
		// 문자열 변수 선언
		String str = "Hello, World"; // 변수선언
		
		System.out.println(str);
		System.out.println(str);
		
		str = "abc";
		System.out.println(str);
		
		// 정수 변수 선언
		int num1 = 10;
		int num2 = 20;
		int plusResult = 0;
		
		plusResult = num1 + num2;
		
		System.out.println(num1 + num2);
		System.out.println(num1 - num2);
		System.out.println(num1 * num2);
		System.out.println(num1 / num2);
		System.out.println((double)num1 / num2);
		System.out.println((double)(num1 / num2));
		System.out.println(plusResult);
		
		// ----Exercise
		int startNum = 30, endNum = 40;
		int pResult = startNum + endNum;
		int mResult = startNum - endNum;
		
		System.out.println(startNum + "과 " + endNum + "의 덧셈 결과는 " + pResult + "입니다.");
		System.out.println(startNum + "과 " + endNum + "의 뺄셈 결과는 " + mResult + "입니다.");
		System.out.println(startNum + "과 " + endNum + "의 곱셈 결과는 " + (startNum * endNum) + "입니다.");
		System.out.println(startNum + "과 " + endNum + "의 나눗셈 중 몫은 " + (startNum / endNum) + "입니다.");
		System.out.println(startNum + "과 " + endNum + "의 나눗셈 결과는 " + ((double)startNum / endNum) + "입니다.");
		System.out.println(startNum + "과 " + endNum + "의 덧셈결과와 뺄셈결과의 곱은 " + (pResult * mResult) + "입니다.");
		
		
	}

}
