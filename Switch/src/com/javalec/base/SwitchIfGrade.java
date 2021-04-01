package com.javalec.base;

import java.util.Scanner;

public class SwitchIfGrade {

	public static void main(String[] args) {
		// 선언문
		Scanner scanner = new Scanner(System.in);
		int score = 0;
		String grade = "";
		
		// 처리
		System.out.print("성적을 입력하세요:");
		score = scanner.nextInt();
		
		if(score > 100 || score < 0) {
			grade = "error";
		}else {
			switch(score/10) {
			case 10:
			case 9:
				grade = "A";
				break;
			case 8:
				grade = "B";
				break;
			case 7:
				grade = "C";
				break;
			case 6:
				grade = "D";
				break;
			default:
				grade = "F";
				break;
			}
		}
		
		// 결과 보여주기
		if(grade.equals("error")) {
			System.out.println("올바르지 않은 입력입니다.");
		}else {
			System.out.println("귀하의 성적은 " + grade + " 입니다.");
		}

	}

}
