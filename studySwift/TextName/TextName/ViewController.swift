//
//  ViewController.swift
//  TextName
//
//  Created by hyogang on 2021/07/14.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var lblWelcome: UILabel!
    @IBOutlet weak var tfName: UITextField!
    @IBOutlet weak var lblMessage: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        lblMessage.text = "환영합니다!"
        
    } // viewDidLoad
    
    
    @IBAction func btnSend(_ sender: UIButton) {
        

        //if tfName.text?.count == 0{
        if tfName.text?.isEmpty == true{
            lblMessage.text = "텍스트를 입력하세요!"
        }else{
            lblWelcome.text = "Welcome! \(tfName.text!)"
            lblMessage.text = "텍스트를 추가했습니다.!"
        }
        
    } // btnSend
    
    
    @IBAction func btnClear(_ sender: UIButton) {
        lblWelcome.text = "Welcome!"
        tfName.text?.removeAll()
        lblMessage.text = "화면 초기상태 입니다."
    } // btnClear
    

} // ViewController

