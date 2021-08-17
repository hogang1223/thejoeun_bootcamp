import 'package:flutter/material.dart';

class Home extends StatelessWidget {
  const Home({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Navigator_AppBar'),
        actions: [
          IconButton(
              onPressed: () {
                sendEmail(context);
              },
              icon: Icon(Icons.email)),
          IconButton(
              onPressed: () {
                receivedEmail(context);
              },
              icon: Icon(Icons.email_outlined)),
        ],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
                style: ButtonStyle(
                  backgroundColor: MaterialStateProperty.all(Colors.lightGreen),
                ),
                onPressed: () {
                  sendEmail(context);
                },
                child: Text('send email')),
            ElevatedButton(
                style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.orange)),
                onPressed: () {
                  receivedEmail(context);
                },
                child: Text('received email')),
          ],
        ),
      ),
    );
  }

  void sendEmail(BuildContext context) {
    Navigator.pushNamed(context, '/send');
  }

  void receivedEmail(BuildContext context) {
    Navigator.pushNamed(context, '/received');
  }
}
