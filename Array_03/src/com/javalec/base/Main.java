package com.javalec.base;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] array1 = {10, 20, 30, 40, 50};
		int[] array2 = array1;
		
		for(int i=0; i<array1.length; i++) {
			System.out.print("array1: " + array1[i] + "/ \t");
			System.out.println("array2: " + array2[i]);
		}
		
		array2[0] = 100;
		array2[1] = 200;
		array2[2] = 300;
		array2[3] = 400;
		array2[4] = 500;
		
		for(int i=0; i<array1.length; i++) {
			System.out.print("array1: " + array1[i] + "/ \t");
			System.out.println("array2: " + array2[i]);
		}
		
		int[] array3 = new int[array1.length];
//		array3 = array1; // Call by reference
		
		for(int i=0; i<array1.length; i++) { // Call by value
			array3[i] = array1[i];
		}
		
		array3[0] = 10;
		array3[1] = 20;
		array3[2] = 30;
		array3[3] = 40;
		array3[4] = 50;
		
		for(int i=0; i<array1.length; i++) {
			System.out.print("array1: " + array1[i] + "/ \t");
			System.out.println("array3: " + array3[i]);
		}
	}

}
