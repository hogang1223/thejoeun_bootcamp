package com.javalec.base;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
//		Scanner scanner = new Scanner(System.in);
//		
//		System.out.print("단을 입력하세요 : ");
//		int dan = scanner.nextInt();
//		
//		
//		for(int i=1; i<=9; i++) {
//			System.out.println(dan + " X " + i + " = " + String.format("%02d",(dan*i))); // "%2d" -2자리의 십진수, "%02d"빈공간엔 0 입력
//		}

//		// 2단 ~ 9단 자동 출력
//		int dan = 0;
//		
//		for(dan = 2; dan<=9; dan++) {
////			System.out.println(dan);
//			System.out.println("--- " + dan + "단 ---");
//			for(int i=1; i<=9; i++) {
//				System.out.println(dan + " X " + i + " = " + String.format("%02d",(dan*i)));
//			}System.out.println("----------");
//		}
		
//		Scanner scanner = new Scanner(System.in);
//		int startDan = 0, endDan = 0;
//		int temp = 0;
//		
//		System.out.print("구구단 시작 범위 입력 : ");
//		startDan = scanner.nextInt();
//		System.out.print("구구단 끝 범위 입력 : ");
//		endDan = scanner.nextInt();
//		
//		// 시작과 끝을 자동으로 변환
//		if(startDan > endDan) {
//			temp = startDan; startDan = endDan; endDan = temp;
//		}
//		for(int dan = startDan; dan<=endDan; dan++) {
//			System.out.println("--- " + dan + "단 ---");
//			for(int j=1; j<=9; j++) {
//					System.out.println(dan + " X " + j + " = " + String.format("%2d",(dan * j)));
//			}System.out.println("");
//		}
		
//		// 사용자의 입력정보 Check
//		if(startDan > endDan) {
//			System.out.println("입력 정보를 확인하세요.");
//		}
//		// 결과 보여주기
//		else {
//			for(int dan = startDan; dan<=endDan; dan++) {
//				System.out.println("--- " + dan + "단 ---");
//				for(int j=1; j<=9; j++) {
//					System.out.println(dan + " X " + j + " = " + String.format("%2d",(dan * j)));
//				}System.out.println("");
//			}
//		}
		
		
		Scanner scanner = new Scanner(System.in);
		int startDan = 0, endDan = 0;
		int temp = 0;
		
		System.out.print("구구단 시작 범위 입력 : ");
		startDan = scanner.nextInt();
		System.out.print("구구단 끝 범위 입력 : ");
		endDan = scanner.nextInt();
		
		// 시작과 끝을 자동으로 변환
		if(startDan > endDan) {
			temp = startDan; startDan = endDan; endDan = temp;
		}
		
		// 홀수 단일 경우 홀수 값만 출력, 짝 단일 경우 짝수 값만 출력
		for(int dan = startDan; dan<=endDan; dan++) {
			System.out.println("--- " + dan + "단 ---");
			if(dan%2==0) {	// 짝수 단일 경우
				for(int num=2; num<=8; num+=2) {
					System.out.println(dan + " X " + num + " = " + String.format("%2d",(dan * num)));
				}
			}else {			// 홀수 단일 경우
				for(int num = 1; num<=9; num+=2) {
					System.out.println(dan + " X " + num + " = " + String.format("%2d",(dan * num)));
				}
			}
			
			
			
//			for(int j=1; j<=9; j++) {
//				
//					System.out.println(dan + " X " + j + " = " + String.format("%2d",(dan * j)));
//			}System.out.println("");
		}
	}
}
