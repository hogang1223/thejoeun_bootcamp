package com.javalec.base;

import java.util.Scanner;

public class Exercise_08 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 몇 개의 숫자를 입력한지 결정한 후 숫자를 입력하고
		// 이 중 최대값의 위치와 최대값을 구하라.
		
		Scanner scanner = new Scanner(System.in);
		int inputNum = 0;
		int[] numArray;
		int maxIndex = 0;
				
		System.out.print("입력할 숫자의 갯수 ? (100개 미만) : ");
		inputNum = scanner.nextInt();
		
		numArray = new int[inputNum];
		
		System.out.println("5개의 숫자를 입력하세요!");
		for(int i = 0; i<inputNum; i++) {
			numArray[i] = scanner.nextInt();	
			
			if(numArray[i]>numArray[maxIndex]) {
				maxIndex = i;
			}
		}
		
		System.out.println("입력한 숫자 중 최대값은 " + numArray[maxIndex] + "이고 " + (maxIndex+1) + "번째 값입니다.");
		
	}

}
