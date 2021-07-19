//
//  ViewController.swift
//  MultiLine
//
//  Created by hyogang on 2021/07/19.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var tfInput: UITextField!
    @IBOutlet weak var tvResult: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        tvResult.isEditable = false // Read Only
        
    } // end viewDidLoad

    
    @IBAction func btnAppend(_ sender: UIButton) {
        
        let strInput = tfInput.text!.trimmingCharacters(in: .whitespacesAndNewlines)
        if !strInput.isEmpty {
            tvResult.text += tfInput.text! + "\n"
        }
    } // end btnAppend
    
} // end ViewController

