//
//  ViewController.swift
//  Quiz02
//
//  Created by Kenny on 2021/07/14.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var tfNum1: UITextField!
    @IBOutlet weak var tfNum2: UITextField!
    
    @IBOutlet weak var tfAddition: UITextField!
    @IBOutlet weak var tfSubtraction: UITextField!
    @IBOutlet weak var tfMultiplication: UITextField!
    @IBOutlet weak var tfDivisionQuotient: UITextField!
    @IBOutlet weak var tfDivisionRemainder: UITextField!
    
    @IBOutlet weak var tfMessage: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tfMessage.text = "숫자만 입력하세요!"
        readOnly()
        
    }

    @IBAction func btnCalc(_ sender: UIButton) {
        // Optional 제거
        // nil 이면 밑에 함수 자체를 실행 X , nil 로 리턴해버린다.
        
        if let
        
        guard let strNum1 = tfNum1.text else { return }
        guard let strNum2 = tfNum2.text else { return }
        
        
        if strNum1.isEmpty == true{
            tfNum1.becomeFirstResponder()
            tfMessage.text = "첫번째 숫자를 입력하세요!"
        }else if strNum2.isEmpty == true{
            tfNum2.becomeFirstResponder()
            tfMessage.text = "두번째 숫자를 입력하세요!"
        }else{
            let num1 = Int(strNum1)!
            let num2 = Int(strNum2)!
            calculation(num1, num2)
        }
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    // 계산 결과값 read only로 처리
    func readOnly(){
        tfAddition.isUserInteractionEnabled = false
        tfSubtraction.isUserInteractionEnabled = false
        tfMultiplication.isUserInteractionEnabled = false
        tfDivisionQuotient.isUserInteractionEnabled = false
        tfDivisionRemainder.isUserInteractionEnabled = false
    }
    
    // 계산 함수
    func calculation(_ num1: Int, _ num2: Int){
        tfAddition.text = String(num1 + num2)
        tfSubtraction.text = String(num1 - num2)
        tfMultiplication.text = String(num1 * num2)
        
        if num2 == 0{
            tfDivisionQuotient.text = "계산불가"
            tfDivisionRemainder.text = "계산불가"
        }else{
            tfDivisionQuotient.text = String(num1 / num2)
            tfDivisionRemainder.text = String(num1 % num2)
        }
        tfMessage.text = "계산이 완료 되었습니다."
        self.view.endEditing(true)

    }
    
} // ViewController

