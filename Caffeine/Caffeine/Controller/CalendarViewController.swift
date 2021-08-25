//
//  CalendarViewController.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/18.
//

// TODO: change maxMg -> 임산부, 학생 확인
import UIKit
import SQLite3
import FSCalendar

class CalendarViewController: UIViewController {
    
    // UI
    @IBOutlet weak var btnDateCaffeine: UIButton!
    
    @IBOutlet weak var calendar: FSCalendar!
    let screenSize : CGRect = UIScreen.main.bounds
    
    // Image
    @IBOutlet weak var imageView: UIImageView!
    var coffeeImage : [String] = ["0.png","2_calendar.png", "4_calendar.png", "6_calendar.png", "8_calendar.png", "10_calendar.png"]
    
    // DB
    var db: OpaquePointer?
    var caffeineList : [Caffeine] = []
    let model = CalendarModel()
    
    var clickedDate : String = ""
    var maxMg : Double = 400
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        calendar.delegate = self
        calendar.dataSource = self
        setCalendar()
        
        // DB Set
        model.loadSQLiteDB()
        caffeineOfToday()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        caffeineList = model.selectDBAll()
    }
    
    @IBAction func btnGoList(_ sender: UIButton) {
        self.performSegue(withIdentifier: "sgGoList", sender: self)
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
            percentage = Double(totalMg) / maxMg * 100
        }
        
        switch percentage {
        case 0 :
            imageView.image = UIImage(named: coffeeImage[0])
        case 1...20 :
            imageView.image = UIImage(named: coffeeImage[1])
        case 21...40 :
            imageView.image = UIImage(named: coffeeImage[2])
        case 41...60 :
            imageView.image = UIImage(named: coffeeImage[3])
        case 61...80 :
            imageView.image = UIImage(named: coffeeImage[4])
        case 81...100 :
            imageView.image = UIImage(named: coffeeImage[5])
        default:
            imageView.image = UIImage(named: coffeeImage[5])
        }

    }
    
    func caffeineOfToday(){
        // set button UI
        btnDateCaffeine.layer.cornerRadius = 12
        btnDateCaffeine.layer.borderWidth = 2.0
        btnDateCaffeine.layer.borderColor = CGColor(red: 0.333, green: 0.220, blue: 0.000, alpha: 100) // c80
        btnDateCaffeine.imageEdgeInsets = UIEdgeInsets(top: 0, left: 24, bottom: 0, right: 24)
        btnDateCaffeine.titleEdgeInsets = UIEdgeInsets(top: 0, left: 50, bottom: 0, right: 24)
        // get Today's Data
        caffeineList = model.selectDBAll()
        
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let today = dateFormatter.string(from: Date())
        clickedDate = today
        
        let totalMg = getClickData(calendarDate: today)
        getCoffeeColor(totalMg: totalMg)
        
        btnDateCaffeine.setTitle("카페인 섭취량 : \(totalMg)mg", for: .normal)
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
        calendar.appearance.headerTitleColor = UIColor(named: "c60")
        calendar.appearance.weekdayTextColor = UIColor(named: "c60")
        calendar.appearance.headerDateFormat = "YYYY년 M월"
        calendar.locale = Locale(identifier: "ko_KR")
        calendar.appearance.headerMinimumDissolvedAlpha = 0
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "sgGoList"{
            let destinationView = segue.destination as! ListViewController
            destinationView.clickedDate = clickedDate
            destinationView.totalMg = getClickData(calendarDate: clickedDate)
        }
    }
    
    
} // CalendarViewController

// MARK : - Calendar Delegate, DataSource
extension CalendarViewController: FSCalendarDelegate, FSCalendarDataSource, FSCalendarDelegateAppearance{
    
    func calendar(_ calendar: FSCalendar, didSelect date: Date, at monthPosition: FSCalendarMonthPosition) {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let calendarDate = dateFormatter.string(from: date)
        clickedDate = calendarDate
        
        let totalMg = getClickData(calendarDate: calendarDate)
        btnDateCaffeine.setTitle("카페인 섭취량 : \(totalMg)mg", for: .normal)
        
        getCoffeeColor(totalMg: totalMg)
    }
    
    func calendar(_ calendar: FSCalendar, appearance: FSCalendarAppearance, fillDefaultColorFor date: Date) -> UIColor? {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let calendarDate = dateFormatter.string(from: date)
        let today = dateFormatter.string(from: Date())
        
        if calendarDate == today{
            return UIColor(displayP3Red: 0.702, green: 0.820, blue: 0.898, alpha: 100)
        }else{
            let totalMg = getClickData(calendarDate: calendarDate)
            
            var percentage:Double = 0.0
            if totalMg != 0{
                percentage = Double(totalMg) / maxMg * 100
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
                return UIColor(named: "c100")
            }
        }
        
    }
    func calendar(_ calendar: FSCalendar, appearance: FSCalendarAppearance, titleDefaultColorFor date: Date) -> UIColor? {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let calendarDate = dateFormatter.string(from: date)
        let today = dateFormatter.string(from: Date())
        
        if calendarDate == today{
            return UIColor.white
        }else{
            let totalMg = getClickData(calendarDate: calendarDate)
            if totalMg != 0{
                return UIColor.white
            }else{
                return UIColor(named: "c80")
            }
//            switch percentage {
//            case 0...60 :
//                return UIColor(named: "c80")
//            default:
//                return UIColor(named: "c60")
//            }
        }
    }
}
