//
//  ViewController.swift
//  Quiz15_NavigationControl
//
//  Created by hyogang on 2021/07/22.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var imgLamp: UIImageView!
    
    let lampOn = UIImage(named: "lamp_on.png")
    let lampOff = UIImage(named: "lamp_off.png")
    let lampPink = UIImage(named: "lamp_pink.png")
    
    var lampIsOn = true
    var pinkLampIsOn = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        imgLamp.image = lampOn
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let lampViewController = segue.destination as! LampViewController
        
        lampViewController.lampIsOn = self.lampIsOn
        lampViewController.pinkLampIsOn = self.pinkLampIsOn
        lampViewController.delegate = self // <<<<<<
        
    }
}

extension ViewController: LampDelegate{
    func didLampOnOffDone(_ controller: LampViewController, lampIsOn: Bool) {
        if lampIsOn {
            imgLamp.image = lampOn
            self.lampIsOn = true
        }else{
            imgLamp.image = lampOff
            self.lampIsOn = false
        }
    }
    
    func didPinkLampOnOffDone(_ controller: LampViewController, pinkLampOn: Bool) {
        if pinkLampOn {
            imgLamp.image = lampPink
            self.pinkLampIsOn = true
        }else{
            self.pinkLampIsOn = false
        }
    }
}

