//
//  ViewController.swift
//  QuizAlram
//
//  Created by hyogang on 2021/07/19.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var lblCurrentTime: UILabel!
    @IBOutlet weak var lblPickerTime: UILabel!
    
    var currentTime : String = ""
    var changeTime : String = ""
    var count : Int = 0
    
    let interval = 1.0 // 1 sec
    let timeSelector : Selector = #selector(ViewController.updateTime)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        lblPickerTime.text = "시간을 선택하세요!"
        
        Timer.scheduledTimer(timeInterval: interval, target: self, selector: timeSelector, userInfo: nil, repeats: true)
    }

    @IBAction func changeDatePicker(_ sender: UIDatePicker) {
        let datePickerView = sender
        let formatter = DateFormatter()
        
        formatter.locale = Locale(identifier: "ko")
        formatter.dateFormat = "yyyy-MM-dd EEE a hh:mm" // 24시간 HH
        changeTime = formatter.string(from:datePickerView.date)
        lblPickerTime.text = "선택시간 : \(changeTime)"
    }
    
    
    @objc func updateTime(){
        let date = NSDate() // Next Step
        let formatter = DateFormatter()
        
        formatter.locale = Locale(identifier: "ko")
        formatter.dateFormat = "yyyy-MM-dd EEE a hh:mm"
        
        currentTime = formatter.string(from: date as Date)
        lblCurrentTime.text = "현재시간 : \(currentTime)"
        
        if currentTime == changeTime {
            count += 1
            if count % 2 == 0{
                view.backgroundColor = UIColor.red
            }else{
                view.backgroundColor = UIColor.blue
            }
            
        }else{
            view.backgroundColor = UIColor.white
        }
    }

}

