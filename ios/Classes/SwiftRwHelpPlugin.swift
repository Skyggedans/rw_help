import Flutter
import UIKit

public class SwiftRwSpeechRecognizerPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "rw_speech_recognizer", binaryMessenger: registrar.messenger())
    let instance = SwiftRwSpeechRecognizerPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
