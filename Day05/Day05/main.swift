//
//  main.swift
//  Day05
//
//  Created by hyogang on 2021/07/20.
//

import Foundation

// --------
// 구조체 : Structure
// : Swift에서 Data Type을 정의할때

struct Sample{
    var sampleProperty: Int = 10 // 가변 프로퍼티
    let fixedSampleProperty: Int = 100 // 불변 프로퍼티
    static var tytpeProperty: Int = 1000 // 타입 프로퍼티
    
    func instanceMethod(){
        print("instance method")
    }
    
    static func typeMethod(){
        print("type method")
    }
    
}

// Structure 사용

var samp: Sample = Sample()
print(samp.sampleProperty)

samp.sampleProperty = 100
print(samp.sampleProperty)

var samp1 = Sample()
print(samp1.sampleProperty)


// Type Property
Sample.typeMethod()
print(Sample.tytpeProperty)

// 학생 구조체


struct Student{
    var name: String = "unknown"
    var `class`: String = "Swift"
    
    static func selfIntroduce(){
        print("학생 타입 입니다.")
    }
    
    func selfIntroduce(){
//        print("저는 \(`class`)반 \(name)입니다.")
        print("저는 \(self.class)반 \(name)입니다.")
    }
}

Student.selfIntroduce()

var student: Student = Student()
student.name = "Jasper"
student.class = "스위프트"
student.selfIntroduce()

let cathy: Student = Student()
//cathy.name = "Cathy"



class Student1{
    var name: String = "unknown"
    var `class`: String = "Swift"
    
    static func selfIntroduce(){
        print("학생 타입 입니다.")
    }
    
    func selfIntroduce(){
//        print("저는 \(`class`)반 \(name)입니다.")
        print("저는 \(self.class)반 \(name)입니다.")
    }
}

Student1.selfIntroduce()
var student1: Student1 = Student1()

class Shape{

    
    func input(_ r:Double){
    
        var pi = 3.14
        printResult(shapeType: "원", area: r * r * pi, border: 2 * r * pi)
    }
    
    func input(_ w:Double, _ h:Double){
        printResult(shapeType: "직사각형", area: w * h , border: (w + h) * 2)
    }
    
    func input(_ a:Double, _ b:Double, _ c:Double){
        printResult(shapeType: "직각삼각형", area: (a * b) / 2 , border: a + b + c)
    }
    
    func printResult(shapeType:String, area:Double, border:Double){
        print("\(shapeType)의 면적은 \(Int(area))이고 둘레는 \(Int(border))입니다.")
    }
    
}

let sh1 : Shape = Shape()
let sh2 : Shape = Shape()
let sh3 : Shape = Shape()

sh1.input(5) // 원의 면적과 둘레 구하기
sh2.input(5, 6) // 직사각형의 면적과 둘레 구하기
sh3.input(5, 6, 8) // 직각삼각형의 면적과 둘레 구하기


class Shape1{
    var area = 0.0
    let pi = 3.14157
    
    init(){
        print("계산시작")
    }
    
    init(radius: Double){
        area = radius * radius * pi
        calc()
    }
    func input(r:Double){
        area = r * r * pi
        calc()
    }
    func calc(){
        print("원의 면적은 \(Int(area))입니다.")
    }
}

let sh4 : Shape1 = Shape1()
sh4.input(r: 5.0)

let sh5 : Shape1 = Shape1(radius: 5.0)


class QuizShape1{
    var shapeType = ""
    var area = 0.0
    var border = 0.0
    
    init(){
        print("계산시작")
    }
    
    init(r: Double){
        let pi = 3.14157
        shapeType = "원"
        area = r * r * pi
        border = 2 * r * pi
        calc()
    }
    
    init(w: Double, h: Double){
        shapeType = "직사각형"
        area = w * h
        border = (w + h) * 2
        calc()
    }
    init(a: Double, b: Double, c: Double){
        shapeType = "직각삼각형"
        area = (a * b) / 2
        border = a + b + c
        calc()
    }

    func calc(){
        print("\(shapeType)의 면적은 \(Int(area))이고 둘레는 \(Int(border))입니다.")
    }
}
let sh9 : QuizShape1 = QuizShape1()
let sh6 : QuizShape1 = QuizShape1(r: 5)
let sh7 : QuizShape1 = QuizShape1(w: 5, h: 6)
let sh8 : QuizShape1 = QuizShape1(a: 5, b: 6, c: 8)

// Extension
// 구조체, 클래스, 프로토콜 타입에 새로운 기능을 추가할 수 있는 기능.

extension Int{
    var isEven: Bool{
        return self % 2 == 0
    }
    var isOdd: Bool{
        return self % 2 == 1
    }
}

print(1.isEven)

// Closure

// 함수
func sumFunction(a: Int, b: Int) -> Int{
    return a + b
}

var sumResult: Int = sumFunction(a: 10, b: 20)
print(sumResult)

// Closure
var sumClosure: (Int, Int) -> Int = {(a: Int, b:Int) -> Int in
    return a + b
}
sumResult = sumClosure(10, 20)
print(sumResult)

// ----
print(Int.random(in: 1..<10))
print(Int.random(in: 1..<10))

func getNum() -> String{
    return String(Int.random(in: 1..<10))
}

print(getNum())
