//
//  ViewController.swift
//  Quiz51
//
//  Created by hyogang on 2021/07/19.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var imgView: UIImageView!
    @IBOutlet weak var lblSize: UILabel!
    @IBOutlet weak var lblSwitchOnOff: UILabel!
    @IBOutlet weak var switchSize: UISwitch!
    
    var imgOn : UIImage? // 켜진 전구 이미지
    var imgOff : UIImage? // 꺼진 전구 이미지
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        imgOn = UIImage(named: "lamp_on.png")
        imgOff = UIImage(named: "lamp_off.png")
        
        imgView.image = imgOn
        
        switchSize.isOn = false
        
    }
    
    
    @IBAction func switchSize(_ sender: UISwitch) {
        let scale : CGFloat = 2.0
        var newWidth : CGFloat
        var newHeight : CGFloat
        
        switch sender.isOn{
        case true :
            newWidth = imgView.frame.width * scale
            newHeight = imgView.frame.height * scale
            lblSize.text = "전구 확대"
            imgView.frame.size = CGSize(width: newWidth, height: newHeight)
        default:
            newWidth = imgView.frame.width / scale
            newHeight = imgView.frame.height / scale
            lblSize.text = "전구 축소"
            imgView.frame.size = CGSize(width: newWidth, height: newHeight)
        }
        
    } // end switchSize
    
    @IBAction func switchOnOff(_ sender: UISwitch) {
        switch sender.isOn{
        case true :
            lblSwitchOnOff.text = "전구 스위치 On"
            imgView.image = imgOn
        default:
            lblSwitchOnOff.text = "전구 스위치 Off"
            imgView.image = imgOff
        }
        
    } // end switchOnOff
    
}

