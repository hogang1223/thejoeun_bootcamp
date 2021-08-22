//
//  CaffeineDB.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/18.
//

import Foundation
import SQLite3

class CaffeineDB {
    var db: OpaquePointer?
    
    func createSQLite(){
        // SQLite 생성
        let fileURL = try! FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false).appendingPathComponent("Caffeine.sqlite")
        
        if sqlite3_open(fileURL.path, &db) != SQLITE_OK{
            print("error opening database")
        }
        if sqlite3_exec(db, "CREATE TABLE IF NOT EXISTS caffeine(no INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, mg INTEGER, name TEXT, memo TEXT)", nil, nil, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error creating table: \(errmsg)")
        }
    }
}
