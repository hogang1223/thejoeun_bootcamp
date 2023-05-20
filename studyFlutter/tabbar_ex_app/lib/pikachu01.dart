import 'package:flutter/material.dart';

class Pikachu01 extends StatelessWidget {
  const Pikachu01({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.pink[100],
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: CircleAvatar(
                backgroundImage: AssetImage('images/pikachu1.png'),
                radius: 50.0,
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: CircleAvatar(
                backgroundImage: AssetImage('images/pikachu2.png'),
                radius: 50.0,
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: CircleAvatar(
                backgroundImage: AssetImage('images/pikachu3.png'),
                radius: 50.0,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
