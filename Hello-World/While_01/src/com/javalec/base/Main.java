package com.javalec.base;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		int remainder = 0;
		int sum = 0;
		
		System.out.print("Enter an integer : ");
		int inputValue = scanner.nextInt();
		
		while(inputValue!=0) {
			remainder = inputValue % 10;
			sum += remainder;
			inputValue /= 10;
		}System.out.println(sum);
		
	}

}
