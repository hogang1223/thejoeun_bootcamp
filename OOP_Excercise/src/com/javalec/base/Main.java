package com.javalec.base;

import com.javalec.function.AddNum;
import com.javalec.function.Calc;
import com.javalec.function.Calc2;
import com.javalec.function.Calc3;
import com.javalec.function.DivideNum;
import com.javalec.function.MultiplyNum;
import com.javalec.function.SubtractNum;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num1 = 100, num2 = 20;
		
		// 더하기
		AddNum addNum = new AddNum();
		int addResult =  addNum.addAction(num1, num2);
		System.out.println(addResult);
		
		// 빼기
		SubtractNum subtractNum = new SubtractNum();
		int subtractResult =  subtractNum.subtractAction(num1, num2);
		System.out.println(subtractResult);
		
		// 곱하기
		MultiplyNum multiplyNum = new MultiplyNum();
		int multiplyResult =  multiplyNum.multiplyAction(num1, num2);
		System.out.println(multiplyResult);
		
		// 나누기
		DivideNum divideNum = new DivideNum();
		int divideResult =  divideNum.divideAction(num1, num2);
		System.out.println(divideResult);
		
		System.out.println("-------------------------");
		
		// 하나의 Calc class
		Calc calc = new Calc();
		
		addResult = calc.add(num1, num2);
		subtractResult = calc.subtract(num1, num2);
		multiplyResult = calc.multiply(num1, num2);
		divideResult = calc.divide(num1, num2);

		System.out.println(addResult);
		System.out.println(subtractResult);
		System.out.println(multiplyResult);
		System.out.println(divideResult);
		
		System.out.println("-------------------------");
		
		// Constructor로
		System.out.println("------String-----");
		num1 = 2; 
		num2 = 3;
		Calc2 calc2 = new Calc2(num1, num2);
		
		String sAddResult = calc2.addCalc2();
		String sSubstractResult = calc2.subtractCalc2();
		String sMultiplyResult = calc2.multiplyCalc2();
		String sDivideResult = calc2.divideCalc2();
		System.out.println(sAddResult);
		System.out.println(sSubstractResult);
		System.out.println(sMultiplyResult);
		System.out.println(sDivideResult);

		
//		addResult = calc2.addCalc2();
//		subtractResult = calc2.subtractCalc2();
//		multiplyResult = calc2.multiplyCalc2();
//		double divideResult2 = calc2.divideCalc2();
		
//		calc2.print(calc2.addCalc2());

		
//		System.out.println(addResult);
//		System.out.println(subtractResult);
//		System.out.println(multiplyResult);
//		System.out.println(divideResult2);

//		System.out.println("-------------------------");
//		
//		num1 = 0;
//		num2 = 0;
//		Calc3 calc3 = new Calc3(num1, num2);
//		calc3.addCalc3();
//		calc3.substractCalc3();
//		calc3.multiplyCalc3();
//		calc3.divideCalc3();
		
	}

}
