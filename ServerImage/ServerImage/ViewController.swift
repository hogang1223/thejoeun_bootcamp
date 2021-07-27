//
//  ViewController.swift
//  ServerImage
//
//  Created by hyogang on 2021/07/27.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var imgView: UIImageView!
    
    var index = 0
    var images : [UIImage] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        appendImageViewList()
        imgView.image = images[0]
        //loadImage(index: 0)
        // Do any additional setup after loading the view.
    }
    
    func appendImageViewList(){
        for i in 1...6 {
            var url = URL(string: "http://192.168.2.12:8080/ios/flower_0\(i).png")
            var data = try? Data(contentsOf: url!)
            images.append(UIImage(data: data!)!)
        }
    }

    @IBAction func btnPrev(_ sender: UIButton) {
        index -= 1
        if index < 0{
            index = images.count - 1
        }else{
            imgView.image = images[index]
        }
    }
    
    
    @IBAction func btnNext(_ sender: UIButton) {
        index += 1
        if index >= images.count{
            index = 0
        }else{
            imgView.image = images[index]
        }
    }
    
    
    

    @IBAction func btnImage1(_ sender: UIButton) {
        let url : URL = URL(string: "http://192.168.2.12:8080/ios/flower_01.png")!
        let defaultSession = Foundation.URLSession(configuration: URLSessionConfiguration.default)
        let task = defaultSession.dataTask(with: url){(data, response, error) in
            if error != nil{
                print("Failed to download data")
            }else{
                print("Data is downloaded")
                DispatchQueue.main.async {
                    self.imgView.image = UIImage(data: data!)
                    if let image = UIImage(data: data!){
                        if let data = image.pngData(){
                            let filename = self.getDocumentDirectory().appendingPathComponent("copy.png")
                            try? data.write(to: filename)
                        }
                    }
                }
            }
        }
        task.resume()
    }
    
    @IBAction func btnImage2(_ sender: UIButton) {
        let url = URL(string: "http://192.168.2.12:8080/ios/flower_02.png")
        let data = try? Data(contentsOf: url!)
        imgView.image = UIImage(data: data!)
    }
    
    
    func getDocumentDirectory() -> URL{
        let paths = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        return paths[0]
    }
    
}

