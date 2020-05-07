import 'dart:async';

import 'package:flutter/services.dart';

class Oppwasdkplugin {
  static const MethodChannel _channel = const MethodChannel('oppwasdkplugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> get payment async {
    final String res = await _channel.invokeMethod('payment');
    return res;
  }

  static Future<String> validateCard(String number) async {
    var params = <String, dynamic>{"number": number};
    final String res = await _channel.invokeMethod('validate', params);
    return res;
  }
}
