//
//  ViewController.swift
//  Emoji
//
//  Created by hyogang on 2021/07/15.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var lblEmoji: UILabel!
    var index : Int = 0
    var arrEmoji = ["😃","😄","😆","😅","😂","🥲"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        lblEmoji.text = arrEmoji[index]
        
    }


    @IBAction func btnPlay(_ sender: UIButton) {
        
        index += 1
        if index == 6{
            index = 0
        }
        lblEmoji.text = arrEmoji[index]
        
    }
    
    
    @IBAction func btnPrev(_ sender: UIButton) {
        
        index -= 1
        if index == -1{
            index = 5
        }
        lblEmoji.text = arrEmoji[index]
        
    }
    
    
} // ViewController

