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
  String resultText = "";
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        FocusScope.of(context).unfocus();
      },
      child: Scaffold(
        appBar: AppBar(
          title: Text("간단한 덧셈 계산기"),
          backgroundColor: Colors.teal,
        ),
        body: Center(
          child: Padding(
            padding: const EdgeInsets.all(20.0),
            child: Column(
              children: [
                TextField(
                  controller: num1Controller,
                  decoration: InputDecoration(
                    labelText: "더하고 싶은 첫 번째 숫자를 입력해주세요!",
                  ),
                  keyboardType: TextInputType.number,
                ),
                SizedBox(
                  height: 30.0,
                ),
                TextField(
                  controller: num2Controller,
                  decoration: InputDecoration(
                    labelText: "더하고 싶은 두 번째 숫자를 입력해주세요!",
                  ),
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
                      setState(() {
                        resultText = "";
                      });
                    } else {
                      setState(() {
                        num1 = int.parse(num1Controller.text);
                        num2 = int.parse(num2Controller.text);
                        resultText = "입력하신 숫자의 합은 ${num1 + num2} 입니다.";
                      });
                    }
                  },
                  icon: Icon(Icons.add),
                  label: Text("덧셈 계산"),
                ),
                SizedBox(
                  height: 40.0,
                ),
                Text(
                  resultText,
                  style: TextStyle(fontSize: 20.0, color: Colors.redAccent),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void showSnackBar(BuildContext context) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text("숫자를 입력해주세요!"),
        duration: Duration(seconds: 2),
        backgroundColor: Colors.teal,
      ),
    );
  }
}
