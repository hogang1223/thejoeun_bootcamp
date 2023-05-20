import 'package:flutter/material.dart';
import 'package:tabbar_ex_app/pikachu01.dart';
import 'package:tabbar_ex_app/pikachu02.dart';

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

class _MyHomePageState extends State<MyHomePage>
    with SingleTickerProviderStateMixin {
  late TabController controller;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    controller = TabController(length: 2, vsync: this);
  }

  @override
  void dispose() {
    // TODO: implement dispose
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: TabBarView(
        children: [
          Pikachu01(),
          Pikachu02(),
        ],
        controller: controller,
      ),
      bottomNavigationBar: TabBar(
        labelColor: Colors.black,
        tabs: [
          Tab(
            icon: Icon(
              Icons.alarm,
              color: Colors.blue,
            ),
            text: 'Pikachu Scene #1',
          ),
          Tab(
            icon: Icon(
              Icons.account_circle,
              color: Colors.red,
            ),
            text: 'Pikachu Scene #2',
          ),
        ],
        controller: controller,
      ),
    );
  }
}
