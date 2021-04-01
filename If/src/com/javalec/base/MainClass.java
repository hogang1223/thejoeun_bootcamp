package com.javalec.base;

import java.util.Iterator;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		
//		int i = 10; // 변수는 위에다 작성! 보통 어디에 쓰는지 주석으로 입력 
//		int j = 20;
//		int k = 10;
		
//		// 비교문 #1		
//		if(i > j) {
//			System.out.println("i가 j보다 큽니다.");
//		}
//				
//		System.out.println(">>> End <<<");
		
		// 비교문 #2  : 비교문으로 입력한 숫자 크기의 차이 구하기. 
//		if(i>j) {
//			System.out.println("i는 j보다 " + (i-j) + "만큼 큽니다.");
//		} else {
//			System.out.println("j는 i보다 " + (j-i) + "만큼 큽니다.");
//		}
//		
		// 비교문 #3 : 비교문으로 크다, 작다, 같다의 3종류 이상의 비교
//		if(i>j) {
//			System.out.println("i가 j보다 큽니다.");
//		}else if(i == j){
//			System.out.println("i가 j는 같습니다.");
//		}else {
//			System.out.println("i가 j보다 작습니다.");
//		}
//		
//		System.out.println(">>> End <<<");
////				
//		int price = 5000;
//		
//		if(price > 8000) {
//			System.out.println("너무 비쌉니다.");
//		}else if(price > 5000) {
//			System.out.println("조금 비쌉니다.");
//		}else if(price>3000) {
//			System.out.println("적당한 금액 입니다.");
//		}else {
//			System.out.println("싼편 입니다.");
//		}
//		
//		System.out.println("구매 하실래요?");
//		
		/*
		 * 성적을 입력하세요 : 90
		 * 귀하의 학점은 A학점 입니다. 
		 * 
		 * 성적을 입력하세요 : 80
		 * 귀하의 학점은 B학점 입니다.
		 * 
		 * 성적을 입력하세요 : 40
		 * 귀하의 학점은 F학점 입니다.
		 */
	
//		Scanner scanner = new Scanner(System.in);
//		
//		System.out.print("성적을 입력하세요 : ");
//		int score = scanner.nextInt();
//		String grade;
//		
//		
//		if(score > 100 || score < 0) { // 중괄호 안써도 상관은 없음 
//			grade = "error";
//		}else if(score >= 90) {
//			grade = "A";
//		}else if(score >= 80) {
//			grade = "B";
//		}else if(score >= 70) {
//			grade = "C";
//		}else if(score >= 60) {
//			grade = "D";
//		}else {
//			grade = "F";
//		}
//		
//		if(grade.equals("error")) { 
//			System.out.println("점수가 잘못 입력 되었습니다.");
//		}else {
//			System.out.println("귀하의 학점은 " + grade + "입니다.");
//		}
		
		Scanner scanner = new Scanner(System.in);
		int kor ;
		int eng ;
		int math ;
		double avg = 0;
		String grade ;

		
				
		System.out.println("국어 점수를 입력 하세요!");
		kor = scanner.nextInt();
		
		System.out.println("영어 점수를 입력 하세요!");
		eng = scanner.nextInt();
		
		System.out.println("수학 점수를 입력 하세요!");
		math = scanner.nextInt();
		
		avg = ((double)kor+eng+math)/3;
		System.out.println("평균점수 : " + avg);
		
		int scores[] = {kor, eng, math};
		String subject[] = {"국어", "영어", "수학"};
		
		for(int i = 0; i<scores.length; i++) {
			if(scores[i]>avg) {				
				grade = subject[i] + " 점수는 평균 보다 높습니다.";
			}else if(scores[i]==avg) {
				grade = subject[i] + " 점수는 평균 점수 입니다.";
			}
			else {
				grade = subject[i] + " 점수는 평균 보다 낮습니다.";
			}

			System.out.println(grade);
		}
		
		
//		if(kor>avg) {
//			System.out.println("국어 점수는 평균 보다 높습니다.");
//		}else if(kor == avg){
//			System.out.println("국어 점수는 평균 점수 입니다.");
//		}else {
//			System.out.println("국어 점수는 평균 보다 낮습니다.");
//		}
//		
//		if(eng>avg) {
//			System.out.println("영어 점수는 평균 보다 높습니다.");
//		}else if(eng == avg){
//			System.out.println("영어 점수는 평균 점수 입니다.");
//		}else {
//			System.out.println("영어 점수는 평균 보다 낮습니다.");
//		}
//		
//		if(math>avg) {
//			System.out.println("수학 점수는 평균 보다 높습니다.");
//		}else if(math == avg){
//			System.out.println("수학 점수는 평균 점수 입니다.");
//		}else {
//			System.out.println("수학 점수는 평균 보다 낮습니다.");
//		}
		

		
				
		
		
	}

}
