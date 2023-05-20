import 'package:flutter/material.dart';

class Add2 extends StatefulWidget {
  const Add2({Key? key}) : super(key: key);

  @override
  _Add2State createState() => _Add2State();
}

class _Add2State extends State<Add2> {
  int num1 = 0;
  int num2 = 0;
  String result = "";

  TextEditingController num1Controller = TextEditingController();
  TextEditingController num2Controller = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        FocusScope.of(context).unfocus();
      },
      child: Scaffold(
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
              ElevatedButton.icon(
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
                      result = "덧셈결과 ${num1 + num2}";
                    });
                  }
                },
                icon: Icon(Icons.add),
                label: Text("덧셈 계산"),
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
