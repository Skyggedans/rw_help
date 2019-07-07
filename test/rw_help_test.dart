import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:rw_help/rw_help.dart';

void main() {
  const MethodChannel channel = MethodChannel('rw_speech_recognizer');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
  });
}
