//
//  ViewController.swift
//  Quiz13-01_PageControlAndMap
//
//  Created by hyogang on 2021/07/23.
//

import UIKit
import MapKit

class ViewController: UIViewController {

    @IBOutlet weak var lblLocation: UILabel!
    @IBOutlet weak var myMap: MKMapView!
    @IBOutlet weak var pageControl: UIPageControl!
    
    let myLoc = CLLocationManager()
    
    let location = [("혜화문", 37.5878892, 127.0037098), ("흥인지문", 37.5711907, 127.00950), ("창의문", 37.5926027, 126.9664771), ("숙정문", 37.5956584, 126.9810576)]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        // PageControl
        pageControl.numberOfPages = location.count
        pageControl.currentPage = 0
        pageControl.pageIndicatorTintColor = UIColor.lightGray
        pageControl.currentPageIndicatorTintColor = UIColor.systemBlue
        
        // Map
        myLoc.delegate = self
        myLoc.requestWhenInUseAuthorization() // 승인 허용 문구 받아서 처리
        myLoc.startUpdatingLocation() // GPS 좌표 받기 시작
        myMap.showsUserLocation = true
        
        lblLocation.text = location[0].0
        mapMove(location[0].0, location[0].1, location[0].2)

    }

    @IBAction func changePage(_ sender: UIPageControl) {
        mapMove(location[pageControl.currentPage].0, location[pageControl.currentPage].1, location[pageControl.currentPage].2)
        lblLocation.text = location[pageControl.currentPage].0
    }
    
    
    func mapMove(_ title:String, _ lat:CLLocationDegrees, _ lon:CLLocationDegrees){
        let pLoc = CLLocationCoordinate2DMake(lat, lon)
        
        //배율
        let pSpan = MKCoordinateSpan(latitudeDelta: 0.02, longitudeDelta: 0.02)
        
        //좌표 정보
        let pRegion = MKCoordinateRegion(center: pLoc, span: pSpan)
        //현재 지도를 좌표 정보로 보이기
        myMap.setRegion(pRegion, animated: true)
        setPoint(pLoc, title)
    }
    
    
    func setPoint(_ loc:CLLocationCoordinate2D, _ title:String){
        let pin = MKPointAnnotation()
        
        pin.coordinate = loc
        pin.title = title
        
        myMap.addAnnotation(pin)
    }
    
    
    
} // ViewController
extension ViewController:CLLocationManagerDelegate{
    //실시간으로 위치를 알아야 하니까. didUpdateLocations
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        
        // 내위치 저장
        let lastLoc = locations.last
        
        //지도 보기
        myLoc.stopUpdatingLocation() // 좌표 받기 중지
        
    }
}

