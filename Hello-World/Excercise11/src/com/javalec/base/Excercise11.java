package com.javalec.base;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Excercise11 {
	static int searchNum = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// 몇개의 숫자를 입력 할지 정한 후, 숫자를 입력하고 이중 검색을 원하는 숫자의 위치 파악
		
		Scanner scanner = new Scanner(System.in);
		int inputNum = 0;
		int[] arrNum;
//		int searchNum = 0;
		int count = 0;
		int i=0;
		String message = "는 존재하지 않습니다.";
		
		
		// 사용자 입력 - 입력 갯수
		System.out.print("입력할 숫자의 갯수? :");
		inputNum = scanner.nextInt();
		
		// 사용자 입력 - 갯수만큼 숫자 입력
		arrNum = new int[inputNum];
		
		System.out.println(inputNum + "개의 숫자를 입력하세요!:");
		
		for(i=0; i<inputNum; i++) {
			System.out.print(i+1 +"의 숫자 :");
			arrNum[i] = scanner.nextInt(); 
		}
		
		// 사용자 입력 - 검색할 숫자 입력
		System.out.print("검색할 숫자는? : ");
		searchNum = scanner.nextInt();
		
		// 위치 찾기 - 함수 사용
//		if(IntStream.of(arrNum).anyMatch(x -> x == searchNum)) {
//			System.out.println(searchNum + "의 위치는 " + i +"번째 입니다.");
//		}else {
//			System.out.println(searchNum + "은 존재하지 않습니다.");	
//		}
		
		// 위치 찾기 - count
//		for(count=0; i<inputNum; count++) {
//			if(searchNum == arrNum[i]) {
//				System.out.println(searchNum + "의 위치는 " + (i+1) +"번째 입니다.");
//				break;
//			}
//		}
//		if(count == inputNum) {
//			System.out.println(searchNum + "은 존재하지 않습니다.");				
			
			
			
		// 위치 찾기 - message
		for(i=0; i<inputNum; i++) {
			if(searchNum == arrNum[i]) {
				message = ("의 위치는 " + (i+1) + "번째 입니다.");
				break;
			}
		}
		System.out.println(searchNum + message);
		
	}

}
