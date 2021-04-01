package com.javalec.base;

import java.util.Scanner;

public class BmiCalculation {

	public static void main(String[] args) {
		// BMI지수= 몸무게(kg) ÷ (신장(m) × 신장(m))

		int weight ;
		int height ;
		double bmi ; 
		String strBmi;
		int bestWeight;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("몸무게를 입력하세요(kg) : ");
		weight = scanner.nextInt();
		
		System.out.print("신장(키)을 입력하세요(cm) : ");
		height = scanner.nextInt();
		double heightMeter = (double)height/100;
		
		bmi = (double)(int)(weight /(heightMeter * heightMeter)*10)/10;
		
		if(bmi<18.5) {
			strBmi = "저체중";
		}else if(bmi<23) {
			strBmi = "정상체중";
		}else if(bmi<25) {
			strBmi = "과체중";
		}else if(bmi<30) {
			strBmi = "경도비만";
		}else
			strBmi = "고도비만";
		
		System.out.println("당신의 bmi 지수는 " + bmi + "으로 " +strBmi + "입니다." );
		
		bestWeight = (int)(21 * heightMeter * heightMeter);
		System.out.println("최적의 표준체중은 " + bestWeight + "kg 입니다.");
		
	}

}
