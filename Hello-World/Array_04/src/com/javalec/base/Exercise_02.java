package com.javalec.base;

public class Exercise_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 10의 10(0) ! 10(10)까지의 제곱승을 구하라
		//10 ^ 0 = 1
		//10 ^ 1 = 10
		//10 ^ 2 = 100
		//10 ^ 3 = 1000
		//10 ^ 4 = 10000
		//10 ^ 5 = 100000
		//10 ^ 6 = 1000000
		
		
		long[] num = new long[11];
		long result = 1;
			
		for(int i = 0; i<num.length; i++) {
			num[i] = result;
			System.out.println("10^" + String.format("%2d", i) + " = " + String.format("%11d", num[i]));
			result *= 10;
		}
		
//		int num = 10;
//		long result = 1;
//		
//		for(int i=0; i<=10; i++) {
//			System.out.println("10 ^ " + String.format("%2d", i) + " = " + String.format("%11d", result));
//			result *= num;
//		}
		
	}

}
