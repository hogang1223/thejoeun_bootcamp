//
//  ViewController.swift
//  Quiz8
//
//  Created by hyogang on 2021/07/16.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var tfScore: UITextField!
    
    @IBOutlet weak var tvSummary: UITextView!
    
    var index : Int = 0
    var subject : [String] = ["국어점수", "영어점수", "수학점수", "요약"]
    var score : [Int] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        lblTitle.text = subject[index]
        tvSummary.text = ""
        tvSummary.isEditable = false
    }
    
    @IBAction func btnCalc(_ sender: UIButton) {
     
     if tfScore.isHidden == false {
          if checkScoreisEmptyOrString(subjectScore: tfScore.text!) == false{
               tvSummary.text = "\(subject[index])가 이상해요!!"
          }else{
               score.append(Int(tfScore.text!)!)
               tfScore.text = ""
               tvSummary.text = ""
               index += 1
          }
     }else{
          index += 1
          tvSummary.text = ""
          tfScore.text = ""
     }
     
     switch index {
     case 0...2:
          tfScore.isHidden = false
          lblTitle.text = subject[index]
     case 3:
          lblTitle.text = subject[index]
          tfScore.isHidden = true
          tvSummary.text = """
          총점은 \(totalScore())입니다.
          평균은 \(String(format:"%.2f", calcAvg(totalScore: totalScore())))입니다.
          국어점수는 \(score[0])으로 \(compareScoreToAvg(score: score[0], avg: calcAvg(totalScore: totalScore())))
          영어점수는 \(score[1])으로 \(compareScoreToAvg(score: score[1], avg: calcAvg(totalScore: totalScore())))
          수학점수는 \(score[2])으로 \(compareScoreToAvg(score: score[2], avg: calcAvg(totalScore: totalScore())))
          """
          index = -1
     default:
          return
     }

    } // end btnCalc

     func totalScore() -> Int{
        var total:Int = 0

        for i in 0..<score.count {
            total += score[i]
        }
        return total
     }

     func calcAvg(totalScore:Int) -> Double{
        return Double(totalScore) / Double(score.count)
     }

     func compareScoreToAvg(score:Int, avg:Double) -> String {
        if Double(score) > avg {
          return "평균보다 높습니다."
        }else if Double(score) == avg{
          return "평균과 같습니다."
        }else{
          return "평균보다 낮습니다."
        }
     }
     
     func checkScoreisEmptyOrString(subjectScore:String?)-> Bool{
          if let checkScore = subjectScore{
               return checkIntOrString(checkScore)
          }
          return false
     }
     
     func checkIntOrString(_ subjectScore:String)-> Bool{
          if let iScore = Int(subjectScore){
               return true
          }else{
               return false
          }
     }
    
} // end ViewController

