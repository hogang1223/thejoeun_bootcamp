package com.javalec.base;

public class MainClass {

	public static void main(String[] args) {
		// 연산자 종류별 출력 
		int i = 10;
		int j = 2, k = 5;
		
		System.out.println("<-- 산술 연산자 -->");
		System.out.println("덧셈:" + i + " + " + j + " = " + (i+j));
		System.out.println("뺄셈:" + i + " - " + j + " = " + (i-j));
		System.out.println("곱셈:" + i + " * " + j + " = " + (i*j));
		System.out.println("나눈 몫:" + i + " / " + j + " = " + (i/j));
		System.out.println("나눈 나머지:" + i + " % " + j + " = " + (i%j));
		System.out.println();
		
		System.out.println("<-- 자동 증감 연산자 -->");
//		i = i + 1;
//		i += 1;
		i++;
		System.out.println(i);
		
//		j = j-1 ;
//		j-= 1;
		j-- ;
		System.out.println(j);
		System.out.println();
		
		System.out.println("<-- 동등 비교 관계 연산자 -->");
		System.out.println("i == j : " + (i == j));
		System.out.println("i != j : " + (i != j));
		System.out.println("i > j : " + (i > j));
		System.out.println("i < j : " + (i < j));
		System.out.println("i >= j : " + (i >= j));
		System.out.println("i <= j : " + (i <= j));
		System.out.println();
		
		System.out.println("<-- 논리 연산자 -->");
		System.out.println((i > j) && (j > k) );
		System.out.println((i > j) || (j > k) );
		System.out.println();
		
		System.out.println("<-- 삼항 연산자 -->");
		System.out.println(k ==5 ? "k는 5" : "k는 5아님");
		
		// Exercise : i가 홀수인지 짝수인지 출력 하시오
		System.out.println(i%2==0 ? "i는 짝수" : "i는 홀수");
		
		
		
	}

}
