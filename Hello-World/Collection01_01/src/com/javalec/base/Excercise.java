package com.javalec.base;

import java.util.ArrayList;

public class Excercise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> evenNum = new ArrayList<Integer>();
		int tot = 0;
		
		for (int i=2; i<=100; i+=2) {
			evenNum.add(i);
		}
		for(int i=0; i<50; i++) {
			tot += evenNum.get(i);
		}
		System.out.println(tot);
		
		
		
	}

}
