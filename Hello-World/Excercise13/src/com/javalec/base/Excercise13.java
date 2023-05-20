package com.javalec.base;

import java.util.Scanner;

public class Excercise13 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 몇개의 숫자를 입력할지 결정한 후 숫자를 입력하고 이 중 필요없는 숫자의 위치를 정해 삭제
		
		Scanner scanner = new Scanner(System.in);
		int inputNum = 0;
		int [] arrNum ;
		int index = 0;
		int newIndex = 0;
		int [] newArr ;
		int newNum = 0;
		
		// 사용자 입력 - 입력할 숫자의 갯수
		System.out.print("입력할 숫자의 갯수? :");
		inputNum = scanner.nextInt();
		
		// 사용자 입력 - 숫자 입력
		arrNum = new int[inputNum];
		System.out.println(inputNum + "개의 숫자를 입력하세요! :");
		for(index = 0; index <inputNum; index++) {
			System.out.print((index+1) + "의 숫자 :");
			arrNum[index] = scanner.nextInt();
		}
		
		// 숫자 삽입 위치
		
		System.out.print("몇번째의 숫자를 삭제 하시겠습니까? :");
		newIndex = scanner.nextInt();
		
		
		newArr = new int[(arrNum.length)-1];

		
		// 배열 재정렬하기
		for(int i=0; i<newArr.length; i++) {
			newArr[i] = arrNum[i];
		}
		/*
		 * newIndex -> 3번째 삭제 (index = 2)
		 * arrNum[3] = 44 -> newArr[2]
		 * arrNum[4] = 55 -> newArr[3]
		 */
		
		for(index=inputNum-1; index>newIndex-1; index--) {
			newArr[index-1] = arrNum[index]; 
		}		
		
		
		// 결과 출력
		System.out.println("------- 결과 -------");
		for(index = 0; index<newArr.length; index++) {
			System.out.println(newArr[index]);
		}
	}

}
