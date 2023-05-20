//
//  ViewController.swift
//  Quiz01
//
//  Created by hyogang on 2021/07/14.
//

import UIKit

class ViewController: UIViewController {

    let welcome : String = "Welcome!"
    @IBOutlet weak var lblWelcome: UILabel!
    
    
    @IBOutlet weak var lblSmile: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
    } //viewDidLoad

    @IBAction func btnName(_ sender: UIButton) {
        
        if lblWelcome.text == welcome {
            lblWelcome.text?.append(" 조효경")
        }else{
            lblWelcome.text = welcome
        }
        
        
    } //btnName
    
    @IBAction func btnSmile(_ sender: UIButton) {
        let smileEmoji : String = "😄"
        
        lblSmile.text?.append(smileEmoji)
        
        
    } // btnSmile
    
} // ViewController

