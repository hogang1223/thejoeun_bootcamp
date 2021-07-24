//
//  ViewController.swift
//  Navigation
//
//  Created by hyogang on 2021/07/22.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var tfMessage: UITextField!
    @IBOutlet weak var imgView: UIImageView!
    
    let imgOn = UIImage(named: "lamp_on.png")
    let imgOff = UIImage(named: "lamp_off.png")
    
    var isOn = true
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        imgView.image = imgOn
    }
    
    // EditViewController와 연결하는 함수
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let editViewController = segue.destination as! EditViewController
        
        if segue.identifier == "editButton" {
            editViewController.textWayValue = "Segue : Use Button!"
        }else{
            editViewController.textWayValue = "Segue : Use Bar Button!"
        }
        editViewController.isOn = self.isOn // self 안써도 됨
        editViewController.textMessage = tfMessage.text!
        editViewController.delegate = self // <<<<<<
    }

} //ViewController

extension ViewController: EditDelegate{
    func didMessageEditDone(_ controller: EditViewController, message: String) {
        tfMessage.text = message
    }
    
    func didImageOnOffDone(_ controller: EditViewController, isOn: Bool) {
        if isOn{
            imgView.image = imgOn
            self.isOn = true // ViewController의 isOn self 안쓰면 함수의 isOn(let) 이기 때문에 변수명 변경 불가
        }else{
            imgView.image = imgOff
            self.isOn = false
        }
    }
}

