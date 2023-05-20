package com.javalec.base;

import java.util.Scanner;

public class Repeat10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		int[] score = new int[10];
		int[] hiscore = new int[10];
		

		// 점수 입력
		System.out.println("Input score : ");
		for(int i=0; i<score.length; i++) {
			System.out.print(i+1 + "의 score :");
			score[i] = scanner.nextInt();
			hiscore[score[i] / 10]++ ;
		}
		scanner.close();
		
		// 출력
		for(int i=(score.length-1); i>=0; i--) {
			System.out.print(String.format("%2d : ", i*10));
			for(int j=1; j<=hiscore[i]; j++) {
				System.out.print("#");
			}System.out.println();
		}
		
	}

}
