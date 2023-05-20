//
//  ViewController.swift
//  Quiz13_PageControl
//
//  Created by hyogang on 2021/07/21.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var lblNumber: UILabel!
    @IBOutlet weak var pageControl: UIPageControl!
    let numArr = Array(1...10)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        lblNumber.text = String(numArr[0])
        lblNumber.textColor = UIColor.red
        pageControl.numberOfPages = numArr.count
        pageControl.currentPage = 0
        pageControl.pageIndicatorTintColor = UIColor.systemTeal
        pageControl.currentPageIndicatorTintColor = UIColor.systemPink

    }

    @IBAction func changePage(_ sender: UIPageControl) {
        lblNumber.text = String(numArr[pageControl.currentPage])
        if Int(lblNumber.text!)! % 2 == 1{
            lblNumber.textColor = UIColor.red
        }else{
            lblNumber.textColor = UIColor.blue
        }
    }
    
}

