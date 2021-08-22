//
//  CalendarModel.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/21.
//

import Foundation
import SQLite3

class CalendarModel{
    
    var db: OpaquePointer?
    var caffeineList : [Caffeine] = []
    
    func loadSQLiteDB(){
        
        let fileURL = try! FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false).appendingPathComponent("Caffeine.sqlite")
        if sqlite3_open(fileURL.path, &db) != SQLITE_OK{
            print("error opening database")
        }
        
        if sqlite3_exec(db, "CREATE TABLE IF NOT EXISTS caffeine(no INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, mg INTEGER, name TEXT)", nil, nil, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error creating table: \(errmsg)")
        }
    }

    
    func selectDBAll() -> [Caffeine]{
        caffeineList.removeAll()
        let query = "SELECT * FROM caffeine"
        
        var stmt: OpaquePointer?
        let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self) // 한글 깨짐 방지 ****
        
        if sqlite3_prepare(db, query, -1, &stmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error pareparing select: \(errmsg)")
            return caffeineList
        }
        
        while sqlite3_step(stmt) == SQLITE_ROW{
            let no = sqlite3_column_int(stmt, 0)
            let date = String(cString: sqlite3_column_text(stmt, 1))
            let mg = sqlite3_column_int(stmt, 2)
            let name = String(cString: sqlite3_column_text(stmt, 3))
            
            var cDate : String = date.components(separatedBy:["년", "월", "일"]).joined()
            cDate = cDate.replacingOccurrences(of: " ", with: "-")
            //print(no, date, mg, name)

            caffeineList.append(Caffeine(no: Int(no), date: cDate, mg: Int(mg), name: name))
        }
        return caffeineList
    }
    
    func selectDBSelectedDate(selectedDate:String) -> [Caffeine]{
        caffeineList.removeAll()
        var stmt: OpaquePointer?
        let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self) // 한글 깨짐 방지 ****
        
        let query = "SELECT * FROM caffeine WHERE date = ?"
        
        if sqlite3_prepare(db, query, -1, &stmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error pareparing select: \(errmsg)")
            return caffeineList
        }
        if sqlite3_bind_text(stmt, 1, selectedDate, -1, SQLITE_TRANSIENT) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding date: \(errmsg)")
            return caffeineList
        }
        
        while sqlite3_step(stmt) == SQLITE_ROW{
            let no = sqlite3_column_int(stmt, 0)
            let date = String(cString: sqlite3_column_text(stmt, 1))
            let mg = sqlite3_column_int(stmt, 2)
            let name = String(cString: sqlite3_column_text(stmt, 3))
            
            var cDate : String = date.components(separatedBy:["년", "월", "일"]).joined()
            cDate = cDate.replacingOccurrences(of: " ", with: "-")
            //print(no, date, mg, name)

            caffeineList.append(Caffeine(no: Int(no), date: cDate, mg: Int(mg), name: name))
        }
        return caffeineList
    }
}
