//
//  ViewController.swift
//  Quiz06
//
//  Created by hyogang on 2021/07/15.
//

import UIKit

class BtnViewController: UIViewController {

    
    @IBOutlet weak var imgTitle: UILabel!
    @IBOutlet weak var imgView: UIImageView!
    
    var numImage = 0
    var imageName = ["flower_01.png","flower_02.png","flower_03.png","flower_04.png","flower_05.png","flower_06.png"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        imgTitle.text = imageName[numImage]
        imgView.image = UIImage(named: imageName[numImage])
        
    }
    
    
    @IBAction func btnPrev(_ sender: UIButton) {
    
        numImage -= 1
        if numImage < 0{
            numImage = imageName.count - 1
        }
        displayImage(numImage)
    }
    
    
    @IBAction func btnNext(_ sender: UIButton) {
        numImage += 1
        if numImage >= imageName.count {
            numImage = 0
        }
        displayImage(numImage)
    }
    
    func displayImage(_ numImage: Int){
        imgTitle.text = imageName[numImage]
        imgView.image = UIImage(named: imageName[numImage])
    }

} // ViewController

