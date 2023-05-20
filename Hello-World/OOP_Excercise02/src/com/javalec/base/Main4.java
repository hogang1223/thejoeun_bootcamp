package com.javalec.base;

import java.util.Scanner;

import com.javalec.function.Calc4;

public class Main4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		int num1 = 0, num2 = 0;
		int add = 0, substract = 0, multiply = 0, divide=0;
		String[] arrCalc = {"덧셈", "뺄셈", "곱셈", "나눗셈"};
		String[] arrResult = new String[arrCalc.length];
		
		// 사용자 입력
		System.out.print("첫번째 숫자를 입력하세요 :");
		num1 = scanner.nextInt();
		System.out.print("두번째 숫자를 입력하세요 :");
		num2 = scanner.nextInt();
		
		// 연산 결과
		Calc4 calc4 = new Calc4(num1, num2);
		calc4.print();
		calc4.print2();
		
		// 배열로 받아와서 for문으로 출력
		System.out.println(">>>>>>>결과<<<<<<<");
		calc4.print3(arrResult);
		for(int i=0; i<arrCalc.length; i++) {
			System.out.println(arrCalc[i] + " : " + arrResult[i]);
		}
	}

}
