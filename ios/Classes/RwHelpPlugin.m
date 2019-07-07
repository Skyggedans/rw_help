#import "RwSpeechRecognizerPlugin.h"
#import <rw_speech_recognizer/rw_speech_recognizer-Swift.h>

@implementation RwSpeechRecognizerPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftRwSpeechRecognizerPlugin registerWithRegistrar:registrar];
}
@end
