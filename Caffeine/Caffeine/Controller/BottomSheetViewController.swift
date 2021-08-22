//
//  BottomSheetViewController.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/18.
//

import UIKit
import SQLite3

class BottomSheetViewController: UIViewController {

    var db: OpaquePointer?
    var userCaffeine:[Caffeine] = []
    @IBOutlet weak var userCaffeineTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    /// SQLite : SELECT * * * * * *
    func selectValues() {
        // Init Array
        userCaffeine.removeAll()
        
        // Query
        let queryString = "SELECT * FROM caffeine"
        
        // Statement
        var stmt: OpaquePointer?
        
        //
        if sqlite3_prepare(db, queryString, -1, &stmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error preparing select : \(errmsg)")
            return
        }
        
        // 한줄씩 가져오기
        while (sqlite3_step(stmt) == SQLITE_ROW) {
            // Int 값 불러오기
            let no = sqlite3_column_int(stmt, 0)
            let date = String(cString: sqlite3_column_text(stmt, 1))
            let mg = sqlite3_column_int(stmt, 2)
            let name = String(cString: sqlite3_column_text(stmt, 3))
            
            
            // Data 잘 들어갔나 확인
            print(no, date, mg, name)
            
            // describing:
            userCaffeine.append(Caffeine(no: Int(no), date: date, mg: Int(mg), name: name))
            
        }
        // 값이 들어왔으면 재구성
        self.userCaffeineTableView.reloadData()
    }

}

extension BottomSheetViewController: UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return userCaffeine.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "BottomSheetTableViewCell") as! BottomSheetTableViewCell
        let item = userCaffeine[indexPath.row]
        
        cell.lblItem.text = item.name
        cell.lblCaffeine.text = String(item.mg)
        
        return cell
    }

}

