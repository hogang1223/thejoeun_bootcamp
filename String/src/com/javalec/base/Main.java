package com.javalec.base;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str1 = "abcdefg";
		String str2 = "HIJKLMN";
		String str3 = "opqrstu";
		String str4 = " abc def ghi ";
		
		System.out.println(str1.concat(str2)); // 연결
		System.out.println(str1.substring(3)); // 부분 문자열 추출 - 시작점~끝까지
		System.out.println(str1.substring(3, 5)); // 부분 문자열 추출 - 시작점,끝점 설정
		System.out.println(str1.length()); // 문자열 길이
		System.out.println(str1.toUpperCase()); // 대문자로 변환
		System.out.println(str2.toLowerCase()); // 소문자로 변환
		System.out.println(str1.charAt(3)); // index자리의 문자 출력
		System.out.println("------------------------");
		// str1을 알파벳 하나씩 출력하기
		
		for(int i=0; i<str1.length(); i++) {
			System.out.println(str1.charAt(i));
		}
		
		System.out.println(str1.indexOf('c')); // c의 자리(index) 출력
		System.out.println(str1.equals(str3)); // str3와 값이 같은지 비교
		System.out.println(str4.trim()); // 왼쪽 끝과 오른쪽 끝 공백 제거
		System.out.println(str1.replace('a', 'Z')); // 'a'를 'Z'로 바꿔 출력
		System.out.println(str1);
		System.out.println(str1.replaceAll("abc", "ZZZZZZ"));  // 덩어리 변경
		
		
		
		
		
	}

}
