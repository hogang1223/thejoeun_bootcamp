//
//  main.swift
//  Day03
//
//  Created by hyogang on 2021/07/16.
//

import Foundation

// Dictionary
// Key와 Value로 구성됨

// var scoreDictionary : Dictionary<String, Int> = [String:Int]()
var scoreDictionary : [String: Int] = [:]
scoreDictionary["유비"] = 100
scoreDictionary["관우"] = 90
scoreDictionary["장비"] = 80

print(scoreDictionary)
// 초기값을 가지는 Dictionary

let initializedDictionary : [String: String] = ["name":"James", "gender":"male"]

// Set
// Set은 집합연산에 사용되고, 중복 데이터를 적용하지 않는다.

let oddDigits: Set = [1,3,5,7,9,9,9,9,9]
let evenDigits : Set = [0,2,4,6,8]
let singleDigitPrimeNumbers: Set = [2,3,5,7]

// 합집합
print(oddDigits.union(evenDigits).sorted())

// 교집합
print(oddDigits.intersection(singleDigitPrimeNumbers).sorted())

// 차집합
print(oddDigits.subtracting(singleDigitPrimeNumbers).sorted())

// Set의 동등 비교
let houseAnimals: Set = ["dog", "cat"]
let farmAnimals : Set = ["cow", "chicken","sheep","dog","cat"]
let cityAnimals : Set = ["duck","rabbit"]

print(houseAnimals.isSubset(of: farmAnimals))
print(farmAnimals.isSuperset(of: houseAnimals))
print(farmAnimals.isDisjoint(with: cityAnimals))

// If
var isCar = true
var isNew = true

if isCar, isNew{
    print("New Car")
}
isNew = false

if isCar, isNew{
    print("New Car")
}else{
    print("Old Car")
}

print("=======================================")

// Switch 조건문
// Before Switch

let personAge = 14
if personAge < 1{
    print("baby")
}else if personAge < 3{
    print("toddler")
}else if personAge < 5{
    print("preschooler")
}else if personAge < 13{
    print("gradeschooler")
}else if personAge < 18{
    print("teen")
}else{
    print("adult")
}

// After using Switch statement
switch personAge{
case 0..<1:
    print("baby")
case 1..<3:
    print("toddler")
case 3..<5:
    print("preschooler")
case 5..<13:
    print("gradeschooler")
case 13..<18:
    print("teen")
default :
    print("adult")
}

let someInteger = 123456

switch someInteger{
case 0:
    print("zero")
case 1..<100:
    print("1~99")
case 101...Int.max:
    print("over 100")
default:
    print("unknown")
}

print(Int.max)

let aCharacter: Character = "a"

switch aCharacter{
case "A", "a":
    print("The letter A")
default:
    print("Not the letter A")
}

// 국어, 영어, 수학 점수로 총점 평균을 구하고 등급을 정하기

let name = "유비"
let kor = 91
let eng = 97
let math = 95

var tot = 0
var avg : Double = 0

tot = kor + eng + math
avg = Double(tot) / 3


// 점수 등급 출력 -1
var grade: String

// if문을 이용한 등급 출력
if avg >= 90{
    grade = "A"
}else if avg >= 80{
    grade = "B"
}else if avg >= 70{
    grade = "C"
}else if avg >= 60{
    grade = "D"
}else{
    grade = "F"
}


// switch문을 이용한 등급 출력
switch avg{
case 90..<101:
    grade = "A"
case 80..<90:
    grade = "B"
case 70..<80:
    grade = "C"
case 60..<70:
    grade = "D"
case 0..<60:
    grade = "F"
default:
    grade = "점수가 이상해요"
}

// 삼항연산자 등급 출력
grade = avg >= 90 ? "A" :
        avg >= 80 ? "B" :
        avg >= 70 ? "C" :
        avg >= 60 ? "D" : "F"
print(grade)

// 배열과 반복문
var score = [90 , 80 , 70 , 60 ,  0]
var level = ["A", "B", "C", "D", "F"]

avg = 50
for i in 0..<score.count{
    
    if avg >= Double(score[i]) {
        print(level[i])
        break;
    }
}

// 반복문
let names = ["Anna", "Alex", "Brian", "Jack"]

for i in 0..<name.count{
    print (names[i])
}

for name in names {
    print(name)
}

// dictionary 반복문
let numberOfLegs = ["Spider":8, "Ant":6, "Cat":4]

for (animalName, LegCount) in numberOfLegs{
    print("\(animalName) have \(LegCount) legs")
}

let seq1 = 1...5
for i in seq1{
    print("\(i) * 5 = \(i*5)")
}

// let seq1 = 5...1 // error

for i in seq1.reversed(){
    print("\(i) * 5 = \(i*5)")
}

let minutes = 60
let hourIntervar = 5

for tickMark in stride(from: 0, to: minutes, by: hourIntervar){
    print(tickMark)
}

for tickMark in stride(from: 0, to: minutes, by: hourIntervar).reversed(){
    print(tickMark)
}

// while
var startIndex = 0
var endIndex = 100
var sum = 0

while startIndex <= endIndex{
    sum += startIndex
    startIndex += 1
}
//print(sum)

startIndex = 0
sum = 0
// 1부터 100까지의 짝수의 합 (단, if문 사용 금지)

while startIndex <= endIndex{
    sum += startIndex
    startIndex += 2
}
print(sum)


var total : Int = 0

while true {
    print("Enter an integer(0 ~ 9) : ", terminator: " ")
    if var number = Int(readLine()!){
        while number > 0{
            total += number % 10
            number /= 10
        }
        break
    }else{
        print("error")
        continue
    }

}
print("Sum of digits = \(total)")



// ----- 강사님 소스
print("Enter an integer(0 ~ 9) : ", terminator: "")
var repNum = 0
var remainder = 0
var sumNo = 0

var inpNum = Int(readLine()!)

if let _: Int = inpNum{
    repNum = inpNum!
    while repNum != 0{
        remainder = repNum % 10
        sumNo += remainder
        repNum /= 10
    }
    print("Sum Of digits = \(sumNo)")
}else{
    print("input value is wrong!")
}
