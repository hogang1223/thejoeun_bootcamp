package com.javalec.base;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StoreHQ hq = new StoreHQ();
		
		System.out.println("HQ----------------");
		hq.orderKim();
		hq.orderBu();
		hq.orderBi();
		hq.orderSoon();
		hq.orderKong();
		System.out.println();
		
		System.out.println("Shop1----------------");
		StoreHQ sh1 = new Shop1();
		sh1.orderKim();
		sh1.orderBu();
		sh1.orderBi();
		sh1.orderSoon();
		sh1.orderKong();
		System.out.println();
		
		System.out.println("Shop2----------------");
		StoreHQ sh2 = new Shop2();
		sh2.orderKim();
		sh2.orderBu();
		sh2.orderBi();
		sh2.orderSoon();
		sh2.orderKong();
		System.out.println();
		
		System.out.println("Shop3----------------");
		StoreHQ sh3 = new Shop3();
		sh3.orderKim();
		sh3.orderBu();
		sh3.orderBi();
		sh3.orderSoon();
		sh3.orderKong();
		
		StoreHQ[] store = {new StoreHQ(), new Shop1(), new Shop2(), new Shop3()};
		
		for(int i = 0; i<store.length; i++) {
			
			System.out.println("--------------");
			store[i].orderKim();
			store[i].orderBu();
			store[i].orderBi();
			store[i].orderSoon();
			store[i].orderKong();
			
		}
	}

}
