package com.javalec.base;

import java.util.Scanner;

import com.javalec.function.Gugudan2;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		Gugudan2 gugudan2 = new Gugudan2();
		
		int inputNum = 0;
		
		System.out.print("구구단을 출력할 숫자를 입력하세요 : ");
		inputNum = scanner.nextInt();
		scanner.close();
		gugudan2.calcGugudan2(inputNum);
		
		
		
	}

}
