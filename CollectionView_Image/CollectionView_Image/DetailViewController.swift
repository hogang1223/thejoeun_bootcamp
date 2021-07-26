//
//  DetailViewController.swift
//  CollectionView_Image
//
//  Created by hyogang on 2021/07/26.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var imgView: UIImageView!
    var image = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        imgView.image = UIImage(named: image)
    }
    
    func receiveImage(_ item: String){
        image = item
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
