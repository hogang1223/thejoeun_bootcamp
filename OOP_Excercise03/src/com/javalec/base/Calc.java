package com.javalec.base;

public class Calc {
	
	int [] num ;
	
	public Calc() {
		// TODO Auto-generated constructor stub
	}
	
	public Calc(int[] num) {
		super();
		this.num = num;
	}

	public int addAction() {
		 int add = 0;
		 for(int i=0; i<num.length; i++) {
			 add += num[i];
		 }
		return add;
	}
}
