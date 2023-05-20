package com.javalec.base;

import java.util.Scanner;

public class Excercise12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 몇개의 숫자를 입력할지 결정한 후 숫자를 입력하고 입력된 위치에 새로운 숫자를 삽입
		
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
		newArr = new int[(arrNum.length)+1];
		for(index=0; index<inputNum; index++) {
			newArr[index] = arrNum[index];
		}
		// newArr = {11 22 44 55};
		
		System.out.print("숫자를 삽입하고자 하는 위치는 ? :");
		newIndex = scanner.nextInt();
		
		// 삽입 하고자 하는 숫자
		System.out.print("삽입하고자 하는 숫자는? :");
		newNum = scanner.nextInt();
		
		newArr[newIndex-1] = newNum;
		
		// 배열 재정렬하기
		for(index=inputNum; index>=newIndex; index--) {
//			System.out.println(arrNum[index-1]);
			newArr[index] = arrNum[index-1];
		}
		
		
		
		// 결과 출력
		System.out.println("------- 결과 -------");
		for(index = 0; index<newArr.length; index++) {
			System.out.println(newArr[index]);
		}
		
	}

}
