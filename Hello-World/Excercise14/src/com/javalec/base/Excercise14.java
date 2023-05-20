package com.javalec.base;

import java.util.Scanner;

public class Excercise14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 선언문
		Scanner scanner = new Scanner(System.in);
		int choose = 0;
		String[] bank = {"입금", "출금", "현황", "종료"};
		int guest = 5;
		int[] account = new int[5];
		int money = 0;
		
		
		while(true) {
			for(int i=0; i<bank.length; i++) {
				System.out.println((i+1) + "." + bank[i]);
			}
			// 번호 선택
			System.out.print("번호를 선택하세요!");
			choose = scanner.nextInt();
			
			if(choose==4) {
				System.out.println(">>>>> Thank You <<<<<");
				break;
			}else if(choose==3){
				System.out.println("\t고객명\t잔액");
				System.out.println("\t----\t---");
				for(int i=0; i<account.length; i++) {
					System.out.println("\t" + (i+1) + "\t" + account[i]);
				}
				System.out.println("------------------------------------");
				
			}else {
				// 고객 번호 입력
				System.out.print("고객번호 :");
				guest = scanner.nextInt();
				
				// 금액 입력
				System.out.print("금액 :");
				money = scanner.nextInt();
				
				switch(choose) {
				case 1 : // 입금
					account[guest-1] += money;
					System.out.println("입금 결과 : 고객번호 :" + guest + " 잔액 : " + account[guest-1]);
					System.out.println("------------------------------------");
					break;
					
				case 2 : // 출금
					if(account[guest-1]<money) { //잔액이 부족할 때
						System.out.println("잔액이 부족합니다!");
						System.out.println("------------------------------------");					
					}else {
						account[guest-1] -= money;
						System.out.println("출금 결과 : 고객번호 :" + guest + " 잔액 : " + account[guest-1]);
						System.out.println("------------------------------------");
					}
					break;
				}
			}
		}
		scanner.close();
	}

}
