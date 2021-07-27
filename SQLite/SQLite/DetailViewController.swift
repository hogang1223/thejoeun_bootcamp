//
//  DetailViewController.swift
//  SQLite
//
//  Created by hyogang on 2021/07/27.
//

import UIKit
import SQLite3

class DetailViewController: UIViewController {

    @IBOutlet weak var tfId: UITextField!
    @IBOutlet weak var tfName: UITextField!
    @IBOutlet weak var tfDept: UITextField!
    @IBOutlet weak var tfPhone: UITextField!
    
    var db: OpaquePointer?
    var receivedStudent : Students = Students.init(id: 0, name: "", dept: "", phone: "")
        
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        setData()
        
        let fileURL = try! FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false).appendingPathComponent("StudentData.sqlite")
        if sqlite3_open(fileURL.path, &db) != SQLITE_OK{
            print("error opening database")
        }
        if sqlite3_exec(db, "CREATE TABLE IF NOT EXISTS students(sid INTEGER PRIMARY KEY AUTOINCREMENT, sname TEXT, sdept TEXT, sphone TEXT)", nil, nil, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error creating table: \(errmsg)")
        }

        // Do any additional setup after loading the view.
    }
    
    func recieveItems(_ student:Students){
        receivedStudent = student
    }
    
    func setData(){
        tfId.text = String(receivedStudent.id)
        tfName.text = String(receivedStudent.name!)
        tfDept.text = String(receivedStudent.dept!)
        tfPhone.text = String(receivedStudent.phone!)
    }
    
    @IBAction func btnDelete(_ sender: UIButton) {
        tempDelete()
    }

    @IBAction func btnUpdate(_ sender: UIButton) {
        print("btnUpdate")
        tempUpdate()
    }
    
    // sqlite - delete
    func tempDelete(){
        
        var stmt:OpaquePointer?
        
        let id = receivedStudent.id
        let queryString = "DELETE FROM students WHERE sid = \(id)"
        //preparing the query
        if sqlite3_prepare(db, queryString, -1, &stmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error preparing delete \(errmsg)")
            return
        }
        if sqlite3_step(stmt) != SQLITE_DONE{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("failure deleting student: \(errmsg)")
            return
        }
        let resultAlert = UIAlertController(title: "결과", message: "삭제 되었습니다.", preferredStyle: .alert)
        let okAction = UIAlertAction(title: "OK", style: .default, handler: {ACTION in self.navigationController?.popViewController(animated: true)
        })
        resultAlert.addAction(okAction)
        present(resultAlert, animated: true, completion: nil)
    }
    
    // sqlite - update
    func tempUpdate(){
        
        var stmt: OpaquePointer?
        let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self) // 한글 깨짐 방지 ****
        
        let id = receivedStudent.id
        let name = tfName.text?.trimmingCharacters(in: .whitespacesAndNewlines)
        let dept = tfDept.text?.trimmingCharacters(in: .whitespacesAndNewlines)
        let phone = tfPhone.text?.trimmingCharacters(in: .whitespacesAndNewlines)
        
        let queryString = "UPDATE students SET sname = '\(name!)', sdept = '\(dept!)', sphone = '\(phone!)' WHERE sid = \(id)"
        
        if sqlite3_prepare(db, queryString, -1, &stmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error pareparing insert: \(errmsg)")
            return
        }
        
        // stmp 실행
        if sqlite3_step(stmt) != SQLITE_DONE{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("failure inserting student: \(errmsg)")
            return
        }
        
        let resultAlert = UIAlertController(title: "결과", message: "수정 되었습니다.", preferredStyle: .alert)
        let okAction = UIAlertAction(title: "OK", style: .default, handler: {ACTION in self.navigationController?.popViewController(animated: true)
        })
        resultAlert.addAction(okAction)
        present(resultAlert, animated: true, completion: nil)
    
    }
    
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
