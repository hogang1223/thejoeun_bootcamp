//
//  ViewController.swift
//  SystemLayout
//
//  Created by hyogang on 2021/07/29.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var tfUserId: UITextField!
    @IBOutlet weak var tfUserPassword: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


    @IBAction func btnOK(_ sender: UIButton) {
        if tfUserId.text == "aaa" && tfUserPassword.text == "1111"{
            Share.userID = tfUserId.text!
            self.performSegue(withIdentifier: "afterChecking", sender: self) // <<<<<<<<<<<<<<<< segue 연결은 viewController
        }else{
            let idAlert = UIAlertController(title: "경고", message: "아이디 혹은 비밀번호가 일치하지 않습니다.", preferredStyle: .alert)
            let idAction = UIAlertAction(title: "YES", style: .default, handler: nil)
            idAlert.addAction(idAction)
            present(idAlert, animated: true, completion: nil)
        }
    }
    
//    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
//        if segue.identifier == "afterChecking"{
//            
//        }
//    }
}

