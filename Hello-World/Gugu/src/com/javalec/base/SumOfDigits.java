package com.javalec.base;

import java.util.Scanner;

public class SumOfDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 입력한 한 자릿수 정수의 합을 구하는 프로그램을 작성하라.
		// Enter an integer(0~9) : 12345678
		// Sum of digits = 36
		
		Scanner scanner = new Scanner(System.in);
		int num = 0;
		int sum = 0;
		
		System.out.print("Enter an integer(0~9) : ");
		num = scanner.nextInt();
		
		for(int i = num; i>0; i/=10) {
			sum += i%10;
		}
		System.out.println("Sum of digits = " + sum);

	}

}
