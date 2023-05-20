//
//  ViewController.swift
//  Quiz13-03_SwipeGestureAndPageControl
//
//  Created by hyogang on 2021/07/21.
//

import UIKit
import WebKit

class ViewController: UIViewController {

    @IBOutlet weak var webView: WKWebView!
    @IBOutlet weak var indicatorView: UIActivityIndicatorView!
    @IBOutlet weak var pageControl: UIPageControl!
    
    let urls = ["http://www.naver.com", "http://www.daum.net", "http://www.google.com"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        loadWebPage(url: urls[0])
        pageControl.numberOfPages = urls.count
        
        pageControl.currentPage = 0
        pageControl.pageIndicatorTintColor = UIColor.systemGreen
        pageControl.currentPageIndicatorTintColor = UIColor.orange
        
        // 한손가락 Gesture 구성
        makeSingleTouch()
        
        webView.navigationDelegate = self
        
    }
    // page load
    func loadWebPage(url: String){
        let myUrl = URL(string: url)
        let myRequest = URLRequest(url: myUrl!)
        webView.load(myRequest)
    }
    
    func makeSingleTouch(){

        let swipeLeft = UISwipeGestureRecognizer(target: self, action: #selector(ViewController.respondToSwipeGesture(_:)))
        swipeLeft.direction = UISwipeGestureRecognizer.Direction.left
        self.view.addGestureRecognizer(swipeLeft)
        
        let swipeRight = UISwipeGestureRecognizer(target: self, action: #selector(ViewController.respondToSwipeGesture(_:)))
        swipeRight.direction = UISwipeGestureRecognizer.Direction.right
        self.view.addGestureRecognizer(swipeRight)
    }
    
    @objc func respondToSwipeGesture(_ gesture: UIGestureRecognizer){
        if let swipeGesture = gesture as? UISwipeGestureRecognizer{
            switch swipeGesture.direction {
            case UISwipeGestureRecognizer.Direction.left:
                pageControl.currentPage += 1
                loadWebPage(url: urls[pageControl.currentPage])
            case UISwipeGestureRecognizer.Direction.right:
                pageControl.currentPage -= 1
                loadWebPage(url: urls[pageControl.currentPage])
            default:
                break
            }
        }
    }


} // ViewController


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

