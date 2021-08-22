//
//  CalendarViewController.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/18.
//

import UIKit
import SQLite3
import FSCalendar

class CalendarViewController: UIViewController {
    
    // UI
    @IBOutlet weak var roundView: UIView!
    @IBOutlet weak var imgCoffee: UIImageView!
    @IBOutlet weak var lblMg: UILabel!

    @IBOutlet weak var calendar: FSCalendar!
    let screenSize : CGRect = UIScreen.main.bounds
    
    // DB
    var db: OpaquePointer?
    var caffeineList : [Caffeine] = []
    let model = CalendarModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        calendar.delegate = self
        calendar.dataSource = self
        setCalendar()
        
        // DB Set
        model.loadSQLiteDB()
        
        caffeineOfToday()
        
        // Do any additional setup after loading the view.
    }
    override func viewWillAppear(_ animated: Bool) {
        caffeineList = model.selectDBAll()
    }
    
    func caffeineOfToday(){
        // set bottom UI
        lblMg.textColor = UIColor(named: "c80")
        roundView.layer.borderColor = CGColor(red: 0.333, green: 0.220, blue: 0.000, alpha: 100) // c80
        roundView.layer.borderWidth = 2.0
        roundView.layer.cornerRadius = 12.0
        
        // get Today's Data
        caffeineList = model.selectDBAll()
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let today = dateFormatter.string(from: Date())
        
        let totalMgOfToday = getClickData(calendarDate: today)
        getCoffeeColor(totalMg: totalMgOfToday)
        
        lblMg.text = "카페인 섭취량 : \(totalMgOfToday)mg"
    }
    
    func getClickData(calendarDate : String) -> Int{
        var totalMg : Int = 0
        
        for i in 0..<caffeineList.count{
            if calendarDate == caffeineList[i].date{
                totalMg += caffeineList[i].mg
            }
        }
        return totalMg
    }
    
    func getCoffeeColor(totalMg: Int){
        var percentage : Double = 0
        if totalMg != 0{
            percentage = Double(totalMg) / 400 * 100
        }
        
        switch percentage {
        case 0 :
            imgCoffee.image = UIImage(systemName: "drop")
            imgCoffee.tintColor = UIColor(named: "c80")
        case 1...20 :
            imgCoffee.image = UIImage(systemName: "drop.fill")
            imgCoffee.tintColor = UIColor(named: "c20")
        case 21...40 :
            imgCoffee.image = UIImage(systemName: "drop.fill")
            imgCoffee.tintColor = UIColor(named: "c40")
        case 41...60 :
            imgCoffee.image = UIImage(systemName: "drop.fill")
            imgCoffee.tintColor = UIColor(named: "c60")
        case 61...80 :
            imgCoffee.image = UIImage(systemName: "drop.fill")
            imgCoffee.tintColor = UIColor(named: "c80")
        case 81...100 :
            imgCoffee.image = UIImage(systemName: "drop.fill")
            imgCoffee.tintColor = UIColor(named: "c100")
        default:
            imgCoffee.image = UIImage(systemName: "drop.fill")
            imgCoffee.tintColor = .black
        }

    }

    
    func setCalendar(){
        
        // autoLayout
        self.view.addSubview(calendar)
        
        calendar.translatesAutoresizingMaskIntoConstraints = false
        
        calendar.widthAnchor.constraint(equalToConstant: screenSize.width * 0.95).isActive = true
        calendar.heightAnchor.constraint(equalTo: calendar.widthAnchor, multiplier: 1/1).isActive = true
        calendar.centerXAnchor.constraint(equalTo: self.view.centerXAnchor).isActive = true
        calendar.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor).isActive = true
        
        // 달력 UI
        calendar.appearance.titleDefaultColor = UIColor(named: "c100")
        calendar.appearance.titleWeekendColor = UIColor(named: "c100")
        calendar.appearance.headerTitleColor = UIColor(named: "c60")
        calendar.appearance.weekdayTextColor = UIColor(named: "c60")
        calendar.appearance.headerDateFormat = "YYYY년 M월"
        calendar.locale = Locale(identifier: "ko_KR")
        calendar.appearance.headerMinimumDissolvedAlpha = 0
    }
    
    
} // CalendarViewController

// MARK : - Calendar Delegate, DataSource
extension CalendarViewController: FSCalendarDelegate, FSCalendarDataSource, FSCalendarDelegateAppearance{
    
    func calendar(_ calendar: FSCalendar, didSelect date: Date, at monthPosition: FSCalendarMonthPosition) {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let calendarDate = dateFormatter.string(from: date)
        
        let totalMg = getClickData(calendarDate: calendarDate)
        
        lblMg.text = "카페인 섭취량 : \(totalMg)mg"
        getCoffeeColor(totalMg: totalMg)
    }
    
    func calendar(_ calendar: FSCalendar, appearance: FSCalendarAppearance, fillDefaultColorFor date: Date) -> UIColor? {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let calendarDate = dateFormatter.string(from: date)
        let today = dateFormatter.string(from: Date())
        
        if calendarDate == today{
            return UIColor.systemTeal
        }else{
            let totalMg = getClickData(calendarDate: calendarDate)
            
            var percentage:Double = 0.0
            if totalMg != 0{
                percentage = Double(totalMg) / 400 * 100
            }
            switch percentage {
            case 0 :
                return UIColor.white
            case 1...20:
                return UIColor(named: "c20")
            case 21...40:
                return UIColor(named: "c40")
            case 41...60:
                return UIColor(named: "c60")
            case 61...80:
                return UIColor(named: "c80")
            case 81...100:
                return UIColor(named: "c100")
            default:
                return UIColor.black
            }
        }
        
    }
}
