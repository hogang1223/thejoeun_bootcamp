package com.javalec.base;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		System.out.println("apple\nBanana\nGrape"); // \n = new line
//		System.out.println("apple\tBanana\tGrape"); // \t = tap
//		System.out.println("apple\"Banana\"Grape"); // \" = "
		
		int dan = 0;
		int num = 0;
		
		for(dan = 2; dan<=9; dan++) {
			System.out.print("--- " + dan + "단 --- \t");
		}System.out.println();
		
		for(num = 1; num<=9; num++) {
			for(dan = 2; dan<=9; dan++) {
				//5의 배수에 *찍기
				if((dan*num)%5==0) { //5의 배수일 때
					System.out.print(dan + " X " + num + " = " + (dan*num) + "*\t");
				}else { //5의 배수가 아닐 때
					System.out.print(dan + " X " + num + " = " + (dan*num) + "\t");
				}
			}System.out.println();
		}
	}

}
