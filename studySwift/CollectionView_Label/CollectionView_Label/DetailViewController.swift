//
//  DetailViewController.swift
//  CollectionView_Label
//
//  Created by hyogang on 2021/07/26.
//

import UIKit

class DetailViewController: UIViewController {

    var receiveitem = ""
    
    @IBOutlet weak var lblHuman: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        lblHuman.text = receiveitem
    }
    
    func receiveItems(_ item: String){
        receiveitem = item
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
