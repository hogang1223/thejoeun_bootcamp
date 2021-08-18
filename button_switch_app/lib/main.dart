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
  Color buttonColor = Colors.teal;
  bool switchValue = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Changed Button color on Switch'),
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
                    buttonText = "Flutter";
                    buttonColor = Colors.redAccent;
                    switchValue = true;
                  });
                } else {
                  setState(() {
                    buttonText = "Hello";
                    buttonColor = Colors.teal;
                    switchValue = false;
                  });
                }
              },
              child: Text('$buttonText'),
            ),
            Switch(
                value: switchValue,
                onChanged: (value) {
                  setState(() {
                    switchValue = value;
                    if (switchValue == false) {
                      buttonText = "Hello";
                      buttonColor = Colors.teal;
                    } else {
                      buttonText = "Flutter";
                      buttonColor = Colors.redAccent;
                    }
                  });
                }),
          ],
        ),
      ),
    );
  }
}
