//
//  ListViewController.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/22.
//

import UIKit

class ListViewController: UIViewController {

    @IBOutlet weak var caffeinTable: UITableView!
    @IBOutlet weak var lblTotalMg: UILabel!
    
    // prepare
    var clickedDate : String = ""
    var totalMg : Int = 0
    
    // DBData
    var db: OpaquePointer?
    var caffeineList : [Caffeine] = []
    let model = CalendarModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        caffeinTable.delegate = self
        caffeinTable.dataSource = self
        
        // DB Set
        model.loadSQLiteDB()
        let splitDate = clickedDate.split(separator: "-")
        let dbDate = "\(splitDate[0])년 \(splitDate[1])월 \(splitDate[2])일"
        caffeineList = model.selectDBSelectedDate(selectedDate: dbDate)
        
        // UISetting
        self.navigationItem.title = dbDate
        lblTotalMg.text = "Total : \(totalMg)mg"
        
    }

}
// MARK : - Table Delegate, DataSource
extension ListViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return caffeineList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = caffeinTable.dequeueReusableCell(withIdentifier: "listCell", for: indexPath)
        
        cell.textLabel?.text = caffeineList[indexPath.row].name
        cell.detailTextLabel?.text = "\(caffeineList[indexPath.row].mg)mg"
        
        return cell
    }
 
}
