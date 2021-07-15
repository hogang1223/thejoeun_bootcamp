//
//  ViewController.swift
//  Quiz32
//
//  Created by hyogang on 2021/07/15.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var tfStartNum: UITextField!
    @IBOutlet weak var tfEndNum: UITextField!
    
    @IBOutlet weak var lblMessage: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        lblMessage.text = "숫자를 입력해주세요."
    }

    
    
    @IBAction func btnSum(_ sender: UIButton) {
        
//        let startNum : Int
//        let endNum : Int
//
//        if (Int(tfStartNum.text!)! > Int(tfEndNum.text!)!) == true{
//            startNum = Int(tfStartNum.text!)!
//            endNum = Int(tfEndNum.text!)!
//        }
        let startNum : Int = Int(tfStartNum.text!)!
        let endNum : Int = Int(tfEndNum.text!)!
        
        lblMessage.text = calcSum(startNum, endNum)
        
    }
    
    func calcSum(_ num1: Int, _ num2: Int) -> String{
        
        var sum: Int = 0
        
        var startNum : Int = 0
        var endNum : Int = 0
        
        if(num1<num2){
            startNum = num1
            endNum = num2
        }else{
            startNum = num2
            endNum = num1
        }
        

        for i in startNum...endNum{
            sum += i
        }
        return "\(startNum)~\(endNum)의 합계는 \(sum)입니다."
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
}

