//
//  tempInsert.swift
//  Caffeine
//
//  Created by hyogang on 2021/08/21.
//

import Foundation
import SQLite3

class TempInsertDB{
    
    var db: OpaquePointer?
    // tempData    
        let tempDate : [String] = ["2021년 08월 12일", "2021년 08월 12일", "2021년 08월 12일", "2021년 08월 12일", "2021년 08월 15일", "2021년 08월 15일", "2021년 08월 15일", "2021년 08월 15일", "2021년 08월 19일", "2021년 08월 19일"]
        let tempMg : [Int] = [70,255, 70, 150, 75, 70, 150, 70, 255, 70]
        let tempName : [String] = ["믹스커피", "Espresso 3shot", "믹스커피", "Espresso 2shot", "Espresso 1shot", "믹스커피", "Espresso 2shot", "믹스커피", "Espresso 3shot", "믹스커피"]
        let tempMemo : [String] = ["카누", "", "맥심 모카골드", "빽다방 아이스아메리카노", "", "맥심 모카골드", "", "", "", ""]
    
    
    func loadData(){
        let fileURL = try! FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false).appendingPathComponent("Caffeine.sqlite")
        if sqlite3_open(fileURL.path, &db) != SQLITE_OK{
            print("error opening database")
        }
        
        if sqlite3_exec(db, "CREATE TABLE IF NOT EXISTS caffeine(no INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, mg INTEGER, name TEXT)", nil, nil, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error creating table: \(errmsg)")
        }
    }
    
    
    
    func tempInsert(){
        var stmt: OpaquePointer?
        let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self) // 한글 깨짐 방지 ****
        
        //CREATE TABLE IF NOT EXISTS caffeine(no INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, mg INTEGER, name TEXT)

        let queryString = "INSERT INTO caffeine(date, mg, name, memo) VALUES (?,?,?,?)"
        
        // insert
        for i in 0..<tempDate.count{
//            let queryString = "INSERT INTO caffeine(date, mg, name) VALUES (\(tempDate[i]),\(tempMg[i]),\(tempName[i])"
            
            if sqlite3_prepare(db, queryString, -1, &stmt, nil) != SQLITE_OK{
                let errmsg = String(cString: sqlite3_errmsg(db)!)
                print("error pareparing insert: \(errmsg)")
                return
            }
            
            if sqlite3_bind_text(stmt, 1, tempDate[i], -1, SQLITE_TRANSIENT) != SQLITE_OK{
                let errmsg = String(cString: sqlite3_errmsg(db)!)
                print("error binding name: \(errmsg)")
                return
            }
            if sqlite3_bind_int(stmt!, 2, Int32(tempMg[i])) != SQLITE_OK{
                let errmsg = String(cString: sqlite3_errmsg(db)!)
                print("error binding dept: \(errmsg)")
                return
            }
            if sqlite3_bind_text(stmt, 3, tempName[i], -1, SQLITE_TRANSIENT) != SQLITE_OK{
                let errmsg = String(cString: sqlite3_errmsg(db)!)
                print("error binding phone: \(errmsg)")
                return
            }
            if sqlite3_bind_text(stmt, 4, tempMemo[i], -1, SQLITE_TRANSIENT) != SQLITE_OK{
                let errmsg = String(cString: sqlite3_errmsg(db)!)
                print("error binding phone: \(errmsg)")
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
}
