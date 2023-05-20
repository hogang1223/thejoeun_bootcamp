package com.javalec.base;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		// Random
//		for(int i = 1; i<=6; i++) {
//			double d = Math.random();
//			int a = (int) (d*3) + 1;
//			System.out.println(a);
//		}
		
//		Random random = new Random();
//		int i = random.nextInt(3);
//		System.out.println(i+1);
		
		// 가위 : 1 , 바위 : 2 , 보 : 3 ? 3
		//       1        2        3
//		Scanner scanner = new Scanner(System.in);
//		String[] game = {"가위","바위", "보"};
//		
//		int myNum = 0;
//		Random random = new Random();
//		int comNum = random.nextInt(3);
//
//		
//		System.out.print("(가위:1, 바위:2, 보:3) ? ");
//		myNum = scanner.nextInt();
//		
//		System.out.println("com : " + (comNum+1));
//		
//		if(myNum==(comNum+1)) {
//			System.out.println("비겼습니다!");			
//		}else if(myNum>(comNum+1)|| (myNum==3 &&(comNum+1)==1)) {
//			System.out.println("당신이 이겼습니다!");
//		}else {
//			System.out.println("당신이 졌습니다!");
//		}
		
		Scanner scanner = new Scanner(System.in);
		String[] game = {"가위","바위", "보"};
		String my = "";
		Random random = new Random();
		int comNum = random.nextInt(3);
		int myNum = 0;
		
		
		System.out.print("(가위, 바위, 보) ? ");
		my = scanner.nextLine();
		for(myNum=0; myNum<game.length; myNum++) {
			if(game[myNum].equals(my)) {
				break;
			}
		}
		
		System.out.println("com : " + game[comNum]);
		
		if(myNum==comNum) {
			System.out.println("비겼습니다!");			
		}else if(myNum>comNum|| (myNum==0 &&comNum==2)) {
			System.out.println("당신이 이겼습니다!");
		}else{
			System.out.println("당신이 졌습니다!");
		}
		scanner.close();
	}

}
