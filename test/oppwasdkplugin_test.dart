import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:oppwasdkplugin/oppwasdkplugin.dart';

void main() {
  const MethodChannel channel = MethodChannel('oppwasdkplugin');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await Oppwasdkplugin.platformVersion, '42');
  });
}
