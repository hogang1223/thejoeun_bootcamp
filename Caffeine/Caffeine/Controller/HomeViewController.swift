//
//  HomeViewController.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/18.
//

import UIKit
import SQLite3
import MaterialComponents.MaterialBottomSheet

let interval = 1.0
let timeSelector: Selector = #selector(HomeViewController.updateTime)

class HomeViewController: UIViewController {
    
    @IBOutlet weak var lblDate: UILabel!
    @IBOutlet weak var lblCaffeine: UILabel!
    @IBOutlet weak var imgCoffee: UIImageView!
    @IBOutlet var viewBackground: UIView!
    
    @IBOutlet weak var btnOne: UIButton!
    @IBOutlet weak var btnTwo: UIButton!
    @IBOutlet weak var btnMix: UIButton!
    
    let sqlite = CaffeineDB()
    var db: OpaquePointer?
    
    var todayDate = ""
    var todayMg = 0
    var myMg = 0
    
    var now = ""

    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        
        // create sqlite
        sqlite.createSQLite()

        // btn design
        btnOne.layer.cornerRadius = btnOne.layer.frame.size.height/2
        btnTwo.layer.cornerRadius = btnTwo.layer.frame.size.height/2
        btnMix.layer.cornerRadius = btnMix.layer.frame.size.height/2
        
        // date check
        Timer.scheduledTimer(timeInterval: interval, target: self, selector: timeSelector, userInfo: nil, repeats: true)
        
        updateTime()
    }
    
    @objc func updateTime() {
        let date = NSDate()
        let formatter = DateFormatter()
        let formatter2 = DateFormatter()
        
        // today date
        formatter.locale = Locale(identifier: "ko")
        formatter.dateFormat = "yyyy년 MM월 dd일"
        lblDate.text = "\(formatter.string(from: date as Date))"
        todayDate = "\(formatter.string(from: date as Date))"
        
        // today time
        formatter2.locale = Locale(identifier: "ko")
        formatter2.dateFormat = "HHmm"
        now = "\(formatter2.string(from: date as Date))"
        
        // night background
        if Int(now)! > 1800 || Int(now)! < 500 {
            viewBackground.backgroundColor = UIColor(named: "cNight")
        }else {
            viewBackground.backgroundColor = UIColor.white
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        
        if UserDefaults.standard.string(forKey: "userName") != nil {
            
            todayMg = sqlite.selectTodaySumMgSQLite(todayDate)
            
            // my caffeine mg check
            let userAge:Int = UserDefaults.standard.integer(forKey: "userAge")
            let userWeight:Int = UserDefaults.standard.integer(forKey: "userWeight")
            let userPregnancy:Bool = UserDefaults.standard.bool(forKey: "userPregnancy")
            
            if userPregnancy {
                myMg = 300
            }else{
                if userAge > 19 {
                    myMg = 400
                }else {
                    myMg = Int(Double(userWeight) * 2.5)
                }
            }
            
            lblCaffeine.text = "\(todayMg) / \(myMg) mg"
            
            // my caffeine percent
            let myPercent:Double = Double(Double(todayMg) / Double(myMg)) * 100
            
            coffeeImg(myPercent)
            
        }else{
            self.performSegue(withIdentifier: "sgMyinfo", sender: self)
        }
        
    }
    
    // img change - myPercent
    func coffeeImg(_ myPercent: Double) {
        
        print(myPercent)
        
        if myPercent > 80.0 {
            imgCoffee.image = UIImage(named: "10.png")
        }else if myPercent > 60.0 {
            imgCoffee.image = UIImage(named: "8.png")
        }else if myPercent > 40.0 {
            imgCoffee.image = UIImage(named: "6.png")
        }else if myPercent > 20.0 {
            imgCoffee.image = UIImage(named: "4.png")
        }else if myPercent > 0.0 {
            imgCoffee.image = UIImage(named: "2.png")
        }else {
            imgCoffee.image = UIImage(named: "0.png")
        }
    }
    
    // insert two shot
    @IBAction func btnTwoShot(_ sender: UIButton) {
        sqlite.insertMainTwoShotSQLite(todayDate)
        viewWillAppear(true)
    }
    
    // insert three shot
    @IBAction func btnThreeShot(_ sender: UIButton) {
        sqlite.insertMainThreeShotSQLite(todayDate)
        viewWillAppear(true)
    }
    
    // insert mix
    @IBAction func btnMix(_ sender: Any) {
        sqlite.insertMainMixSQLite(todayDate)
        viewWillAppear(true)
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
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "sgCalendar" {
            let destinationView = segue.destination as! CalendarViewController
            destinationView.maxMg = Double(myMg)
        }
    }
} 

