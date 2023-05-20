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
  TextEditingController addController = TextEditingController();
  TextEditingController subController = TextEditingController();
  TextEditingController mulController = TextEditingController();
  TextEditingController divController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    // keyboard 어디 눌러도 내리기
    return GestureDetector(
      onTap: () {
        FocusScope.of(context).unfocus();
      },
      child: Scaffold(
        appBar: AppBar(
          title: Text('간단한 계산기'),
        ),
        body: SingleChildScrollView(
          child: Center(
            child: Padding(
              padding: const EdgeInsets.all(20.0),
              child: Column(
                children: [
                  TextField(
                    controller: num1Controller,
                    decoration: InputDecoration(labelText: '첫번째 숫자를 입력하세요'),
                    keyboardType: TextInputType.number,
                  ),
                  SizedBox(
                    height: 10,
                  ),
                  TextField(
                    controller: num2Controller,
                    decoration: InputDecoration(labelText: '두번째 숫자를 입력하세요'),
                    keyboardType: TextInputType.number,
                  ),
                  SizedBox(
                    height: 10,
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      ElevatedButton(
                        onPressed: () {
                          if (num1Controller.text == "" ||
                              num2Controller.text == "") {
                            showSnackBar(context);
                          } else {
                            num1 = int.parse(num1Controller.text);
                            num2 = int.parse(num2Controller.text);

                            addController.text = "${num1 + num2}";
                            subController.text = "${num1 - num2}";
                            mulController.text = "${num1 * num2}";
                            divController.text = "${num1 / num2}";
                          }
                        },
                        child: Text('계산하기'),
                      ),
                      SizedBox(
                        width: 30,
                      ),
                      ElevatedButton(
                        onPressed: () {
                          num1 = 0;
                          num2 = 0;
                          num1Controller.text = "";
                          num2Controller.text = "";
                          addController.text = "";
                          subController.text = "";
                          mulController.text = "";
                          divController.text = "";
                        },
                        child: Text('지우기'),
                        style: ButtonStyle(
                            backgroundColor:
                                MaterialStateProperty.all(Colors.redAccent)),
                      ),
                    ],
                  ),
                  SizedBox(
                    height: 10,
                  ),
                  TextField(
                    controller: addController,
                    decoration: InputDecoration(labelText: '덧셈결과'),
                    readOnly: true,
                  ),
                  SizedBox(
                    height: 10,
                  ),
                  TextField(
                    controller: subController,
                    decoration: InputDecoration(labelText: '뺄셈결과'),
                    readOnly: true,
                  ),
                  SizedBox(
                    height: 10,
                  ),
                  TextField(
                    controller: mulController,
                    decoration: InputDecoration(labelText: '곱셈결과'),
                    readOnly: true,
                  ),
                  SizedBox(
                    height: 10,
                  ),
                  TextField(
                    controller: divController,
                    decoration: InputDecoration(labelText: '나눗셈결과'),
                    readOnly: true,
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }

  void showSnackBar(BuildContext context) {
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(
      content: Text("숫자를 입력해주세요!"),
      backgroundColor: Colors.teal,
    ));
  }
}
