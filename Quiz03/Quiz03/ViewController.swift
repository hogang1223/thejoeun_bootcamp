//
//  ViewController.swift
//  Quiz03
//
//  Created by SeungYeon on 2021/07/15.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var tfNum: UITextField!
    @IBOutlet weak var lblMessage: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        lblMessage.text = ""
    }

    @IBAction func btnClick(_ sender: UIButton) {
        
        
        // 강사님01
//        var message: String
//        let checkNumber = Int(tfNum.text!) ?? 0
//        if checkNumber % 2 == 0 {
//            message = "짝수"
//        }else{
//            message = "홀수"
//        }
//        lblMessage.text = "입력하신 숫자는 \(message)입니다."
        
        // 강사님02
        let numCheck = checkNil(str: tfNum.text!)
        if  numCheck == 1 {
            let returnValue = numberDecision(str: tfNum.text!)
            lblMessage.text = "입력하신 숫자는 \(returnValue)입니다."
        }else{
            lblMessage.text = "숫자를 확인하세요!"
        }
        
        
        
        
//        guard let strNum = tfNum.text else { return }
//
//        if strNum.isEmpty == true{
//            lblMessage.text = "숫자를 확인하세요!"
//            // 포커스
//            tfNum.becomeFirstResponder()
//        }else{
//            let num1 = Int(strNum)! % 2
//            lblMessage.text = num1 == 0 ? "입력하신 숫자는 짝수입니다.":"입력하신 숫자는 홀수입니다."
////            result(num1)
//        }
        
    }
    
//    func result(_ num01: Int){
//        if num01 % 2 == 0 {
//            lblMessage.text = "입력하신 숫자는 짝수입니다."
//        }else{
//            lblMessage.text = "입력하신 숫자는 홀수입니다."
//        }
//    }
    
    
    // 리턴 타입 -> Int
    func checkNil(str: String!) -> Int{
        // 체크 기본!!!!
        let  check = str.trimmingCharacters(in: .whitespacesAndNewlines)
        if check.isEmpty{
            return 0
        }else{
            return 1
        }
    }
    
    func numberDecision(str: String!) -> String {
        if Int(str)! % 2 == 0{
            return "짝수"
        }else{
            return "홀수"
        }
    }
    
}

