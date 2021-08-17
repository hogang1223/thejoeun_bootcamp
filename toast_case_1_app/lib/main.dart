import 'package:flutter/material.dart';
import 'package:toast_case_1_app/myToast.dart';

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

class MyHomePage extends StatelessWidget {
  const MyHomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Toast message case 1'),
      ),
      body: MyToast(
          //       child: ElevatedButton(
          //         style: ButtonStyle(
          //             backgroundColor: MaterialStateProperty.all(Colors.blue)),
          //         onPressed: () {
          //           toastAction();
          //           // Fluttertoast.showToast(
          //           //   msg: "Toast Button is clicked",
          //           //   backgroundColor: Colors.black,
          //           //   textColor: Colors.white,
          //           //   fontSize: 14.0,
          //           //   toastLength: Toast.LENGTH_SHORT,
          //           // );
          //         },
          //         child: Text('Toast Button'),
          //       ),
          ),
    );
  }
}
