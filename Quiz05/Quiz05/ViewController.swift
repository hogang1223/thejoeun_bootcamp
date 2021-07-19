//
//  ViewController.swift
//  Quiz05
//
//  Created by hyogang on 2021/07/19.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var tfDan: UITextField!
    @IBOutlet weak var tvResult: UITextView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
    } // end viewDidLoad


    @IBAction func btnCalc(_ sender: UIButton) {
        
        if let inputNum : Int = Int(tfDan.text!) {
            tvResult.text = calcMultiple(dan: inputNum)
            
        }else{
            tvResult.text = "올바른 숫자를 입력하세요."
        }
    } // end btnCalc
    
    func calcMultiple(dan:Int) -> String{
        var result : String = ""
        for i in 1..<10{
            result += "\(dan) X \(i) = \(String(format:"%2d", dan * i)) \n"
        }
        return result
    }
    
} // end ViewController

