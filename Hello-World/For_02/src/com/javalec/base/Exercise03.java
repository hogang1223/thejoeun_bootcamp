package com.javalec.base;

public class Exercise03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		int dan = 0;
		
		for(dan = 2; dan<=9; dan++) {
			System.out.println(dan + " X " + 1 + " = " + (dan*1) +"\t" +
							dan + " X " + 2 + " = " + (dan*2) + "\t" +
							dan + " X " + 3 + " = " + (dan*3));
		}
		
		// for문 2개 쓰기
		
		System.out.println("\nfor문 2개 쓰기");
		dan = 0; 
		int num = 0;
		
		for(dan = 2; dan <=9; dan++) {
		
			for(num =1; num<=3; num++) {
				System.out.print(dan + " X " + num + " = " + (dan*num) + "\t");
			}System.out.println();
		}
		
		
	}

}
