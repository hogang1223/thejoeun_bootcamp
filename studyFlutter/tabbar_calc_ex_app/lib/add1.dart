import 'package:flutter/material.dart';

class Add1 extends StatefulWidget {
  const Add1({Key? key}) : super(key: key);

  @override
  _Add1State createState() => _Add1State();
}

class _Add1State extends State<Add1> {
  int num1 = 0;
  int num2 = 0;
  String result = "";

  TextEditingController num1Controller = TextEditingController();
  TextEditingController num2Controller = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
          children: [
            TextField(
              controller: num1Controller,
              decoration: InputDecoration(
                labelText: '첫번째 숫자를 입력하세요',
              ),
              keyboardType: TextInputType.number,
            ),
            SizedBox(
              height: 30.0,
            ),
            TextField(
              controller: num2Controller,
              decoration: InputDecoration(
                labelText: '두번째 숫자를 입력하세요',
              ),
              keyboardType: TextInputType.number,
            ),
            SizedBox(
              height: 30.0,
            ),
            ElevatedButton(
              onPressed: () {
                if (num1Controller.text.isEmpty ||
                    num2Controller.text.isEmpty) {
                  showSnackBar(context);
                  setState(() {
                    result = "";
                  });
                } else {
                  setState(() {
                    num1 = int.parse(num1Controller.text);
                    num2 = int.parse(num2Controller.text);
                    result = '입혁하신 숫자의 합은 ${num1 + num2}입니다.';
                  });
                }
              },
              child: Text('덧셈 계산'),
            ),
            SizedBox(
              height: 30.0,
            ),
            Text(
              result,
              style: TextStyle(
                  color: Colors.red,
                  fontSize: 24.0,
                  fontWeight: FontWeight.bold),
            )
          ],
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
