package com.javalec.com;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 1부터 100까지의 합을 구하기
		
		int tot = 0;
		
//		for(int i = 1; i <= 100; i++) {
//			tot +=i;
//		}
		
		int i = 1;
		
//		while(i <= 100) {
//			tot += i;
//			i++ ;
//		}
		
//		while(true) {
//			tot += i;
//			i++ ;
//			if(i > 100) {
//				break;
//			}
//		}
//		System.out.println(tot);
		
		while(true) {
			if(i % 2 == 1) {
				System.out.println(i);
			}else {
//				continue; // 밑이 아니라 위로 다시 올라감
			}
			i++;
			
			if(i>10) {
				break;
			}
		}
		
	}

}
