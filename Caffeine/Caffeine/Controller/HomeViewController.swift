//
//  HomeViewController.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/18.
//

import UIKit
import SQLite3
import MaterialComponents.MaterialBottomSheet

class HomeViewController: UIViewController {
    
    @IBOutlet weak var lblName: UILabel!
    var db: OpaquePointer?

    override func viewDidLoad() {
        super.viewDidLoad()
        // create sqlite
        let sqlite = CaffeineDB()
        sqlite.createSQLite()
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        if let userName = UserDefaults.standard.string(forKey: "userName"){
            lblName.text = userName
        }else{
            self.performSegue(withIdentifier: "sgMyinfo", sender: self)
        }
    }
    
    
    @IBAction func btnCalendar(_ sender: UIBarButtonItem) {
        
//        // tempData Insert
//        let insert = TempInsertDB();
//        insert.loadData()
//        insert.tempInsert()
        
        self.performSegue(withIdentifier: "sgCalendar", sender: self)
    }
    @IBAction func btnMyinfo(_ sender: UIBarButtonItem) {
        self.performSegue(withIdentifier: "sgMyinfo", sender: self)
    }
    
    @IBAction func btnAddCaffeine(_ sender: UIBarButtonItem) {
        self.performSegue(withIdentifier: "sgAdd", sender: self)
    }
    
    
    @IBAction func btnTodayCaffeine(_ sender: UIButton) {
        let vc = storyboard?.instantiateViewController(identifier: "BottomSheetViewController") as! BottomSheetViewController
        let bottomSheet: MDCBottomSheetController = MDCBottomSheetController(contentViewController: vc)
        present(bottomSheet, animated: true, completion: nil)
        
    }
    
    
}

