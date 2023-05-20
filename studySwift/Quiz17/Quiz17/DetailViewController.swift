//
//  DetailViewController.swift
//  Quiz17
//
//  Created by hyogang on 2021/07/23.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var imgView: UIImageView!
    @IBOutlet weak var lblDetail: UILabel!
    
    var detailImage : String = ""
    var detailContent : String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        lblDetail.text = detailContent
        imgView.image = UIImage(named: detailImage)

        // Do any additional setup after loading the view.
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
