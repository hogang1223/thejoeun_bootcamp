//
//  ViewController.swift
//  QuizPickerViewGugudan
//
//  Created by hyogang on 2021/07/20.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var lblDan: UILabel!
    @IBOutlet weak var pickerVIew: UIPickerView!
    @IBOutlet weak var tvResult: UITextView!
    
    let danArray = Array(2...9)
    var maxArrayNum = 0
    let viewColumn = 1
    var result : String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        maxArrayNum = danArray.count
        
        for i in 1...9 {
            result += "\(danArray[0]) x \(i) = \( danArray[0] * i) \n"
        }
        lblDan.text = "\(danArray[0])단"
        tvResult.text = result
        pickerVIew.dataSource = self
        pickerVIew.delegate = self
        
    }


}


extension ViewController: UIPickerViewDataSource{
    
    // pickerView의 컬럼 갯수
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return viewColumn
    }
    
    // 출력할 이미지 파일 갯수
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return maxArrayNum
    }
    
}

extension ViewController: UIPickerViewDelegate{
    
    // PickerView에 Title 입히기
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return "\(danArray[row])단"
    }
    
    // PickerView에 이미지 선택
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        result = ""
        for i in 1...9 {
            result += "\(danArray[row]) x \(i) = \( danArray[row] * i) \n"
        }
        
        lblDan.text = "\(danArray[row])단"
        tvResult.text = result
    }
    
}

