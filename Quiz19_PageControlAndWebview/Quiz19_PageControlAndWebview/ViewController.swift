//
//  ViewController.swift
//  Quiz19_PageControlAndWebview
//
//  Created by hyogang on 2021/07/21.
//

import UIKit
import WebKit

class ViewController: UIViewController {

    @IBOutlet weak var lblSiteUrl: UILabel!
    @IBOutlet weak var webView: WKWebView!
    @IBOutlet weak var indicatorView: UIActivityIndicatorView!
    @IBOutlet weak var pageControl: UIPageControl!
    
    let urls = ["www.naver.com", "www.daum.net", "www.google.com", "www.nate.com", "www.youtube.com"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loadWebPage(url: "http://www.naver.com")
        lblSiteUrl.text = urls[0]
        
        pageControl.numberOfPages = urls.count
        pageControl.currentPage = 0
        pageControl.pageIndicatorTintColor = UIColor.systemTeal
        pageControl.currentPageIndicatorTintColor = UIColor.systemPink
        
        webView.navigationDelegate = self
    }
    
    @IBAction func pageChange(_ sender: UIPageControl) {
        lblSiteUrl.text = urls[pageControl.currentPage]
        loadWebPage(url: "http://" + urls[pageControl.currentPage])
    }

    // page load
    func loadWebPage(url: String){
        let myUrl = URL(string: url)
        let myRequest = URLRequest(url: myUrl!)
        webView.load(myRequest)
    }


}

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
