//
//  ViewController.swift
//  Quiz18_PickerViewAndWebView
//
//  Created by hyogang on 2021/07/21.
//

import UIKit
import WebKit

class ViewController: UIViewController {

    
    @IBOutlet weak var pickerView: UIPickerView!
    @IBOutlet weak var webView: WKWebView!
    @IBOutlet weak var indicatorView: UIActivityIndicatorView!
    
    let siteUrls = [("네이버", "www.naver.com"), ("구글", "www.google.com"), ("다음","www.daum.net"), ("네이트","www.nate.com"), ("유튜브","www.youtube.com")]
    
    let urls = ["www.naver.com", "www.google.com", "www.daum.net", "www.nate.com", "www.youtube.com"]
    let sites = ["네이버", "구글", "다음", "네이트", "유튜브"]
    let viewColumn = 1
    var maxArrayNum = 0

    override func viewDidLoad() {
        super.viewDidLoad()
        maxArrayNum = siteUrls.count
        
        loadWebPage(url: "http://www.naver.com")
        webView.navigationDelegate = self
        pickerView.dataSource = self
        pickerView.delegate = self
    }
    
    // page load
    func loadWebPage(url: String){
        let myUrl = URL(string: url)
        let myRequest = URLRequest(url: myUrl!)
        webView.load(myRequest)
    }


} // ViewController

// WKNavigationDelegate
extension ViewController: WKNavigationDelegate{
    
    // Indicator 시작
    func webView(_ webView: WKWebView, didCommit navigation: WKNavigation!) {
        indicatorView.startAnimating()
        indicatorView.isHidden = false
    }
    
    // Indicator 종료
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        indicatorView.stopAnimating()
        indicatorView.isHidden = true
    }
    
    // Error 발생시
    func webView(_ webView: WKWebView, didFail navigation: WKNavigation!, withError error: Error) {
        indicatorView.isHidden = true
    }
}

// PickerView
extension ViewController: UIPickerViewDataSource{
    // pickerView의 컬럼 갯수
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return viewColumn
    }
    // urls 배열 수
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return maxArrayNum
    }
}
extension ViewController: UIPickerViewDelegate{
    
    // PickerView에 Title 입히기
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return siteUrls[row].0
    }
    
    // webView띄우기
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        loadWebPage(url: "http://" + siteUrls[row].1)
    }
    
}

