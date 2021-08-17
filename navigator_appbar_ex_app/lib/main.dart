import 'package:flutter/material.dart';
import 'package:navigator_appbar_ex_app/home.dart';
import 'package:navigator_appbar_ex_app/received.dart';
import 'package:navigator_appbar_ex_app/send.dart';

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
          return Home();
        },
        '/send': (context) {
          return Send();
        },
        '/received': (context) {
          return Received();
        },
      },
    );
  }
}
