package com.javalec.function;

public class Gugudan2 {

	public Gugudan2() {
		// TODO Auto-generated constructor stub
	}
	
	public void calcGugudan2(int num) {
		int result = 0;
		String sResult = "";
		String evenResult = "";
		String oddResult = "";
		
		for(int i=1; i<=9; i++) {
			
			result = num * i;
//			sResult = num + " x " ;
			
			if(i%2==0) {
				evenResult = num + " x * = " + result ;
				System.out.println(evenResult);
			}else {
				oddResult = num + " x " + i + " = " + result;
				System.out.println(oddResult);
			}
//			if(i%2==0) {
//				System.out.println(sResult + "* = " + result);
//			}else {
//				System.out.println(sResult + i + " = " + result);
//			}
		}
	}
	
}
