//
//  ViewController.swift
//  SendMessage
//
//  Created by hyogang on 2021/07/19.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var tvScreen: UITextView!
    @IBOutlet weak var tfMessage: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        tvScreen.isEditable = false

    } // end viewDidLoad
    
    
    @IBAction func btnSend(_ sender: UIButton) {
        
        let strInput = tfMessage.text!.trimmingCharacters(in: .whitespacesAndNewlines)
        if !strInput.isEmpty {
            tvScreen.text += tfMessage.text! + "\n"
            tfMessage.text = ""
            self.view.endEditing(true)
        }
        
    } // end btnSend
    
    @IBAction func btnEmoji(_ sender: UIButton) {
        tfMessage.text! += "🥲"
    } // end btnEmoji
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    

} // end ViewController

