//
//  DetailViewController.swift
//  Kanna_04
//
//  Created by hyogang on 2021/07/27.
//

import UIKit
import WebKit

class DetailViewController: UIViewController {

    @IBOutlet weak var indicator: UIActivityIndicatorView!
    @IBOutlet weak var webView: WKWebView!
    var hreflink = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        loadWebPage(url: "https://www.rottentomatoes.com/\(hreflink)")
        webView.navigationDelegate = self

        // Do any additional setup after loading the view.
    }
    
    func receiveItems(_ item: String){
        hreflink = item
    }
    
    
    func loadWebPage(url: String){
        let myUrl = URL(string: url)
        let myRequest = URLRequest(url: myUrl!)
        webView.load(myRequest)
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

extension DetailViewController: WKNavigationDelegate{
    
    // Indicator 시작
    func webView(_ webView: WKWebView, didCommit navigation: WKNavigation!) {
        indicator.startAnimating()
        indicator.isHidden = false
    }
    
    // Indicator 종료
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        indicator.stopAnimating()
        indicator.isHidden = true
    }
    
    // Error 발생시
    func webView(_ webView: WKWebView, didFail navigation: WKNavigation!, withError error: Error) {
        indicator.isHidden = true
    }
}
