//
//  DBModel.swift
//  ServerJson_03
//
//  Created by hyogang on 2021/07/28.
//

import Foundation

class DBModel: NSObject{
    var scode: String?
    var sname: String?
    var sdept: String?
    var simage: String?
    var sphone: String?
    
    // Empty Constructor
    override init() {
    }
    
    init(scode: String, sname: String, sdept: String, simage: String, sphone: String) {
        self.scode = scode
        self.sname = sname
        self.sdept = sdept
        self.simage = simage
        self.sphone = sphone
    }
}
