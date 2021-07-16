//
//  ViewController.swift
//  Quiz31
//
//  Created by hyogang on 2021/07/16.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var tfNum1: UITextField!
    @IBOutlet weak var tfNum2: UITextField!
    @IBOutlet weak var tfResult: UITextField!
    
    @IBOutlet weak var lblMessage: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        tfResult.isUserInteractionEnabled = false
    }

    @IBAction func btnAdd(_ sender: UIButton) {
// 처음에는 보통 var로 씀.. let을 쓸 경우라면 경고창이 떠서 바꿔주면 됨.
//       // if문으로 빈칸 체크 안 하고 이렇게 쓸 수 있다!
//       // (Quiz3. 참고 : Optional과 삼항연산자 동시에 씀)
//       // 그림 그리기에서 placeHolder(즉, hint)로 0이라고 힌트를 써두고 이렇게 쓴다.
       let checkNum1 = Int(tfNum1.text!) ?? 0
       let checkNum2 = Int(tfNum2.text!) ?? 0
//
//       sumResult(checkNum1, checkNum2)

        
//        if tfNum1.text?.isEmpty == true{
//            tfNum1.becomeFirstResponder()
//            lblMessage.text = "1번 숫자 입력 바람"
//        }else if tfNum2.text?.isEmpty == true{
//            tfNum2.becomeFirstResponder()
//            lblMessage.text = "2번 숫자 입력 바람"
//        }else{
            if (Int(tfNum1.text!)! % 2) == 1{
                tfNum1.becomeFirstResponder()
                lblMessage.text = "1번 숫자는 홀수. 계산불가!"
            }else if (Int(tfNum2.text!)! % 2) == 1{
                tfNum2.becomeFirstResponder()
                lblMessage.text = "2번 숫자는 홀수. 계산불가!"
            }else{
                tfResult.text = String(Int(tfNum1.text!)! + Int(tfNum2.text!)!)
                lblMessage.text = "결과!"
                self.view.endEditing(true)
            }
//        }
        
    } //btnAdd
    
    func sumResult(num1:Int, num2:Int) -> String{
        if (num1 % 2) == 0 && (num2 % 2) == 0{
            return "계산완료"
        }else{
            return "홀수!"
        }
    }
    
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
}

