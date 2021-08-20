import 'package:flutter/material.dart';
import 'package:textfield_login_app/animalTabController.dart';
import 'package:textfield_login_app/login.dart';
import 'package:textfield_login_app/loginSuccess.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: '/',
      routes: {
        '/': (context) {
          return LogIn();
        },
        '/loginSuccess': (context) {
          return LoginSuccess();
        },
        '/animal': (context) {
          return AnimalTabController();
        }
      },
    );
  }
}
