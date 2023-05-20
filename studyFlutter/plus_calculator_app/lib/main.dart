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
  int num1 = 0;
  int num2 = 0;
  TextEditingController num1Controller = TextEditingController();
  TextEditingController num2Controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        FocusScope.of(context).unfocus();
      },
      child: Scaffold(
        appBar: AppBar(
          title: Text('간단한 덧셈 계산기'),
          backgroundColor: Colors.teal,
        ),
        body: Center(
          child: Padding(
            padding: const EdgeInsets.all(20.0),
            child: Column(
              children: [
                Padding(
                  padding: const EdgeInsets.all(20.0),
                  child: Text(
                    '덧셈 결과 : ${num1 + num2}',
                    style: TextStyle(fontSize: 25.0),
                  ),
                ),
                TextField(
                  controller: num1Controller,
                  decoration:
                      InputDecoration(labelText: "더하고 싶은 첫번째 숫자를 입력하세요!"),
                  keyboardType: TextInputType.number,
                ),
                SizedBox(
                  height: 30.0,
                ),
                TextField(
                  controller: num2Controller,
                  decoration:
                      InputDecoration(labelText: "더하고 싶은 두번째 숫자를 입력하세요!"),
                  keyboardType: TextInputType.number,
                ),
                SizedBox(
                  height: 30.0,
                ),
                ElevatedButton.icon(
                    onPressed: () {
                      if (num1Controller.text == "" ||
                          num1Controller.text.isEmpty ||
                          num2Controller.text == "" ||
                          num2Controller.text.isEmpty) {
                        showSnackBar(context);
                      } else {
                        setState(() {
                          num1 = int.parse(num1Controller.text);
                          num2 = int.parse(num2Controller.text);
                        });
                      }
                    },
                    icon: Icon(Icons.add),
                    label: Text('덧셈 계산')),
                ElevatedButton(
                  onPressed: () {
                    if (num1Controller.text == "" ||
                        num1Controller.text.isEmpty ||
                        num2Controller.text == "" ||
                        num2Controller.text.isEmpty) {
                      showSnackBar(context);
                    } else {
                      num1 = int.parse(num1Controller.text);
                      num2 = int.parse(num2Controller.text);
                    }
                  },
                  child: Row(
                    children: [
                      Icon(Icons.add),
                      SizedBox(
                        width: 20.0,
                      ),
                      Text('덧셈 계산'),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void showSnackBar(BuildContext context) {
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(
      content: Text('숫자를 입력하세요!'),
      duration: Duration(seconds: 2),
      backgroundColor: Colors.teal,
    ));
  }
}
