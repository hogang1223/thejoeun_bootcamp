//
//  DBModel.swift
//  ServerJson_02
//
//  Created by hyogang on 2021/07/28.
//

import Foundation

class DBModel: NSObject{
    var scode: String?
    var sname: String?
    var sdept: String?
    var sphone: String?
    
    // Empty Constructor
    override init() {
    }
    
    init(scode: String, sname: String, sdept: String, sphone: String) {
        self.scode = scode
        self.sname = sname
        self.sdept = sdept
        self.sphone = sphone
    }
}
