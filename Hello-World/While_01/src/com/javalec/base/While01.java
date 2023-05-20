package com.javalec.base;

public class While01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// 1~5까지의 합을 while문으로 작성
		
		int num = 1;
		int tot = 0;
		
		while(num<=5){
			tot += num;
			num++;
		}System.out.println(tot);
	}

}
