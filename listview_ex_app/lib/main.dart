import 'package:flutter/material.dart';
import 'package:listview_ex_app/countryItem.dart';
import 'package:listview_ex_app/firstPage.dart';
import 'package:listview_ex_app/secondPage.dart';

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
  List<Country> countryList = [];
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    controller = TabController(length: 2, vsync: this);

    countryList.add(Country(imagePath: 'america.png', countryName: '미국'));
    countryList.add(Country(imagePath: 'austria.png', countryName: '오스트리아'));
    countryList.add(Country(imagePath: 'belgium.png', countryName: '벨기에'));
    countryList.add(Country(imagePath: 'canada.png', countryName: '캐나다'));
    countryList.add(Country(imagePath: 'china.png', countryName: '중국'));
    countryList.add(Country(imagePath: 'estonia.png', countryName: '에스토니아'));
    countryList.add(Country(imagePath: 'france.png', countryName: '프랑스'));
    countryList.add(Country(imagePath: 'germany.png', countryName: '독일'));
    countryList.add(Country(imagePath: 'greece.png', countryName: '그리스'));
    countryList.add(Country(imagePath: 'hungary.png', countryName: '헝가리'));
    countryList.add(Country(imagePath: 'italy.png', countryName: '이탈리아'));
    countryList.add(Country(imagePath: 'korea.png', countryName: '대한민국'));
    countryList.add(Country(imagePath: 'latvia.png', countryName: '라트비아'));
    countryList.add(Country(imagePath: 'lithuania.png', countryName: '리투아니아'));
    countryList.add(Country(imagePath: 'luxemburg.png', countryName: '룩셈부르크'));
    countryList.add(Country(imagePath: 'netherland.png', countryName: '네덜란드'));
    countryList.add(Country(imagePath: 'romania.png', countryName: '루마니아'));
    countryList.add(Country(imagePath: 'turkey.png', countryName: '터키'));
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
      appBar: AppBar(
        title: Text('ListView Test'),
      ),
      body: TabBarView(
        children: [
          FirstPage(
            list: countryList,
          ),
          SecondPage(
            list: countryList,
          ),
        ],
        controller: controller,
      ),
      bottomNavigationBar: TabBar(
        labelColor: Colors.blueAccent,
        tabs: [
          Tab(
            icon: Icon(
              Icons.looks_one,
              color: Colors.blue,
            ),
            text: 'Page #1',
          ),
          Tab(
            icon: Icon(
              Icons.looks_two,
              color: Colors.red,
            ),
            text: 'Page #2',
          ),
        ],
        controller: controller,
      ),
    );
  }
}
