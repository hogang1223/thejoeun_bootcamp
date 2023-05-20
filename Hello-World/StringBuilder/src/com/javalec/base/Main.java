package com.javalec.base;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String string = new String ("abcdef");
		
		StringBuilder stringBuilder = new StringBuilder("abcdef");
		stringBuilder.append("hijklmn"); // 연결
		System.out.println(stringBuilder);
		
		stringBuilder.insert(3, "zzz"); // 3번째에 "zzz"추가
		System.out.println(stringBuilder);

		stringBuilder.delete(3, 6); // 3번~6번 전까지 삭제
		System.out.println(stringBuilder);

		// stringBuffer 써도 동일
		StringBuffer stringBuffer = new StringBuffer("abcdef");
		stringBuffer.append("hijklmn"); // 연결
		System.out.println(stringBuffer);
		
		stringBuffer.insert(3, "zzz"); // 3번째에 "zzz"추가
		System.out.println(stringBuffer);
		
		stringBuffer.delete(3, 6); // 3번~6번 전까지 삭제
		System.out.println(stringBuffer);
		
		
	}

}
