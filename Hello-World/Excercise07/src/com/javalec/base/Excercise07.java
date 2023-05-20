package com.javalec.base;

import java.util.Scanner;

public class Excercise07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 숫자 삼각형에 프로그램 작성.
		// 몇 단계의 피라미드로 구성할까 ? : 5
		// 1
		// 2 3
		// 4 5 6
		
		Scanner scanner = new Scanner(System.in);
		int inputNum = 0;
		int outputNum = 1;
		
		// 사용자 입력
		System.out.print("몇 단계의 피라미드로 구성할까 ? : ");
		inputNum = scanner.nextInt();
		
		// 피라미드 만들기
		for(int i=1; i<=inputNum; i++) {
			for(int j=0; j<i; j++) {
				System.out.print(String.format("%3d", outputNum));
				outputNum++ ;
			}
			System.out.println();
		}
		
		
	}

}
