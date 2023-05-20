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
    
    func selectTodaySumMgSQLite(_ todayDate:String) -> Int {
        
        var total = 0
        let queryString = "SELECT sum(mg) FROM caffeine where date='\(todayDate)'"
        
        var stmt: OpaquePointer?
        
        if sqlite3_prepare(db, queryString, -1, &stmt, nil) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error preparing select: \(errmsg)")
            return 0
        }
        
        if(sqlite3_step(stmt) == SQLITE_ROW) {
            let mg = sqlite3_column_int(stmt, 0)
            total = Int(mg)
        }
        sqlite3_finalize(stmt)
        
        return total
    }
    
    func insertTodayCoffee(mg: String, name: String, memo: String) {
        
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy년 MM월 dd일"
        let currentDate = formatter.string(from: Date())
        let today = currentDate
        
        // 한글 깨짐 방지
        var stmt: OpaquePointer?
        let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self)
        
        let queryString = "INSERT INTO caffeine (date, mg, name, memo) VALUES (?,?,?,?)"
        
        if sqlite3_prepare(db, queryString, -1, &stmt, nil) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error preparing insert: \(errmsg)")
            return
        }
        
        if sqlite3_bind_text(stmt, 1, today, -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding date: \(errmsg)")
            return
        }
        
        if sqlite3_bind_text(stmt, 2, mg, -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding mg: \(errmsg)")
            return
        }
        
        if sqlite3_bind_text(stmt, 3, name, -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding name: \(errmsg)")
            return
        }
        if sqlite3_bind_text(stmt, 4, memo, -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding memo: \(errmsg)")
            return
        }
        
        if sqlite3_step(stmt) != SQLITE_DONE { // 잘 끝나지 않았으면
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("failure inserting caffeine: \(errmsg)")
            return
        }
        sqlite3_finalize(stmt)
    }
    
    
    
    func insertMainTwoShotSQLite(_ todayDate: String) {
        // 한글 깨짐 방지
        var stmt: OpaquePointer?
        let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self)
        
        let queryString = "INSERT INTO caffeine (date, mg, name, memo) VALUES (?,?,?,?)"
        
        if sqlite3_prepare(db, queryString, -1, &stmt, nil) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error preparing insert: \(errmsg)")
            return
        }
        
        if sqlite3_bind_text(stmt, 1, todayDate, -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding date: \(errmsg)")
            return
        }
        
        if sqlite3_bind_text(stmt, 2, "150", -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding mg: \(errmsg)")
            return
        }
        
        if sqlite3_bind_text(stmt, 3, "Espresso 2shot", -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding name: \(errmsg)")
            return
        }
        if sqlite3_bind_text(stmt, 4, "", -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding memo: \(errmsg)")
            return
        }
        
        if sqlite3_step(stmt) != SQLITE_DONE {      // 잘 끝나지 않았으면
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("failure inserting caffeine: \(errmsg)")
            return
        }
        
        print("Caffeine TwoShoot saved successfully")
        
        sqlite3_finalize(stmt)
    }
    
    func insertMainThreeShotSQLite(_ todayDate: String) {
        // 한글 깨짐 방지
        var stmt: OpaquePointer?
        let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self)
        
        let queryString = "INSERT INTO caffeine (date, mg, name, memo) VALUES (?,?,?,?)"
        
        if sqlite3_prepare(db, queryString, -1, &stmt, nil) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error preparing insert: \(errmsg)")
            return
        }
        
        if sqlite3_bind_text(stmt, 1, todayDate, -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding date: \(errmsg)")
            return
        }
        
        if sqlite3_bind_text(stmt, 2, "225", -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding mg: \(errmsg)")
            return
        }
        
        if sqlite3_bind_text(stmt, 3, "Espresso 3shot", -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding name: \(errmsg)")
            return
        }
        if sqlite3_bind_text(stmt, 4, "", -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding memo: \(errmsg)")
            return
        }
        
        if sqlite3_step(stmt) != SQLITE_DONE {      // 잘 끝나지 않았으면
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("failure inserting caffeine: \(errmsg)")
            return
        }
        
        print("Caffeine ThreeShoot saved successfully")
        sqlite3_finalize(stmt)
    }
    
    func insertMainMixSQLite(_ todayDate: String) {
        // 한글 깨짐 방지
        var stmt: OpaquePointer?
        let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self)
        
        let queryString = "INSERT INTO caffeine (date, mg, name, memo) VALUES (?,?,?,?)"
        
        if sqlite3_prepare(db, queryString, -1, &stmt, nil) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error preparing insert: \(errmsg)")
            return
        }
        
        if sqlite3_bind_text(stmt, 1, todayDate, -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding date: \(errmsg)")
            return
        }
        
        if sqlite3_bind_text(stmt, 2, "70", -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding mg: \(errmsg)")
            return
        }
        
        if sqlite3_bind_text(stmt, 3, "믹스커피", -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding name: \(errmsg)")
            return
        }
        if sqlite3_bind_text(stmt, 4, "", -1, SQLITE_TRANSIENT) != SQLITE_OK {
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error binding memo: \(errmsg)")
            return
        }
        
        if sqlite3_step(stmt) != SQLITE_DONE {// 잘 끝나지 않았으면
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("failure inserting caffeine: \(errmsg)")
            return
        }
        
        print("Caffeine OneShoot saved successfully")
        sqlite3_finalize(stmt)
    }
}

