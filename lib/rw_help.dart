import 'dart:async';

import 'package:flutter/services.dart';

class RwHelp {
  static MethodChannel _channel =
      MethodChannel('com.rockwellits.rw_plugins/rw_help');

  static Future<void> setCommands(List<String> commands) async {
    await _channel.invokeMethod('setCommands', {'commands': commands});
  }
}
