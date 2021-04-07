package com.javalec.base;

public class AddCalc {

	public AddCalc() {
		// TODO Auto-generated constructor stub
	}
	
	// Method
	public int[] sumCalc(int[] arr) {
		int[] incNum = new int[arr.length];
		for(int i=0; i<arr.length; i++) {
			incNum[i] = arr[i] + 1;
		}
		return incNum ;
	}
}
