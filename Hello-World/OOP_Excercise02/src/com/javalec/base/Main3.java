package com.javalec.base;

import java.util.Scanner;

import com.javalec.function.SumOfDigits;

public class Main3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		int inputNum = 0;
		int result = 0;
		
		SumOfDigits sumOfDigits = new SumOfDigits();
		
		System.out.print("Enter an integer(0~9) : ");
		inputNum = scanner.nextInt();
		
		result = sumOfDigits.sumCalc(inputNum);
		System.out.println("Sum of digits = " + result);
	}

}
