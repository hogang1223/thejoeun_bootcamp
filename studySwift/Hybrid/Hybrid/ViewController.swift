//
//  ViewController.swift
//  Hybrid
//
//  Created by hyogang on 2021/07/21.
//

import UIKit
import WebKit // <<<<<<<<<< web Kit webView

class ViewController: UIViewController {

    
    @IBOutlet weak var tfUrl: UITextField!
    @IBOutlet weak var myWebView: WKWebView!
    @IBOutlet weak var myActivityIndicator: UIActivityIndicatorView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        loadWebPage(url: "https://www.naver.com")
        myWebView.navigationDelegate = self
    }
    
    // button Site 1
    @IBAction func btnGoogle(_ sender: UIButton) {
        loadWebPage(url: "https://www.google.com")
    }

    // button Site 2
    @IBAction func btnDaum(_ sender: UIButton) {
        loadWebPage(url: "https://www.daum.net")
    }
    
    // btn Go tfUrl Page
    @IBAction func btnGoInputPage(_ sender: UIButton) {
        guard let inputUrl : String = tfUrl.text else{ return }
        loadWebPage(url: checkInputUrl(inputUrl: inputUrl))
        tfUrl.text = ""
    }
    
    // page load
    func loadWebPage(url: String){
        let myUrl = URL(string: url)
        let myRequest = URLRequest(url: myUrl!)
        myWebView.load(myRequest)
    }
    
    // tfUrl "http://"로 시작하는지 체크
    func checkInputUrl(inputUrl: String) -> String{
        if inputUrl.hasPrefix("http://") {
            return inputUrl
        }else{
            return "http://" + inputUrl
        }
    }
    
    // button Html
    @IBAction func btnHtml(_ sender: UIButton) {
        let htmlString = """
            <html>
                <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                </head>
                <body>
                    <h1>HTML String</h1>
                    <p>String변수를 이용한 웹페이지</p>
                    <p><a href = "http://jtbc.joins.com">JTBC</a>로 이동</p>
                </body>
            </html>
            """
        myWebView.loadHTMLString(htmlString, baseURL: nil)
    }
    
    // button File
    @IBAction func btnFile(_ sender: UIButton) {
        let filePath = Bundle.main.path(forResource: "htmlView", ofType: "html")
        let myUrl = URL(fileURLWithPath: filePath!)
        let myRequest = URLRequest(url: myUrl)
        myWebView.load(myRequest)
    }
    
    
    
    // ToolBar Button Action -------
    @IBAction func btnStop(_ sender: UIBarButtonItem) {
        myWebView.stopLoading()
    }

    @IBAction func btnReload(_ sender: UIBarButtonItem) {
        myWebView.reload()
    }
    
    @IBAction func btnGoBack(_ sender: UIBarButtonItem) {
        myWebView.goBack()
    }

    @IBAction func btnGoForward(_ sender: UIBarButtonItem) {
        myWebView.goForward()
    }
    // ------- ToolBar Button Action
    
} // ViewController

extension ViewController: WKNavigationDelegate{
    
    // Indicator 시작
    func webView(_ webView: WKWebView, didCommit navigation: WKNavigation!) {
        myActivityIndicator.startAnimating()
        myActivityIndicator.isHidden = false
    }
    
    // Indicator 종료
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        myActivityIndicator.stopAnimating()
        myActivityIndicator.isHidden = true
    }
    
    // Error 발생시
    func webView(_ webView: WKWebView, didFail navigation: WKNavigation!, withError error: Error) {
        myActivityIndicator.isHidden = true
    }
}
