import 'package:alert_dialog_exercise_app/firstPage.dart';
import 'package:alert_dialog_exercise_app/secondPage.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: "/",
      routes: {
        "/": (context) {
          return FirstPage();
        },
        "/next": (context) {
          return SecondPage();
        },
      },
    );
  }
}
