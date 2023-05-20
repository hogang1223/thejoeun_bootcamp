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
    let caffeineDB = CaffeineDB()
    let deleteDB = DeleteTodayModel()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        caffeinTable.delegate = self
        caffeinTable.dataSource = self
        
        // DB Set
        model.loadSQLiteDB()
        deleteDB.loadData()
        let splitDate = clickedDate.split(separator: "-")
        let dbDate = "\(splitDate[0])년 \(splitDate[1])월 \(splitDate[2])일"
        caffeineList = model.selectDBSelectedDate(selectedDate: dbDate)
        
        // UISetting
        self.navigationItem.title = dbDate
        countTotalMg()
        
    }
    
    func countTotalMg() {
        totalMg = 0
        for i in caffeineList {
            totalMg += i.mg
        }
        lblTotalMg.text = "Total : \(totalMg)mg"
    }
    
    

}
// MARK : - Table Delegate, DataSource
extension ListViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return caffeineList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = caffeinTable.dequeueReusableCell(withIdentifier: "listCell", for: indexPath) as! ListViewTableViewCell
        let cellNonMemo = caffeinTable.dequeueReusableCell(withIdentifier: "nonMemoListCell", for: indexPath) as! ListView2TableViewCell
        
        if caffeineList[indexPath.row].memo.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty{
            cellNonMemo.lblNameNonMemo.text = caffeineList[indexPath.row].name
            cellNonMemo.lblMgNonMemo.text = "\(caffeineList[indexPath.row].mg)mg"
            return cellNonMemo
        }else{
            cell.lblName.text = caffeineList[indexPath.row].name
            cell.lblMg.text = "\(caffeineList[indexPath.row].mg)mg"
            cell.lblMemo.text = caffeineList[indexPath.row].memo
        }
        
        return cell
    }
 
    func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            
            let item = caffeineList[indexPath.row]
            deleteDB.deleteValues(no: item.no)
            caffeineList.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
            caffeinTable.reloadData()
            countTotalMg()
            
        }
    }
    
}
