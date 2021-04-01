package com.javalec.base;

import java.util.Scanner;

public class Exercise06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 몇개의 숫자를 더할까요 ? : 4
		// 4개의 숫자를 입력하세요
		// 1
		// 2
		// 3
		// 4
		// 입력한 숫자의 합은 10 입니다.
		
		Scanner scanner = new Scanner(System.in);
		int i= 0;
		int inputNum = 0;
		int sum = 0;
		int subNum;
		
		
		System.out.print("몇개의 숫자를 더할까요 ? : ");
		inputNum = scanner.nextInt();
		System.out.println(inputNum + "개의 숫자를 입력하세요");

		while(i<inputNum) {
			subNum = scanner.nextInt();
			sum += subNum;
			i++ ;
		}
//			sum += subNum;
//			i++ ;
//		}
		System.out.println("입력한 숫자의 합은 " + sum + " 입니다.");
	}

}
