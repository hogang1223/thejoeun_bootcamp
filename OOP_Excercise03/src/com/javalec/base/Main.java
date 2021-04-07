package com.javalec.base;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		int num = 0;
		int[] inputNum ;
		
		System.out.print("몇개의 숫자를 입력하시겠습니까? : ");
		num = scanner.nextInt();
		
		inputNum = new int [num];
		for(int i=0; i<inputNum.length; i++) {
			System.out.print((i+1) + "번째 숫자 : ");
			inputNum[i] = scanner.nextInt();
		}
		scanner.close();
		
		Calc calc = new Calc(inputNum);
		System.out.println("------덧셈------");
		System.out.println(calc.addAction());
	}

}
