//
//  AddViewController.swift
//  Quiz17
//
//  Created by hyogang on 2021/07/23.
//

import UIKit

class AddViewController: UIViewController {

    @IBOutlet weak var imgView: UIImageView!
    @IBOutlet weak var pickerView: UIPickerView!
    @IBOutlet weak var tfContent: UITextField!
    
    let viewColumn = 1
    var maxArrayCount = 0
    
    let imageFile = ["cart.png", "clock.png","pencil.png"]
    var imageArray = [UIImage?]()
    var currentImageName = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        for i in 0..<imageFile.count{
                   let image = UIImage(named: imageFile[i])
                   imageArray.append(image)
               }
        maxArrayCount = imageArray.count
        
        pickerView.dataSource = self
        pickerView.delegate = self
        pickerView.selectRow(1, inComponent: 0, animated: true)
        
    }
    
    
    @IBAction func btnAdd(_ sender: UIButton) {
        items.append(tfContent.text!)
        itemsImageFile.append(currentImageName)
        navigationController?.popViewController(animated: true)
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

extension AddViewController:UIPickerViewDataSource{
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return viewColumn
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return maxArrayCount
    }
}

extension AddViewController:UIPickerViewDelegate{
    
//    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> UIImage? {
//        return UIImage(named: itemsImageFile[row])
//    }
    func pickerView(_ pickerView: UIPickerView, viewForRow row: Int, forComponent component: Int, reusing view: UIView?) -> UIView {
        let imageView = UIImageView(image: imageArray[row])
        imageView.frame = CGRect(x:0, y:0, width: 30, height: 30)
        
        return imageView
    }
    
    func pickerView(_ pickerView: UIPickerView, rowHeightForComponent component: Int) -> CGFloat {
        40
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        imgView.image = imageArray[row]
        currentImageName = imageFile[row]
    }
}
