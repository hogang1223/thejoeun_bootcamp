package com.javalec.function;

public class Calc {

	int num = 0;
	int result = 0;
	String array[] ;
	String dan;
	int i = 1;
	
	public Calc() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Calc(int num) {
		super();
		this.num = num;
	}


	public void gugudan() {
		
		for(i = 1; i<=9; i++) {
			result = num * i;
			dan = num + " x " + i + " = " + result; 
			System.out.println(dan);
		}

	}
	
	
}
