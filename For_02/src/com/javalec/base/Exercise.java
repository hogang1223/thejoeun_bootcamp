package com.javalec.base;

import java.util.Scanner;

public class Exercise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Exercise_04
		// 입력한 숫자부터 4개를 증가하여 화면 가로로 구구단을 표시하기
		
		Scanner scanner = new Scanner(System.in);
		int inputNum = 0;
		int num = 0;
		int dan = 0;
		
		System.out.print("Input your number : ");
		inputNum = scanner.nextInt();
		
		// 처리
//		for(num = 1; num <=9; num++) {
//			for(dan = inputNum; dan<inputNum+4; dan++) {
//				System.out.print(dan + " X " + num + " = " + (dan*num) + "\t" );
//				
//			}System.out.println();
//		}
		
		for(num = 1; num<=9; num++) {
			System.out.print(inputNum + " X " + num + " = " + (inputNum*num) + "\t" +
							(inputNum+1) + " X " + num + " = " + ((inputNum+1)*num) + "\t" +
							(inputNum+2) + " X " + num + " = " + ((inputNum+2)*num) + "\t" +
							(inputNum+3) + " X " + num + " = " + ((inputNum+3)*num) + "\n" );		
		}
		
	}
}
