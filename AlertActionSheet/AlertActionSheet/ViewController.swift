//
//  ViewController.swift
//  AlertActionSheet
//
//  Created by hyogang on 2021/07/20.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


    @IBAction func btnAlert(_ sender: UIButton) {
        // Controller 초기화
        let testAlert = UIAlertController(title: "Title", message: "Message", preferredStyle: .alert)
        
        // AlertAction
        let actionDefault = UIAlertAction(title: "Action Default", style: .default, handler: nil)
        let actionDestructive = UIAlertAction(title: "Action Destructive", style: .destructive, handler: {ACTION in
            print("destructive action called.")
        })
        let actionCancel = UIAlertAction(title: "Action Cancel", style: .cancel, handler: nil)
        
        // Controller와 Action결합
        testAlert.addAction(actionDefault)
        testAlert.addAction(actionDestructive)
        testAlert.addAction(actionCancel)
        
        // 화면 띄우기
        present(testAlert, animated: true, completion: nil)
    }
    
    @IBAction func btnActionSheet(_ sender: UIButton) {
        
        // Controller 초기화
        let testActionSheet = UIAlertController(title: "Title", message: "Message", preferredStyle: .actionSheet)

        // AlertAction
        let actionDefault = UIAlertAction(title: "Action Default", style: .default, handler: nil)
        let actionDestructive = UIAlertAction(title: "Action Destructive", style: .destructive, handler: {ACTION in
            print("destructive action called.")
        })
        let actionCancel = UIAlertAction(title: "Action Cancel", style: .cancel, handler: nil)

        // Controller와 Action결합
        testActionSheet.addAction(actionDefault)
        testActionSheet.addAction(actionDestructive)
        testActionSheet.addAction(actionCancel)

        // 화면 띄우기
        present(testActionSheet, animated: true, completion: nil)
        
    }
}

