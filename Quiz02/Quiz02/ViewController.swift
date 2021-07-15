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
    }

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    
    @IBAction func btnCalc(_ sender: UIButton) {
        
        if (tfNum1.text?.isEmpty == true) || (tfNum2.text?.isEmpty == true){
            lblMessage.text = "숫자를 확인하세요!"
        }else{
            
            let num1 = Int(tfNum1.text!)!
            let num2 = Int(tfNum2.text!)!
            
            tfAdd.text = String(num1+num2)
            tfSub.text = String(num1-num2)
            tfMul.text = String(num1*num2)
            tfDiv1.text = String(num1/num2)
            tfDiv2.text = String(num1%num2)
            
            lblMessage.text = "계산이 되었습니다."
        }
        
    }// btnCalc
    
    
} // ViewController

