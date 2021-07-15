//
//  main.swift
//  Day02
//
//  Created by hyogang on 2021/07/15.
//

import Foundation

var str1: String
str1 = "Apple"
str1 = "Google"
//str1 = nil
print(str1)

var str2 : String?
str2 = "Apple"
str2 = "Google"
str2 = "123"
//str2 = nil
print(str2!)

var int1 : Int  = Int(str2!)!

print(int1 + 10)

// 비교 연산자
print(1 == 1)

// Tuple에서의 비교 연산
print((1, "zebra") < (2, "apple"))
print((3, "apple") < (3, "bird"))

// 삼항 조건 연산자
let contentHeight = 40
let hasHeader = true

let rowHeight = contentHeight + (hasHeader ? 50 : 20) // ? True : False
print(rowHeight)

// 범위 연산자
// 닫힌 범위 연산자
for i in 1...10{
    print(i)
}

var sum: Int = 0
for i in 1...10{
    sum += i
}

print("1~10의 합계는 \(sum) 입니다.")

for i in 1...9{
    print("5 X \(i) = \(i*5)")
}

for i in 1..<10{
    print("5 X \(i) = \(i*5)")
}

let names = ["Anna", "Alex", "Brian", "Jack"]

for i in 0..<names.count{
    print("Person \(i+1) is called \(names[i])")
}

// Optional
// : 값이 있을수도 있고, 없을 수도 있음
// : nill값을 포함 할 수도 있고, 포함 안할 수도 있음

var constantNum: Int? = 100
constantNum = nil

var constantNum2 : Int! = 100
print(constantNum2!)
print(constantNum2 + 10)


// Optioanl Unwrapping
var myName: String? = nil

// Optional Binding
if let name: String = myName{
    print(name)
}else{
    print("myName is nil")
}

var yourName: String! = nil

if let name: String = yourName{
    print(name)
}else{
    print("yourName is nil")
}

var myName2: String? = "James"
var yourName2: String? = nil

if let name = myName2, let friend = yourName2{
    print("\(name) and \(friend)") // yourName2가 nil이라 출력 안함
}

// Force Unwrapping
print(myName2!)
myName2 = nil
print(myName2 as Any)

// Collection : 여러값들을 묶어서 하나의 변수로 사용
/*
 Array : 순서가 있는 리스트 컬렉션
 Dictionary : Key와 Value의 쌍으로 이루어진 컬렉션
 Set : 순서가 없고 멤버가 유일한 컬렉션, 집합연산
 */

// Array
// 빈 Array 생성
//var intVariable : Array<Int> = Array<Int>() // class = constructor
//var intVariable : [Int] = [Int]()
var intVariable : [Int] = [] // 보통 이거 사용

intVariable.append(1)
intVariable.append(10)
intVariable.append(100)
intVariable.append(1000)

print(intVariable)
print(intVariable.contains(100))
print(intVariable.contains(90))

print(intVariable[0])
print(intVariable[0...2])
print(intVariable[0...2][0]) // 2차원 아님 0번째꺼 불러온다
intVariable.remove(at: 0)
intVariable.removeLast()
intVariable.removeAll()
print(intVariable)

intVariable = []

// 기본값으로 배열 생성하기
var threeDouble = Array(repeating: 0.0, count: 3)
print(threeDouble)
var anotherDouble = Array(repeating: 2.5, count: 3)
var sixDouble = threeDouble + anotherDouble
print(sixDouble)

let intVariable2 = [1,2,3]
print(type(of: intVariable2))
// intVariable2.append(4) -> let 상수... 추가 불가...

var shoppingList = ["Eggs", "Milk"]

print(shoppingList.count)
if shoppingList.isEmpty{
    
}

shoppingList += ["Baking Power"]
shoppingList += ["Chocolate", "Cheese", "Butter"]
print(shoppingList)


// 배열의 특정 위치 데이터 변경 및 제거
shoppingList[0...3] = ["Banana1", "Banana2", "Banana3", "Banana4", "Banana5", "Banana6"]
print(shoppingList)

shoppingList.insert("apple", at: 0)
print(shoppingList)

// 배열의 값과 인덱스가 필요한 경우
for (index, value) in shoppingList.enumerated(){
    print("Item \(index+1) : \(value)")
}

print(shoppingList.enumerated())

var tot = 0.0
for i in sixDouble{
    tot += i
}
print(tot)

// 최댓값 찾기
print(sixDouble.max()!)
// 최솟값 찾기
print(sixDouble.min()!)



