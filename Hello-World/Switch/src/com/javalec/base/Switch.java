package com.javalec.base;

import java.util.Scanner;

public class Switch {

	public static void main(String[] args) {
		
		// if문과 switch문
		// 선언문
		Scanner scanner = new Scanner(System.in);
		int inputNumber = 0; // 사용자 입력
		String result = ""; // 처리 결과 저장소
		
		// 처리
		System.out.println("숫자를 입력하세요!");
		inputNumber = scanner.nextInt();
		
//		if(inputNumber % 2 == 0) {	// 짝수 판단
//			result = "짝수";
//		}else {						// 홀수 판단
//			result = "홀수";
//		}
		
		switch(inputNumber % 2) {
		case 0:
			result = "짝수";
			break;
		case 1:
			result = "홀수";
			break;
		default:
			break;
		}
		
		// ------------------------ //
		// 2021. 03. 31 Hyogang
		// 기능 : 결과 보여주기 
		// ------------------------ //
		System.out.println("입력하신 숫자 " + inputNumber + "은(는) " + result + "입니다.");
		
		

	}

}
