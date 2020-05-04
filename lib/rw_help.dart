import 'dart:async';

import 'package:flutter/services.dart';

class RwHelp {
  static MethodChannel _channel =
      MethodChannel('com.rockwellits.rw_plugins/rw_help');

  /// Sets the commands for a help screen.
  ///
  /// Sets the [commands] to be displayed while invoking a help screen.
  static Future<void> setCommands(List<String> commands) async {
    await _channel.invokeMethod('setCommands', {'commands': commands});
  }
}
