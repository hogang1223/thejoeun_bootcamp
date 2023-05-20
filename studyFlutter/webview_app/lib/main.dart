import 'dart:async';

import 'package:flutter/material.dart';
import 'package:webview_flutter/webview_flutter.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key}) : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final Completer<WebViewController> _controller =
      Completer<WebViewController>();

  bool isLoading = true; // for indicator
  String site = "https://www.daum.net";
  TextEditingController siteController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        FocusScope.of(context).unfocus();
      },
      child: Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.redAccent,
          title: TextField(
            style: TextStyle(color: Colors.white),
            controller: siteController,
            decoration: InputDecoration(
              hintText: 'Site이름을 입력하세요',
            ),
          ),
          actions: [
            IconButton(
              onPressed: () {
                setState(() {
                  if (siteController.text.isEmpty) {
                    _showSnackBar(context);
                  } else {
                    site = siteController.text.trim();

                    if (site.substring(0, 3) != "http") {
                      site = "https://" + site;
                    }
                    FocusScope.of(context).unfocus();

                    _controller.future.then((value) => value.loadUrl(site));
                  }
                });
              },
              icon: Icon(
                Icons.search,
                color: Colors.white,
              ),
            )
          ],
        ),
        body: Stack(
          children: [
            WebView(
              initialUrl: site,
              javascriptMode: JavascriptMode.unrestricted,
              onWebViewCreated: (WebViewController webViewController) {
                _controller.complete(webViewController);
              },
              onPageFinished: (finish) {
                setState(() {
                  isLoading = false;
                });
              },
              onPageStarted: (start) {
                setState(() {
                  isLoading = true;
                });
              },
            ),
            isLoading
                ? Center(
                    child: CircularProgressIndicator(),
                  )
                : Stack(),
          ],
        ),
        floatingActionButton: FutureBuilder<WebViewController>(
            future: _controller.future,
            builder: (BuildContext context,
                AsyncSnapshot<WebViewController> controller) {
              if (controller.hasData) {
                return FloatingActionButton(
                    child: Icon(Icons.arrow_back),
                    backgroundColor: Colors.redAccent,
                    onPressed: () {
                      controller.data!.goBack();
                    });
              }
              return Container();
            }),
      ),
    );
  }

  void _showSnackBar(BuildContext context) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text('빈값'),
        duration: Duration(seconds: 1),
        backgroundColor: Colors.red,
      ),
    );
  }
}
