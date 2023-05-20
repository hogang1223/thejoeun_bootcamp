package com.javalec.base;

import java.util.Scanner;

public class Exeercise02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// 단을 입력하세요 : 2
		// 1 2 3
		// 4 5 6
		// 7 8 9
		
		Scanner scanner = new Scanner(System.in);
		int dan = 0;
		int i = 0;
		
		System.out.print("단을 입력하세요 : ");
		dan = scanner.nextInt();
		
		// for문 1개 쓰기
		for(i = 1; i<=9; i+=3) {
			System.out.println(dan + " X " + i + " = " + (dan*i) + "\t" +
								dan + " X " + (i+1) + " = " + (dan*(i+1)) + "\t" +
								dan + " X " + (i+2) + " = " + (dan*(i+2)));
		}
		
		// for문 2개 쓰기
		// 1 x 2 = 2 	2 x 2 = 4  3 x 2 = 6
		System.out.println("\nfor문 2개 쓰기");
		
		dan = 2;
		int num1 = 0;
		int num2 = 0;
		
		for(num1 = 1; num1 <=9 ; num1+=3) {
			for(num2 = num1; num2<=num1+2; num2++) {
				System.out.print(dan + " X " + num2 + " = " + (dan*num2) + "\t");
			}System.out.println();
		}
		
		
//		for(int j = 1; j<=9; j=i+3) {
//			for(i = 1; i<=3; i++) {
//			System.out.print(dan + " X " + i + " = " + (dan*i) + "\t");
//			}System.out.println();
//		}
//		
//		
//		for(int j = 1; j<=3; j++) {
//			System.out.print(dan + " X " + j + " = " + (dan*j) + "\t");
//			
//		}System.out.println();
		
		
	}

}
