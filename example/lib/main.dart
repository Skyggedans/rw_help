import 'package:flutter/material.dart';

import 'package:rw_help/rw_help.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();

    RwHelp.setCommands(<String>[
      'Full Boar',
      'California Sunshine',
      'Deadicated',
    ]);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('RealWear HMT-1(Z1) help example app'),
        ),
        body: Builder(builder: (BuildContext context) {
          return Center(
            child: const Text('Say "Show help"'),
          );
        }),
      ),
    );
  }
}
