import 'package:flutter/material.dart';

class Received extends StatelessWidget {
  const Received({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Received Mail'),
        backgroundColor: Colors.orange,
      ),
      body: Padding(
        padding: const EdgeInsets.only(top: 50.0),
        child: Column(
          children: [
            Text('mail from a'),
            Text('mail from b'),
            Text('mail from c'),
          ],
        ),
      ),
    );
  }
}
