package com.javalec.base;

public class Main {

	public static void main(String[] args) {

//		APhone aPhone = new APhone();
//		BPhone bPhone = new BPhone();
//		CPhone cPhone = new CPhone();
		
		// 이렇게 하면 배열로 사용 가능
		SmartPhone aPhone = new APhone();
		SmartPhone bPhone = new BPhone();
		SmartPhone cPhone = new CPhone();
		
		SmartPhone[] smartPhone = {aPhone, bPhone, cPhone};
		
		for(int i=0; i<smartPhone.length; i++) {
			smartPhone[i].callSenderReceiver();
			smartPhone[i].telMethod();
			smartPhone[i].tvRemoteController();
			System.out.println("-----------------------");
		}
		
//		aPhone.callSenderReceiver();
//		aPhone.telMethod();
//		aPhone.tvRemoteController();
//		
//		bPhone.callSenderReceiver();
//		bPhone.telMethod();
//		bPhone.tvRemoteController();
//		
//		cPhone.callSenderReceiver();
//		cPhone.telMethod();
//		cPhone.tvRemoteController();
		
		
	}

}
