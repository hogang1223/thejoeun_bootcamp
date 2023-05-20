import 'package:flutter/material.dart';
import 'package:textfield_login_app/share.dart';

class LoginSuccess extends StatelessWidget {
  const LoginSuccess({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      appBar: AppBar(
        title: Text('${Share.id}님! 환영합니다!'),
        backgroundColor: Colors.black,
      ),
      body: Center(
        child: Image.asset('images/hotel.JPG'),
      ),
    );
  }
}
