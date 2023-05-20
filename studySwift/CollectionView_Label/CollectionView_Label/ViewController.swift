//
//  ViewController.swift
//  CollectionView_Label
//
//  Created by hyogang on 2021/07/26.
//

import UIKit

var list = ["유비", "관우", "장비", "조조", "여포", "동탁", "초선", "손견", "장양", "손책"]

class ViewController: UIViewController {

    @IBOutlet weak var collectionView: UICollectionView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        collectionView.delegate = self
        collectionView.dataSource = self
    }
    
    override func viewWillAppear(_ animated: Bool) {
        collectionView.reloadData()
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "sgDetail"{
            let cell = sender as! CollectionViewCell
            let indexPath = self.collectionView.indexPath(for: cell)
            let detailView = segue.destination as! DetailViewController
            detailView.receiveItems(list[indexPath!.row])
        }
    }
    


} // ViewController

// extension 동시에 여러개 가능 (interface 같네...)

extension ViewController: UICollectionViewDataSource, UICollectionViewDelegate{
    
    // cell의 갯수
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return list.count
    }
    
    // cell의 구성
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "myCell", for: indexPath) as! CollectionViewCell
        
        cell.backgroundColor = .lightGray
        
        cell.lblHuman.text = list[indexPath.row]
        cell.lblHuman.backgroundColor = .yellow
        
        return cell
    }
    
} // extention CollectionView

// Cell Layout 정의
extension ViewController: UICollectionViewDelegateFlowLayout{
    
    // 위 아래 간격
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return 1
    }
    
    // cell size(옆 라인 고려하여 설정)
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        
        // 3등분하여 배치, 옆 간격이 1이므로 1을 빼줌
        let width = collectionView.frame.width / 3 - 1
        let size = CGSize(width: width, height: width)
        
        return size
    }
    
    
} // extension UICollectionViewDelegateFlowLayout
