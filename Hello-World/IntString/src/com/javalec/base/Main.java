package com.javalec.base;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num1= 123;
		String str1 = "12345";
		String str2 = "abcde";
		
		String str3 = Integer.toString(num1);
		System.out.println(str3 + str1); // 문자열 + 문자열
		
		int num2 = Integer.parseInt(str1);
		System.out.println(num1 + num2); // 123 + 12345 = 정수의 합

		
		
	}

}
