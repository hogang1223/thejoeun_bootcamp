package com.javalec.base;

import java.util.Scanner;

public class Gugu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		int dan = 0;
//		Scanner scanner = new Scanner(System.in);
//		
//		System.out.print("원하시는 단을 입력하세요 : ");
//		dan = scanner.nextInt();
//		
//		for(int i = 1; i <= 9; i++) {
//			System.out.println(dan + " X " + i + " = " + (dan * i));
//		}
		
//		// Exercise 1
//		// 원하시는 단을 입력하세요 : 7
//		// 7 X 2 = 14
//		// 7 X 4 = 28
//		// 7 X 6 = 42
//		// 7 X 8 = 56
//		
//		int dan = 0;
//		Scanner scanner = new Scanner(System.in);
//		
//		System.out.print("원하시는 단을 입력하세요 : ");
//		dan = scanner.nextInt();
//		
//		for(int i = 2; i <= 8; i+=2) {
//			System.out.println(dan + " X " + i + " = " + (dan*i));
//		}

		
		// Exercise 2
		// 원하시는 단을 입력하세요 : 7
		// 7 X _ = 7
		// 7 X 2 = 14
		// 7 X _ = 21
		// 7 X 4 = 28
		// 7 X _ = 35
		// 7 X 6 = 42
		
		int dan = 0;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("원하시는 단을 입력하세요 : ");
		dan = scanner.nextInt();
		
		for(int i = 1; i <= 9; i++) {
			if(i%2==0) {
				System.out.println(dan + " X " + i + " = " + (dan*i));
			}else {
				System.out.println(dan + " X _ = " + (dan*i));
			}
		}
		
		
		
	}

}
