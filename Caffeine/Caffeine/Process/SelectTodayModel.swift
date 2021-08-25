//
//  selectTodayModel.swift
//  Caffeine
//
//  Created by Jaewon Park on 2021/08/23.
//

import Foundation
import SQLite3

class SelectTodayModel {
    
    var db: OpaquePointer?
    var userCaffeine : [Caffeine] = []
    
    func loadData(){
        let fileURL = try! FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false).appendingPathComponent("Caffeine.sqlite")
        if sqlite3_open(fileURL.path, &db) != SQLITE_OK{
            print("error opening database")
        }
        
        if sqlite3_exec(db, "CREATE TABLE IF NOT EXISTS caffeine(no INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, mg INTEGER, name TEXT, memo TEXT)", nil, nil, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error creating table: \(errmsg)")
        }
    }
    
    func selectDBSelectedDate(selectedDate:String) -> [Caffeine]{
        userCaffeine.removeAll()
        var stmt: OpaquePointer?
        let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self) // 한글 깨짐 방지 ****
        
        let query = "SELECT * FROM caffeine WHERE date = ?"
        
        if sqlite3_prepare(db, query, -1, &stmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error pareparing select: \(errmsg)")
            return userCaffeine
        }
        if sqlite3_bind_text(stmt, 1, selectedDate, -1, SQLITE_TRANSIENT) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding date: \(errmsg)")
            return userCaffeine
        }
        
        while sqlite3_step(stmt) == SQLITE_ROW{
            let no = sqlite3_column_int(stmt, 0)
            let date = String(cString: sqlite3_column_text(stmt, 1))
            let mg = sqlite3_column_int(stmt, 2)
            let name = String(cString: sqlite3_column_text(stmt, 3))
            let memo = String(cString: sqlite3_column_text(stmt, 4))
            
            var cDate : String = date.components(separatedBy:["년", "월", "일"]).joined()
            cDate = cDate.replacingOccurrences(of: " ", with: "-")
            //print(no, date, mg, name)

            userCaffeine.append(Caffeine(no: Int(no), date: cDate, mg: Int(mg), name: name, memo: memo))
        }
        sqlite3_finalize(stmt)
        sqlite3_close(db)
        
        return userCaffeine
    }
    
    
    
}
