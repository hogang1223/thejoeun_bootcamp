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

    override func viewDidLoad() {
        super.viewDidLoad()
        
        itemListTableView.delegate = self
        itemListTableView.dataSource = self
        
    } // viewDidLoad

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
        
        let model = InsertModel()
        model.loadData()
        
        let item = ItemList[indexPath.row]
        print("name : \(item.1), mg : \(item.0)")
        model.insertValues(name: item.1, mg: item.0)

        self.navigationController?.popViewController(animated: true)
    }

} // extension : TableView
