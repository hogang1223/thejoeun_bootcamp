package com.javalec.function;

public class AddNum {

	// Field
	public int num1, num2 ;
	
	// Constructor
	public AddNum() {
		
	}

	public AddNum(int num1, int num2) {
		super();
		this.num1 = num1;
		this.num2 = num2;
	}



	// Method
	public int addAction(int num1, int num2){
		int resultNum = num1 + num2;
		
		return resultNum;
	}
	
	// 출력하는 메소드
	public void addPrint(int num1, int num2) {
		System.out.println(num1 + num2);
	}
	
	public int addAction2(){
		
		return num1 + num2;
	}
	
}
