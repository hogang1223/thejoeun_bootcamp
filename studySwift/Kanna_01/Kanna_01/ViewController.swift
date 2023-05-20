//
//  ViewController.swift
//  Kanna_01
//
//  Created by hyogang on 2021/07/26.
//

import UIKit
import Kanna


class ViewController: UIViewController {

    let html = """
        <html>
            <body>
                <h1>My First Heading</h1>
                <p>과일 상점 과일 종류</p>
                <a class='mylink' id='applelink' href='http://www.apple.com'>Apple</a>
                <ul>
                    <li>사과</li>
                    <li>바나나</li>
                    <li>복숭아</li>
                    <li>포도</li>
                </ul>
            </body>
        </html>
        """
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        dataCrawling()
    }
    
    func dataCrawling(){
        do{
            let doc = try HTML(html: html, encoding: .utf8)
            print(doc.text!)
            print("---------------------")
            for p in doc.xpath("//p"){
                print(p.text!)
            }
            print("---------------------")
            for li in doc.xpath("//li"){
                print(li.text!)
            }
        }catch let error{
            print("Error : \(error)")
        }
    }
}

