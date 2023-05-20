//
//  AddViewController.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/18.
//

import UIKit
import SQLite3

class TodayViewController: UIViewController {

    @IBOutlet weak var itemListTableView: UITableView!
    var model = CaffeineDB()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        model.createSQLite()
        itemListTableView.delegate = self
        itemListTableView.dataSource = self
        
    } // viewDidLoad

    
    func alertInsertMemo (_ itemname: String, caffeineMg: Int) {
        
        let alert = UIAlertController(title: "카페인 추가", message: "\(itemname)   :   \(String(caffeineMg)) mg", preferredStyle: .alert)
        alert.addTextField(configurationHandler: {(addMemo) in
            addMemo.placeholder = "간단한 메모를 남겨주세요"
        })
        let insert = UIAlertAction(title: "추가", style: .default, handler: {_ in
            let memo = alert.textFields?[0].text ?? ""
            self.model.insertTodayCoffee(mg: String(caffeineMg), name: itemname, memo: memo)
            self.navigationController?.popViewController(animated: true)
        })
        let cancel = UIAlertAction(title: "취소", style: .destructive, handler: nil)
        
        alert.addAction(cancel)
        alert.addAction(insert)
        self.present(alert, animated: true, completion: nil)
        
    }
    
    
    
    
    
} // TodayViewController

// TableView Extension
extension TodayViewController: UITableViewDataSource, UITableViewDelegate {

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return ItemList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TodayTableViewCell") as! TodayTableViewCell
        let item = ItemList[indexPath.row]
        
        cell.itemImgView.image = UIImage(named: item.2)
        cell.lblItem.text = item.1
        cell.lblCaffeineMg.text = "\(String(item.0)) mg"
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        
        let item = ItemList[indexPath.row]
        
        alertInsertMemo(item.1, caffeineMg: item.0)
        

    }

} // extension : TableView
