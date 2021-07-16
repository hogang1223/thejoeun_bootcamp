//
//  ViewController.swift
//  Quiz07
//
//  Created by hyogang on 2021/07/16.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var tfKor: UITextField!
    @IBOutlet weak var tfMath: UITextField!
    @IBOutlet weak var tfEng: UITextField!
    
    lazy var tfScore: [UITextField]  = [tfKor, tfMath, tfEng]
    var subject: [String] = ["국어", "수학", "영어"]
    
    
    @IBOutlet weak var lblResult: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    
    @IBAction func btnCalc(_ sender: UIButton) {

        // null and score check
        for i in 0..<tfScore.count{
            if tfScore[i].text?.isEmpty == true || Int(tfScore[i].text!)! > 100 || Int(tfScore[i].text!)! < 0 {
                lblResult.text = "\(subject[i])의 점수가 이상해요. 확인하세요!"
                tfScore[i].becomeFirstResponder()
                return
            }
        }

        let avg : Double = calcAvg(kor: Int(tfKor.text!)!, math: Int(tfMath.text!)!, eng: Int(tfEng.text!)!)
        let grade : String = checkGrade(avg: avg)

        if avg > 100 || avg < 0{
            lblResult.text = grade
        }else{
            lblResult.text = "평균은 \(String(format:"%.2f", avg))이고 등급은 \(grade) 입니다."
        }
    } //btnCalc
    
    func calcAvg(kor:Int, math:Int, eng:Int) -> Double{
        return Double(kor + math + eng)/3
    }// calcAvg
    
    func checkGrade(avg:Double) -> String{
        switch avg {
        case 90..<101:
            return "수"
        case 80..<90:
            return "우"
        case 70..<80:
            return "미"
        case 60..<70:
            return "양"
        case 0..<60:
            return "가"
        default:
            return "올바르지 않은 점수입니다. 입력값을 재확인 해주세요."
        }
    }
    
    

} // ViewController

