//
//  LampViewController.swift
//  Quiz15_NavigationControl
//
//  Created by hyogang on 2021/07/22.
//

import UIKit

class LampViewController: UIViewController {

    @IBOutlet weak var lblLampOnOff: UILabel!
    @IBOutlet weak var swLampOnOff: UISwitch!
    @IBOutlet weak var swPinkOnOff: UISwitch!
    
    var lampIsOn = true
    var pinkLampIsOn = false
    
    var delegate : LampDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        swLampOnOff.isOn = lampIsOn
        swPinkOnOff.isOn = pinkLampIsOn
        
        if lampIsOn {
            lblLampOnOff.text = "On"
        }else{
            lblLampOnOff.text = "Off"
            pinkLampIsOn = false
            swPinkOnOff.isOn = false
            swPinkOnOff.isEnabled = false
        }
    }

    @IBAction func btnDone(_ sender: UIButton) {
        if delegate != nil{
            delegate?.didLampOnOffDone(self, lampIsOn: lampIsOn)
            delegate?.didPinkLampOnOffDone(self, pinkLampOn: pinkLampIsOn)
        }
        navigationController?.popViewController(animated: true)
    }
    
    @IBAction func switchLampOnOff(_ sender: UISwitch) {
        if sender.isOn {
            lampIsOn = true
            lblLampOnOff.text = "On"
            swPinkOnOff.isEnabled = true
        }else{
            lampIsOn = false
            lblLampOnOff.text = "Off"
            
            pinkLampIsOn = false
            swPinkOnOff.isOn = false
            swPinkOnOff.isEnabled = false
        }
    }
    
    
    @IBAction func switchPinkLampOnOff(_ sender: UISwitch) {
        if sender.isOn {
            pinkLampIsOn = true
        }else{
            pinkLampIsOn = false
        }
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
