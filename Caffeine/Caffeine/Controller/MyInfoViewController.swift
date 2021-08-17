//
//  MyInfoViewController.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/18.
//

import UIKit

class MyInfoViewController: UIViewController {

    @IBOutlet weak var tfName: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.setHidesBackButton(true, animated: false)
        // Do any additional setup after loading the view.
    }
    
    @IBAction func btnRegister(_ sender: UIButton) {
        guard let userName : String = tfName.text?.trimmingCharacters(in: .whitespacesAndNewlines) else{ return }
        
        UserDefaults.standard.set(userName, forKey: "userName")
        self.navigationController?.popViewController(animated: true)
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
