//
//  MyInfoViewController.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/18.
//

import UIKit

class MyInfoViewController: UIViewController {
    
    // UI
    @IBOutlet weak var tfName: UITextField!
    @IBOutlet weak var tfAge: UITextField!
    @IBOutlet weak var tfWeight: UITextField!
    @IBOutlet weak var switchPregnancy: UISwitch!
    @IBOutlet weak var btnCompletion: UIButton!
    
    let myUserDefaults = UserDefaults.standard
 
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // btnCompletion UI Setting
        btnCompletion.layer.cornerRadius = 8
        
        tfName.delegate = self
        tfAge.delegate = self
        tfWeight.delegate = self

    } // ---------- viewDidLoad
    
    
   override func viewWillAppear(_ animated: Bool) {

    if let userName = myUserDefaults.string(forKey: "userName") {
           let userAge = myUserDefaults.integer(forKey: "userAge")
           let userWeight = myUserDefaults.integer(forKey: "userWeight")
           let userPregnancy = myUserDefaults.bool(forKey: "userPregnancy")

            tfName.text = userName
            tfAge.text = String(userAge)
            tfWeight.text = String(userWeight)
            switchPregnancy.isOn = userPregnancy
        
      }else{
        self.navigationItem.setHidesBackButton(true, animated: false)
      }

   } // ---------- viewWillAppear

    
    // btnAction Fuction
    @IBAction func btnRegister(_ sender: UIButton) {
        
        // Data Optional Remove
        guard let userName = tfName.text?.trimmingCharacters(in: .whitespacesAndNewlines) else {
            return
        }
        let userAge:Int? = Int(tfAge.text!)
        let userWeight:Int? = Int(tfWeight.text!)
        let userPregnancy:Bool = switchPregnancy.isOn
        
        
        // Empty Check
        guard tfName.text != "" || tfName.text?.isEmpty != true else {
            tfName.becomeFirstResponder()
            self.showToast(message: "이름을 입력해주세요!")
            return
        }

        guard (tfAge.text != "" || tfAge.text?.isEmpty != true) else {
            tfAge.becomeFirstResponder()
            self.showToast(message: "나이를 입력해주세요!")
            return
        }
        
        guard (tfWeight.text != "" || tfWeight.text?.isEmpty != true) else {
            tfWeight.becomeFirstResponder()
            self.showToast(message: "체중을 입력해주세요!")
            return
        }
        
        // Save
        myUserDefaults.set(userName, forKey: "userName")
        myUserDefaults.set(userAge, forKey: "userAge")
        myUserDefaults.set(userWeight, forKey: "userWeight")
        myUserDefaults.set(userPregnancy, forKey: "userPregnancy")

        // Now Screen Disappear
        self.navigationController?.popViewController(animated: true)
        
    } // ---------- btnRegister
    
    
    // Keyboard Disappear
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    } // --------touchesBegan
    
 
    // Toast Message Fuction
    func showToast(message : String, font: UIFont = UIFont.systemFont(ofSize: 14.0)) {
        let toastLabel = UILabel(frame: CGRect(x: self.view.frame.size.width/2 - 75, y: (self.view.frame.size.height/10)*2, width: 150, height: 35))
        toastLabel.backgroundColor = UIColor(red: 0.4667, green: 0.3373, blue: 0.0784, alpha: 1.0) // c60
        toastLabel.textColor = UIColor.white
        toastLabel.font = font
        toastLabel.textAlignment = .center;
        toastLabel.text = message
        toastLabel.alpha = 1.0
        toastLabel.layer.cornerRadius = 10;
        toastLabel.clipsToBounds = true
        self.view.addSubview(toastLabel)
        UIView.animate(withDuration: 3.0, delay: 0.1, options: .curveEaseOut, animations: {
            toastLabel.alpha = 0.0
        }, completion: {(isCompleted) in
            toastLabel.removeFromSuperview()
        })
    }


    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
    

} // ---------- MyInfoViewController


extension MyInfoViewController: UITextFieldDelegate {
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        
        switch textField {
        case tfName:
            let currentText = textField.text ?? ""
            guard let stringRange = Range(range, in: currentText) else { return false }
            let updatedText = currentText.replacingCharacters(in: stringRange, with: string)
            return updatedText.count <= 10
        case tfAge:
            let currentText = textField.text ?? ""
            guard let stringRange = Range(range, in: currentText) else { return false }
            let updatedText = currentText.replacingCharacters(in: stringRange, with: string)
            return updatedText.count <= 3
        case tfWeight:
            let currentText = textField.text ?? ""
            guard let stringRange = Range(range, in: currentText) else { return false }
            let updatedText = currentText.replacingCharacters(in: stringRange, with: string)
            return updatedText.count <= 3
        default:
            return false
        }
        
    }
}
    
 

