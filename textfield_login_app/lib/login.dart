import 'package:flutter/material.dart';
import 'package:textfield_login_app/share.dart';

class LogIn extends StatefulWidget {
  const LogIn({Key? key}) : super(key: key);

  @override
  _LogInState createState() => _LogInState();
}

class _LogInState extends State<LogIn> {
  String id = "root";
  String pw = "qwer1234";
  TextEditingController idController = TextEditingController();
  TextEditingController pwController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        FocusScope.of(context).unfocus();
      },
      child: Scaffold(
        appBar: AppBar(
          title: Text('Log In'),
        ),
        body: SingleChildScrollView(
          child: Center(
            child: Padding(
              padding: const EdgeInsets.all(20.0),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Icon(
                    Icons.account_circle,
                    size: 150.0,
                    color: Colors.blue,
                  ),
                  TextField(
                    controller: idController,
                    decoration: InputDecoration(labelText: '아이디를 입력하세요'),
                    keyboardType: TextInputType.text,
                  ),
                  SizedBox(
                    height: 30.0,
                  ),
                  TextField(
                    controller: pwController,
                    decoration: InputDecoration(labelText: '패스워드를 입력하세요'),
                    keyboardType: TextInputType.text,
                    obscureText: true,
                  ),
                  SizedBox(
                    height: 30.0,
                  ),
                  ElevatedButton(
                    style: ButtonStyle(
                      backgroundColor: MaterialStateProperty.all(Colors.blue),
                    ),
                    onPressed: () {
                      if (idController.text.isEmpty ||
                          pwController.text.isEmpty) {
                        // 빈값 체크
                        _showSnackBar(
                            context, '아이디 혹은 패스워드를 입력하세요!', Colors.red);
                      } else if (idController.text != id ||
                          pwController.text != pw) {
                        // id, pw 불일치
                        _showSnackBar(
                            context, '아이디 혹은 패스워드가 일치하지 않습니다!', Colors.blue);
                      } else {
                        // 로그인 성공 시
                        Share.id = idController.text;
                        _showDialog(context);
                      }
                    },
                    child: Text('Log In'),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }

  void _showSnackBar(BuildContext context, String result, Color resultColor) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(result),
        duration: Duration(seconds: 2),
        backgroundColor: resultColor,
      ),
    );
  }

  void _showDialog(BuildContext context) {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: Text('Welcome!'),
            content: Text('Log In Success'),
            actions: [
              ElevatedButton(
                style: ButtonStyle(
                  backgroundColor: MaterialStateProperty.all(Colors.blue),
                ),
                onPressed: () {
                  Navigator.of(context).pop();
                  Navigator.pushNamed(context, "/animal");
                },
                child: Text('OK'),
              ),
            ],
          );
        });
  }
}
