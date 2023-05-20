//
//  ViewController.swift
//  HelloWorld
//
//  Created by hyogang on 2021/07/14.
//

import UIKit

class ViewController: UIViewController {

    // IB : Interface Builder
    @IBOutlet weak var lblHello: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        lblHello.text = "Hello World!"
        
    }


}

