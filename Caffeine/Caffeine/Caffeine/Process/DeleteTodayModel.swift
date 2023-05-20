//
//  DeleteTodayModel.swift
//  Caffeine
//
//  Created by Jaewon Park on 2021/08/24.
//

import Foundation
import SQLite3

class DeleteTodayModel {
    
    var db: OpaquePointer?
    
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
    
    func deleteValues(no: Int) {
        
        var stmt: OpaquePointer?
        let _ = unsafeBitCast(-1, to: sqlite3_destructor_type.self)
        
        // Query
        let queryString = "DELETE FROM caffeine WHERE no = ?"
            
        if sqlite3_prepare(db, queryString, -1, &stmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error pareparing insert: \(errmsg)")
            return
        }
        
        if sqlite3_bind_int(stmt!, 1, Int32(no)) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding dept: \(errmsg)")
            return
        }
        
        // stmp 실행
        if sqlite3_step(stmt) != SQLITE_DONE{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("failure insert: \(errmsg)")
            return
        }

    }
}
