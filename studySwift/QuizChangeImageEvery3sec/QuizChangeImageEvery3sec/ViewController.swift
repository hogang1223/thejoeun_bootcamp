//
//  ViewController.swift
//  QuizChangeImageEvery3sec
//
//  Created by hyogang on 2021/07/19.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var lblFileName: UILabel!
    @IBOutlet weak var imgView: UIImageView!
    var index : Int = 0
    
    let imgName : [String] = ["flower_01.png", "flower_02.png", "flower_03.png", "flower_04.png", "flower_05.png", "flower_06.png"]
    
    let interval = 3.0 // 3sec
    let imageSelector : Selector = #selector(ViewController.updateImage)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        lblFileName.text = imgName[index]
        imgView.image = UIImage(named: imgName[index])
        Timer.scheduledTimer(timeInterval: interval, target: self, selector: imageSelector, userInfo: nil, repeats: true)
    }
    
    @objc func updateImage(){
        index += 1
        if index == imgName.count{
            index = 0
        }
        lblFileName.text = imgName[index]
        imgView.image = UIImage(named: imgName[index])
    }


}

