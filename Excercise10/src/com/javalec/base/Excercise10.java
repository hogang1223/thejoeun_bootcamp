package com.javalec.base;

import java.util.Scanner;

public class Excercise10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// 	10개의 점수(0점부터 99점)을 입력받아 점수의 분포를 10점 간격의 등급으로 된 히스토그램을 표시하라
		
		// 선언자
		Scanner scanner = new  Scanner(System.in);
		int[] score = new int[10];
		int[] hiScore = new int[score.length];
		
		// hisScore에 대한 초기 작업
		for(int i=0; i<hiScore.length; i++) {
			hiScore[i] = 0;
		}
		
		
		// 점수 입력
		System.out.println("Input score : ");

		for(int i=0; i<score.length; i++) {
			System.out.print((i+1) + "의 score :");
			score[i] = scanner.nextInt();
			hiScore[score[i] / 10] ++ ;
		}
		
//		//확인하기 위한 임시 source
//		for(int i=0; i<hiScore.length; i++) {
//			System.out.println(i + ":" + hiScore[i]);
//		}

		// 히스토그램
		System.out.println("----------- Histogram ----------");
		
		for(int i=(score.length-1); i>=0; i--) {
			System.out.print(String.format("%2d : ", i*10));
			
			for(int j=1; j<=hiScore[i]; j++) {
				System.out.print("#");
			}System.out.println();
		}
		
		
		
//		for(int i=90; i>=0; i-=10) {
//			System.out.print(String.format("%2d", i) + " : ");
//			
//			for(int j=0; j<hiScore.length; j++) {
//				hiScore[j] = score[j] / 10 * 10 ;
//				
//				if(hiScore[j] == i) {
//					System.out.print("#");
//				}
//			}System.out.println();
//		}
		
		
	}
	
}
