//
//  DeleteModel.swift
//  DBCRUD
//
//  Created by hyogang on 2021/07/28.
//

import Foundation

class DeleteModel{
    
    var urlPath = macIP + "studentDelete_ios.jsp"
    
    func deleteItems(code: String) -> Bool{
        
        var result: Bool = true
        let urlAdd = "?code=\(code)"
        urlPath = urlPath + urlAdd
        
        // 한글 url encoding
        urlPath = urlPath.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)!
        
        let url: URL = URL(string: urlPath)!
        let defaultSession = URLSession(configuration: URLSessionConfiguration.default)
        let task = defaultSession.dataTask(with: url){(data, response, error) in
            if error != nil{
                print("Failed to delete data")
                result = false
            }else{
                print("Data is deleted")
                result = true
            }
        }
        task.resume()
        return result
    }
}
