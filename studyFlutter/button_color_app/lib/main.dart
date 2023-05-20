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
  String buttonText = "Hello";
  Color _color = Colors.blue;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Change button color & text'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all(_color),
              ),
              onPressed: () {
                if (_color == Colors.blue) {
                  // setState에 사용할 변수들은 전역변수로 세팅해놓는게 좋음
                  setState(() {
                    buttonText = "Flutter";
                    _color = Colors.amber;
                  });
                } else {
                  setState(() {
                    buttonText = "Hello";
                    _color = Colors.blue;
                  });
                }
              },
              child: Text('$buttonText'),
            ),
          ],
        ),
      ),
    );
  }
}
