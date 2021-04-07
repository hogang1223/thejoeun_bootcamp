package com.javalec.base;

import java.util.Scanner;
import com.javalec.function.Calc;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		int inputNum = 0;
//		String[] result = new String[9];


		
		System.out.print("구구단을 출력할 숫자를 입력하세요 : ");
		inputNum = scanner.nextInt();
		
		Calc calc = new Calc(inputNum);
		calc.gugudan();
//		System.out.println(result);
	}

}
