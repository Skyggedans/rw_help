package com.rockwellits.rw_plugins.rw_help

import android.app.Activity
import android.content.Intent
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar


class RwHelpPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {
    lateinit var activity: Activity
    private var commands: ArrayList<String>? = null

    companion object {
        const val CHANNEL = "com.rockwellits.rw_plugins/rw_help"
        const val ACTION_UPDATE_HELP = "com.realwear.wearhf.intent.action.UPDATE_HELP_COMMANDS"
        const val EXTRA_TEXT = "com.realwear.wearhf.intent.extra.HELP_COMMANDS"
        const val EXTRA_SOURCE = "com.realwear.wearhf.intent.extra.SOURCE_PACKAGE"

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), CHANNEL)
            val plugin = RwHelpPlugin()

            plugin.activity = registrar.activity()
            channel.setMethodCallHandler(plugin)
        }
    }

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        val channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), CHANNEL)

        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when {
            call.method == "setCommands" -> {
                call.argument<ArrayList<String>>("commands")?.let { setCommands(it) }
                result.success(null)
            }
            else -> result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        this.activity = binding.activity
    }

    override fun onDetachedFromActivity() {
    }

    override fun onDetachedFromActivityForConfigChanges() {
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    }

    private fun applyCommands() {
        if (commands != null) {
            val intent = Intent(ACTION_UPDATE_HELP)

            intent.putExtra(EXTRA_SOURCE, activity.packageName)
            intent.putStringArrayListExtra(EXTRA_TEXT, commands)
            activity.sendBroadcast(intent)
        }
    }

    private fun setCommands(commandsList: ArrayList<String>) {
        commands = commandsList
        applyCommands()
    }
}
