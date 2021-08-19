import 'package:flutter/material.dart';
import 'package:listview_ex_app/countryItem.dart';

class SecondPage extends StatefulWidget {
  final List<Country> list;
  const SecondPage({Key? key, required this.list}) : super(key: key);

  @override
  _SecondPageState createState() => _SecondPageState();
}

class _SecondPageState extends State<SecondPage> {
  final nameController = TextEditingController();
  var _imagePath;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            children: [
              TextField(
                controller: nameController,
                decoration: InputDecoration(labelText: '나라 이름을 입력하세요'),
                keyboardType: TextInputType.text,
                maxLines: 1,
              ),
              Container(
                height: 100,
                child: ListView(
                  scrollDirection: Axis.horizontal,
                  children: [
                    GestureDetector(
                      child: Image.asset(
                        'images/korea.png',
                        width: 80,
                      ),
                      onTap: () {
                        _imagePath = 'korea.png';
                      },
                    ),
                    GestureDetector(
                      child: Image.asset(
                        'images/america.png',
                        width: 80,
                      ),
                      onTap: () {
                        _imagePath = 'america.png';
                      },
                    ),
                    GestureDetector(
                      child: Image.asset(
                        'images/austria.png',
                        width: 80,
                      ),
                      onTap: () {
                        _imagePath = 'austria.png';
                      },
                    ),
                    GestureDetector(
                      child: Image.asset(
                        'images/belgium.png',
                        width: 80,
                      ),
                      onTap: () {
                        _imagePath = 'belgium.png';
                      },
                    ),
                    GestureDetector(
                      child: Image.asset(
                        'images/china.png',
                        width: 80,
                      ),
                      onTap: () {
                        _imagePath = 'china.png';
                      },
                    ),
                    GestureDetector(
                      child: Image.asset(
                        'images/estonia.png',
                        width: 80,
                      ),
                      onTap: () {
                        _imagePath = 'estonia.png';
                      },
                    ),
                    GestureDetector(
                      child: Image.asset(
                        'images/france.png',
                        width: 80,
                      ),
                      onTap: () {
                        _imagePath = 'france.png';
                      },
                    ),
                    GestureDetector(
                      child: Image.asset(
                        'images/germany.png',
                        width: 80,
                      ),
                      onTap: () {
                        _imagePath = 'germany.png';
                      },
                    ),
                    GestureDetector(
                      child: Image.asset(
                        'images/greece.png',
                        width: 80,
                      ),
                      onTap: () {
                        _imagePath = 'greece.png';
                      },
                    ),
                  ],
                ),
              ),
              ElevatedButton(
                style: ButtonStyle(
                  backgroundColor: MaterialStateProperty.all(Colors.amber),
                ),
                onPressed: () {
                  var country = Country(
                      imagePath: _imagePath, countryName: nameController.text);
                  AlertDialog dialog = AlertDialog(
                    title: Text('국가 추가하기'),
                    content: Text(
                      '이 국가는 ${country.countryName}입니다.\n'
                      '이 국가를 추가하시겠습니까?',
                      style: TextStyle(fontSize: 17.0),
                    ),
                    actions: [
                      ElevatedButton(
                        style: ButtonStyle(
                            backgroundColor:
                                MaterialStateProperty.all(Colors.blue)),
                        onPressed: () {
                          widget.list.add(country);
                          Navigator.of(context).pop();
                        },
                        child: Text('yes'),
                      ),
                      ElevatedButton(
                        style: ButtonStyle(
                            backgroundColor:
                                MaterialStateProperty.all(Colors.red)),
                        onPressed: () {
                          Navigator.of(context).pop();
                        },
                        child: Text('no'),
                      ),
                    ],
                  );
                  showDialog(
                      context: context,
                      builder: (BuildContext context) {
                        return dialog;
                      });
                },
                child: Text('국가 추가하기'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
