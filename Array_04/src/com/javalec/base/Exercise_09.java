package com.javalec.base;

import java.util.Scanner;

public class Exercise_09 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 학생수가 4명이고 과목이 3과목일 경우의 점수 합계와 평균 구하기 기능 추가
		
		Scanner scanner = new Scanner(System.in);
		String subject[] = {"Korean", "English", "Mathmatics"};
		int studentNum = 4;
		int[] korScore = new int[studentNum]; 
		int[] engScore = new int[studentNum]; 
		int[] mathScore = new int[studentNum]; 
		int sum = 0;
		int avg = 0;
		
		// 국어 성적 입력
		System.out.println(subject[0]+"의 성적을 입력 : ");
		for(int i = 0; i<studentNum; i++) {
			System.out.print("No" + (i+1) + "의 성적은 : ");
			korScore[i] = scanner.nextInt();
		}
		
		// 영어 성적 입력
		System.out.println(subject[1]+"의 성적을 입력 : ");
		for(int i = 0; i<studentNum; i++) {
			System.out.print("No" + (i+1) + "의 성적은 : ");
			engScore[i] = scanner.nextInt();
		}
		
		// 수학 성적 입력
		System.out.println(subject[2]+"의 성적을 입력 : ");
		for(int i = 0; i<studentNum; i++) {
			System.out.print("No" + (i+1) + "의 성적은 : ");
			mathScore[i] = scanner.nextInt();
		}
		
		// 출력
		for(int sub = 0; sub<subject.length; sub++) {	
			System.out.print("\t" + subject[sub] + "\t");
		}System.out.println("Total\t\tAverage");
		for(int i = 0; i<studentNum; i++) {
			sum = korScore[i] + engScore[i] + mathScore[i] ;
			avg = sum / subject.length ;
			System.out.println("No" + (i+1) + "\t  " +  korScore[i] + "\t\t  " + engScore[i] + "\t\t  " + mathScore[i] + "\t\t  " + sum + "\t\t" + avg);
		}

		
	}

}
