package com.rockwellits.rw_plugins.rw_help

import android.app.Activity
import android.app.Application
import android.content.Intent
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar


class RwHelpPlugin(val activity: Activity, val channel: MethodChannel) : MethodCallHandler {
    private val ACTION_UPDATE_HELP = "com.realwear.wearhf.intent.action.UPDATE_HELP_COMMANDS"
    private val EXTRA_TEXT = "com.realwear.wearhf.intent.extra.HELP_COMMANDS"
    private val EXTRA_SOURCE = "com.realwear.wearhf.intent.extra.SOURCE_PACKAGE"

    private var commands: ArrayList<String>? = null

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "com.rockwellits.rw_plugins/rw_help")

            channel.setMethodCallHandler(RwHelpPlugin(registrar.activity(), channel))
        }
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
