//
//  JsonModel.swift
//  ServerJson_03
//
//  Created by hyogang on 2021/07/28.
//

import Foundation

protocol JsonModelProtocol{
    func itemDownloaded(items: NSArray)
}

let macIP = "http://192.168.219.102:8080/ios/"

class JsonModel{
    var delegate: JsonModelProtocol!
    let urlPath = macIP + "student.json"
    
    func downloadItems(){
        let url: URL = URL(string: urlPath)!
        let defaultSession = URLSession(configuration: URLSessionConfiguration.default)
        let task = defaultSession.dataTask(with: url){(data, response, error) in
            if error != nil{
                print("Failed to dwonload data")
            }else{
                print("Data is downloaded")
                self.parseJson(data!)
            }
        }
        task.resume()
    }
    
    func parseJson(_ data: Data){
        var jsonResult = NSArray()
        do{
            jsonResult = try JSONSerialization.jsonObject(with: data, options: JSONSerialization.ReadingOptions.allowFragments) as! NSArray
        }catch let error as NSError{
            print(error)
        }
        
        var jsonElement = NSDictionary()
        let locations = NSMutableArray()
        
        for i in 0..<jsonResult.count{
            jsonElement = jsonResult[i] as! NSDictionary
            if let scode = jsonElement["code"] as? String,
               let sname = jsonElement["name"] as? String,
               let sdept = jsonElement["dept"] as? String,
               let simage = jsonElement["image"] as? String,
               let sphone = jsonElement["phone"] as? String{
                let query = DBModel(scode: scode, sname: sname, sdept: sdept, simage: simage, sphone: sphone)
                locations.add(query)
            }
        }
        DispatchQueue.main.async(execute: {() -> Void in
            self.delegate.itemDownloaded(items: locations)
        })
    }
}
