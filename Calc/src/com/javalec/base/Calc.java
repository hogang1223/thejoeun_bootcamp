package com.javalec.base;

import java.util.Scanner;

public class Calc {

	public static void main(String[] args) {
		
		// 사용자의 입력 데이터 받기
		Scanner scanner = new Scanner(System.in);
//		
//		System.out.print("숫자를 입력하세요 : ");
//		int num = scanner.nextInt(); // 객체.Method(기능)
//		
//		System.out.println("사용자 입력 숫자는 " + num + "입니다.");
//		
		/* 
		 * Exercise
		 * 숫자를 입력해 주세요 : 12
		 * 입력하신 숫자는 3의 배수 입니다.
		 * 
		 * 숫자를 입력해 주세요 : 10
		 * 입력하신 숫자는 3의 배수가 아닙니다.
		 */
		
		
		
//		System.out.print("숫자를 입력해 주세요 : ");
//		int num1 = scanner.nextInt();
//		System.out.print("입력하신 숫자는 3의 배수");
//		System.out.println(num1 % 3 == 0 ? " 입니다." : "가 아닙니다.");
//		System.out.println();
//		
//		System.out.print("숫자를 입력해 주세요 : ");
//		int num2 = scanner.nextInt();
//		System.out.println(num2 % 3 == 0 ? "입력하신 숫자는 3의 배수 입니다." : "입력하신 숫자는 3의 배수가 아닙니다.");
//		System.out.println();
		
		System.out.print("숫자를 입력해 주세요 : ");
		int num3 = scanner.nextInt();
		System.out.println(num3 % 2 == 0? "2의 배수" : num3 % 3 == 0 ? "3의 배수" : "모름");
				
		
		
		
	}

}
