package com.javalec.base;

import java.util.Scanner;

public class For {

	public static void main(String[] args) {

		// 반복문
		for(int i = 1; i <= 10; i++) {
			System.out.println(i);
		}
		
		// 1부터 10까지 합계 구하기
		int tot = 0; // 누적 변수
		
		for(int i = 1; i<= 10; i++) {
			tot += i;
		}
		System.out.println("1부터 10까지의 합은 " + tot + "입니다.");
		
		// 1부터 100까지의 수 중 짝수의 합과 홀수의 합을 구하시도 (단 if문 사용)
		int totEven = 0, totOdd = 0;
		for(int i=0; i<=100; i++) {
			if(i%2==0) {
				totEven += i;
			}else {
				totOdd += i;
			}
		}System.out.println("짝수의 합 : " + totEven);
		System.out.println("홀수의 합 : " + totOdd);
		
		// 입력한 수의 factorial 구하기
		Scanner scanner = new Scanner(System.in);
		
		int num = 0;
		int factorialValue = 1;
		
		System.out.print("Input your decimal no. : ");
		num = scanner.nextInt();
		
		for(int i=1; i<=num; i++) {
			factorialValue *= i;
		}
		
		System.out.println(num + "'s factorial value = " + factorialValue);

	}

}
