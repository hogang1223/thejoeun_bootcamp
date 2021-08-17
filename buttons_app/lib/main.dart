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

class MyHomePage extends StatelessWidget {
  const MyHomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Buttons'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextButton(
              onPressed: () {
                print("Text Button");
              },
              onLongPress: () {
                print("Long pressed text button");
              },
              child: Text(
                'text Button',
                style: TextStyle(fontSize: 20.0),
              ),
              style: TextButton.styleFrom(primary: Colors.lime),
            ),
            ElevatedButton(
              onPressed: () {
                print('Elevated button');
              },
              child: Text('Elevated button'),
              style: ElevatedButton.styleFrom(
                  primary: Colors.orangeAccent,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10.0),
                  )),
            ),
            OutlinedButton(
              onPressed: () {
                print('outlined button');
              },
              child: Text('outlined button'),
              style: OutlinedButton.styleFrom(
                primary: Colors.pink,
                side: BorderSide(color: Colors.pink, width: 1.0),
              ),
            ),
            TextButton.icon(
              onPressed: () {
                print('Icon button');
              },
              icon: Icon(
                Icons.home,
                size: 30.0,
              ),
              label: Text('Go home'),
              style: TextButton.styleFrom(primary: Colors.teal),
            ),
            ElevatedButton.icon(
              onPressed: () {
                print('Elevated Icon');
              },
              icon: Icon(
                Icons.home,
                size: 20.0,
              ),
              label: Text('Go home'),
              style: ElevatedButton.styleFrom(primary: Colors.deepPurple),
            ),
            OutlinedButton.icon(
              onPressed: () {
                print('oulined icon');
              },
              icon: Icon(
                Icons.home,
                size: 20.0,
              ),
              label: Text('Go home'),
              style: OutlinedButton.styleFrom(
                primary: Colors.deepPurple,
                side: BorderSide(color: Colors.deepPurple, width: 1.0),
              ),
            ),
            ElevatedButton.icon(
              onPressed: () {
                print('Elevated Icon');
              },
              icon: Icon(
                Icons.home,
                size: 20.0,
              ),
              label: Text('Go home'),
              style: ElevatedButton.styleFrom(
                primary: Colors.pink[50],
              ),
            ),
            ButtonBar(
              alignment: MainAxisAlignment.center,
              buttonPadding: EdgeInsets.all(20.0),
              children: [
                TextButton(
                  onPressed: () {},
                  child: Text('TextButton'),
                ),
                ElevatedButton(
                  onPressed: () {},
                  child: Text('Elevated Button'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
