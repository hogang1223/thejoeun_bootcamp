//
//  DetailViewController.swift
//  DBCRUD
//
//  Created by hyogang on 2021/07/28.
//

import UIKit

class DetailViewController: UIViewController {

    var receiveData: DBModel = DBModel.init()
    
    @IBOutlet weak var tfCode: UITextField!
    @IBOutlet weak var tfName: UITextField!
    @IBOutlet weak var tfDept: UITextField!
    @IBOutlet weak var tfPhone: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        settingData()

        // Do any additional setup after loading the view.
    }
    
    
    @IBAction func btnUpdate(_ sender: UIButton) {
        let code = tfCode.text
        let name = tfName.text
        let dept = tfDept.text
        let phone = tfPhone.text
        
        let updateModel = UpdateModel()
        let result = updateModel.updateItems(code: code!, name: name!, dept: dept!, phone: phone!)
        
        if result{
            let resultAlert = UIAlertController(title: "완료", message: "수정 되었습니다.", preferredStyle: .alert)
            let onAction = UIAlertAction(title: "OK", style: .default, handler: {ACTION in
                self.navigationController?.popViewController(animated: true)
            })
            
            resultAlert.addAction(onAction)
            present(resultAlert, animated: true, completion: nil)
        }else{
            let resultAlert = UIAlertController(title: "실패", message: "에러가 발생했습니다.", preferredStyle: .alert)
            let onAction = UIAlertAction(title: "OK", style: .default, handler: nil)
            
            resultAlert.addAction(onAction)
            present(resultAlert, animated: true, completion: nil)
        }
        
    }
    
    
    @IBAction func btnDelete(_ sender: UIButton) {
        
        let deleteCheck = UIAlertController(title: "삭제", message: "삭제하시겠습니까?", preferredStyle: .alert)
        let yse = UIAlertAction(title: "yes", style: .default, handler: {ACTION in self.deleteItem()})
        let no = UIAlertAction(title: "no", style: .default, handler: nil)
        deleteCheck.addAction(no)
        deleteCheck.addAction(yse)
        
        present(deleteCheck, animated: true, completion: nil)
    }
    
    func deleteItem(){
        let code = tfCode.text
        
        let deleteModel = DeleteModel()
        let result = deleteModel.deleteItems(code: code!)
        
        if result{
            let resultAlert = UIAlertController(title: "완료", message: "삭제 되었습니다.", preferredStyle: .alert)
            let onAction = UIAlertAction(title: "OK", style: .default, handler: {ACTION in
                self.navigationController?.popViewController(animated: true)
            })
            
            resultAlert.addAction(onAction)
            present(resultAlert, animated: true, completion: nil)
        }else{
            let resultAlert = UIAlertController(title: "실패", message: "에러가 발생했습니다.", preferredStyle: .alert)
            let onAction = UIAlertAction(title: "OK", style: .default, handler: nil)
            
            resultAlert.addAction(onAction)
            present(resultAlert, animated: true, completion: nil)
        }
    }
    
    func receiveItem(item: DBModel){
        receiveData = item
    }
    
    func settingData(){
        tfCode.text = receiveData.scode!
        tfName.text = receiveData.sname!
        tfDept.text = receiveData.sdept!
        tfPhone.text = receiveData.sphone!
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
