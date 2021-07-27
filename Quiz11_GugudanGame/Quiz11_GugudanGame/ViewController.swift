//
//  ViewController.swift
//  Quiz11_GugudanGame
//
//  Created by hyogang on 2021/07/20.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var lblNum1: UILabel!
    @IBOutlet weak var lblNum2: UILabel!
    
    @IBOutlet weak var tfInputResult: UITextField!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        settingNumber()
    }
    
    @IBAction func btnOK(_ sender: UIButton) {
        
        if let inputNum : Int = Int(tfInputResult.text!){
            checkResult(inputNum: inputNum)
        }else{
            let error = UIAlertController(title: "Error", message: "\nPlease input vaild number.", preferredStyle: .alert)
            
            let close = UIAlertAction(title: "OK", style: .default, handler: nil)
            
            error.addAction(close)
            present(error, animated: true, completion: nil)
        }

    }
    
    func checkNumPolicy(_ inputNum: String) -> Bool{
        
        guard inputNum != nil else{ return false }
        let pattern: String = "[0-9]*"
        let result = NSPredicate()
        
        
        
        return true
    }

    // get random number
    func getNum() -> String{
        return String(Int.random(in: 1..<10))
    }
    
    // number 초기화
    func settingNumber(){
        lblNum1.text = getNum()
        lblNum2.text = getNum()
        tfInputResult.text = ""
    }
    
    // 정답 확인
    func checkResult(inputNum: Int){
        
        guard let num1 = Int(lblNum1.text!) else { return }
        guard let num2 = Int(lblNum2.text!) else { return }
        
        if inputNum == num1 * num2{
            
            let correct = UIAlertController(title: "Result", message: "\nCorrect!\nYou're Genius!", preferredStyle: .alert)
            
            let nextGame = UIAlertAction(title: "Next Game", style: .default, handler: {ACTION in self.settingNumber()})
            
            correct.addAction(nextGame)
            present(correct, animated: true, completion: nil)
            
        }else{
            let wrong = UIAlertController(title: "Result", message: "\nIdiot!\n It's wrong!", preferredStyle: .alert)
            
            let playAgain = UIAlertAction(title: "OK", style: .default, handler:nil)
            
            wrong.addAction(playAgain)
            present(wrong, animated: true, completion: nil)
        }
    }

} // end ViewController

