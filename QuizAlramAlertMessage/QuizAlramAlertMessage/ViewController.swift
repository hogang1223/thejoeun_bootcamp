//
//  ViewController.swift
//  QuizAlramAlertMessage
//
//  Created by hyogang on 2021/07/20.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var lblCurrentTime: UILabel!
    @IBOutlet weak var lblPickerTime: UILabel!
    
    @IBOutlet weak var timePickerVIew: UIDatePicker!
    
    let interval = 1.0
    let timeSelector : Selector = #selector(ViewController.updateTime)
    
    var ct : String = ""
    var pt : String = ""
    var showAlert = true
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        lblPickerTime.text = "알람시간을 선택 하세요."
        Timer.scheduledTimer(timeInterval: interval, target: self, selector: timeSelector, userInfo: nil, repeats: true)
        
    }
    
    @IBAction func changeDatePicker(_ sender: UIDatePicker) {
        let datePickerView = sender
        let formatter = DateFormatter()
        
        formatter.locale = Locale(identifier: "ko")
        formatter.dateFormat = "yyyy-MM-dd EEE a hh:mm" // 24시간 HH
        
        lblPickerTime.text = "선택시간 : \(formatter.string(from:datePickerView.date))"
        
        pt = formatter.string(from: datePickerView.date)
        
    }
    
    
    @objc func updateTime(){
        let date = NSDate() // Next Step
        let formatter = DateFormatter()
        
        formatter.locale = Locale(identifier: "ko")
        formatter.dateFormat = "yyyy-MM-dd EEE a hh:mm:ss"
        lblCurrentTime.text = "현재시간 : \(formatter.string(from: date as Date))"
        
        formatter.dateFormat = "yyyy-MM-dd EEE a hh:mm"
        ct = formatter.string(from: date as Date)
        
        if ct == pt {
            if showAlert {
                let alram = UIAlertController(title: "알림", message: "설정된 시간입니다!!!", preferredStyle: .alert)
                let ok = UIAlertAction(title: "네, 알겠습니다.", style: .default, handler: nil)
                
                alram.addAction(ok)
                present(alram, animated: true, completion:nil)
                showAlert = false
            }
            
            // 배경색 바꾸기
            view.backgroundColor = UIColor.systemBlue

        }else{
            view.backgroundColor = UIColor.white
            showAlert = true
        }
    }


} // end ViewController

