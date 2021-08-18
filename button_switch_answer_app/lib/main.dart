import 'package:flutter/material.dart';

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
  Color buttonColor = Colors.teal;
  var switchValue = false;

  // late는 지금은 값을 줄 수 없으나 후에 주겠다고 선언 -> ? 대신 사용
  late Color _tempColor;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Changed button color on Switch'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all(buttonColor),
              ),
              onPressed: () {
                if (buttonColor == Colors.teal) {
                  setState(() {
                    buttonColor = Colors.redAccent;
                    switchValue = true;
                  });
                } else {
                  setState(() {
                    buttonColor = Colors.teal;
                    switchValue = false;
                  });
                }
              },
              child: Text('Hello'),
            ),
            Switch(
                value: switchValue,
                onChanged: (value) {
                  if (value == true) {
                    _tempColor = Colors.red;
                  } else {
                    _tempColor = Colors.teal;
                  }
                  setState(() {
                    switchValue = value;
                    buttonColor = _tempColor;
                  });
                }),
          ],
        ),
      ),
    );
  }
}
