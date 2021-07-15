//
//  ViewController.swift
//  Quiz02
//
//  Created by hyogang on 2021/07/14.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var tfNum1: UITextField!
    @IBOutlet weak var tfNum2: UITextField!

    @IBOutlet weak var tfAdd: UITextField!
    @IBOutlet weak var tfSub: UITextField!
    @IBOutlet weak var tfMul: UITextField!
    
    @IBOutlet weak var tfDiv1: UITextField!
    @IBOutlet weak var tfDiv2: UITextField!
    
    @IBOutlet weak var lblMessage: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        lblMessage.text = "숫자만 입력하세요!"
        readOnly()
    }

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    
    @IBAction func btnCalc(_ sender: UIButton) {
        // Optional 제거
        guard let strNum1 = tfNum1.text else{ return }
        guard let strNum2 = tfNum2.text else{ return }
        
        if strNum1.isEmpty == true{
            tfNum1.becomeFirstResponder()
            lblMessage.text = "첫번째 숫자를 입력하세요!"
        }else if strNum2.isEmpty == true{
            tfNum2.becomeFirstResponder()
            lblMessage.text = "두번째 숫자를 입력하세요!"
        }else{
            let num1 = Int(strNum1)!
            let num2 = Int(strNum2)!
            calculation(num1, num2)
        }
        
    }// btnCalc
    
    // 계산 결과값 read only로 처리
    func readOnly(){
        tfAdd.isUserInteractionEnabled = false
        tfSub.isUserInteractionEnabled = false
        tfMul.isUserInteractionEnabled = false
        tfDiv1.isUserInteractionEnabled = false
        tfDiv2.isUserInteractionEnabled = false

    }
    
    // 계산 함수
    func calculation(_ num1: Int, _ num2: Int){
        tfAdd.text = String(num1 + num2)
        tfSub.text = String(num1 - num2)
        tfMul.text = String(num1 * num2)
        
        if num2 == 0{
            tfDiv1.text = "계산불가"
            tfDiv2.text = "계산불가"
        }else{
            tfDiv1.text = String(num1 / num2)
            tfDiv2.text = String(num1 % num2)
        }
        
        lblMessage.text = "계산이 완료 되었습니다."
        self.view.endEditing(true)

    }
    
    
    
} // ViewController

