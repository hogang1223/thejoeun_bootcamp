//
//  ViewController.swift
//  Alert
//
//  Created by hyogang on 2021/07/20.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var lampImage: UIImageView!
    @IBOutlet weak var btnLampOn: UIButton!
    @IBOutlet weak var btnLampOff: UIButton!
    @IBOutlet weak var btnLampRemove: UIButton!
    
    let imgOn = UIImage(named: "lamp_on.png")
    let imgOff = UIImage(named: "lamp_off.png")
    let imgRemove = UIImage(named: "lamp_remove.png")
    
    var isLampOn = true // Lamp Status
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        lampImage.image = imgOn
    }
    
    
    @IBAction func btnLampOn(_ sender: UIButton) {
        if isLampOn{
            let lampOnAlert = UIAlertController(title: "경고", message: "현재 On상태 입니다.", preferredStyle: .alert)
            
            let onAction = UIAlertAction(title: "네, 알겠습니다.", style: .default, handler: nil)
            lampOnAlert.addAction(onAction)
            present(lampOnAlert, animated: true, completion: nil)
            
        }else{
            lampImage.image = imgOn
            isLampOn = true
        }
    }
    
    @IBAction func btnLampOff(_ sender: UIButton) {
        if isLampOn{
            let lampTurnOffAlert = UIAlertController(title: "램프 끄기", message: "램프를 끄시겠습니까?", preferredStyle: .alert)
            
            let cancel = UIAlertAction(title: "아니오", style: .default, handler: nil)
            let ok = UIAlertAction(title: "네", style: .default, handler: {ACTION in self.switchLampStatus(lampStatus: "off")})
            
            lampTurnOffAlert.addAction(cancel)
            lampTurnOffAlert.addAction(ok)

            present(lampTurnOffAlert, animated: true, completion: nil)
            
            
        }else{
            let lampOffAlert = UIAlertController(title: "경고", message: "현재 Off상태 입니다.", preferredStyle: .alert)
            
            let onAction = UIAlertAction(title: "네, 알겠습니다.", style: .default, handler: nil)
            lampOffAlert.addAction(onAction)
            present(lampOffAlert, animated: true, completion: nil)
        }
    }
    
    @IBAction func btnLampRemove(_ sender: UIButton) {
        let lampRemoveAlert = UIAlertController(title: "램프 제거?", message: "램프를 제거할까요?", preferredStyle: .alert)
        
        let turnOff = UIAlertAction(title: "아니요, 끕니다!", style: .default, handler: {ACTION in self.switchLampStatus(lampStatus: "off")})
        let turnOn = UIAlertAction(title: "아니요, 켭니다!", style: .default, handler: {ACTION in self.switchLampStatus(lampStatus: "on")})
        let remove = UIAlertAction(title: "네, 제거합니다.", style: .default, handler: {ACTION in self.self.switchLampStatus(lampStatus: "remove")})
        
        lampRemoveAlert.addAction(turnOff)
        lampRemoveAlert.addAction(turnOn)
        lampRemoveAlert.addAction(remove)
        present(lampRemoveAlert, animated: true, completion: nil)
        
    }
    
    func turnOffLamp(){
        isLampOn = false
        lampImage.image = imgOff
    }
    
    func switchLampStatus(lampStatus: String){
        switch lampStatus {
        case "off":
            isLampOn = false
            lampImage.image = imgOff
        case "on":
            isLampOn = true
            lampImage.image = imgOn
        default:
            lampImage.image = imgRemove
            btnLampOn.isEnabled = false
            btnLampOff.isEnabled = false
            btnLampRemove.isEnabled = false
        }
        
    }
    
    
    
} // end ViewController

