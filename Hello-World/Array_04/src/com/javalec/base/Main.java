package com.javalec.base;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		String[] name = {"James", "Cathy", "Kenny", "Martin", "Crystal"};
		int[] height = new int[name.length];
		int sum = 0;
		double avg = 0.0;
		int max = 0, min = 0;
		
		
		// 사용자 입력
		for(int i=0; i<name.length; i++) {
			System.out.print(name[i] + "의 신장을 입력하세요!");
			height[i] = scanner.nextInt();
			sum += height[i];
			
			// 크기 비교
			
			if(height[i]>height[max]) {
				max = i ;
			}
			if(height[i]<height[min]) {
				min = i ;
			}
			
		}
		// 평균 구하기
		
		avg = (double)sum / height.length;
		System.out.println("평균신장은 : " + String.format("%.2f", avg));
		
		System.out.println("가장 큰 학생은 " + name[max] + "이고 그 학생의 키는 " + height[max]);
		System.out.println("가장 작은 학생은 " + name[min] + "이고 그 학생의 키는 " + height[min]);
	}

}
