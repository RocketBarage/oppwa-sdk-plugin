package com.ittest.oppwasdkplugin

import androidx.annotation.NonNull;
import com.oppwa.mobile.connect.payment.card.CardPaymentParams
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** OppwasdkpluginPlugin */
public class OppwasdkpluginPlugin: FlutterPlugin, MethodCallHandler {

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    val channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "oppwasdkplugin")
    channel.setMethodCallHandler(OppwasdkpluginPlugin());
  }

  // This static function is optional and equivalent to onAttachedToEngine. It supports the old
  // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
  // plugin registration via this function while apps migrate to use the new Android APIs
  // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
  //
  // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
  // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
  // depending on the user's project. onAttachedToEngine or registerWith must both be defined
  // in the same class.
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "oppwasdkplugin")
      channel.setMethodCallHandler(OppwasdkpluginPlugin())
    }
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else if(call.method == "payment") {
      result.success("payment call");
    } else if (call.method == "validate") {
      val params: Map<String, Object> = call.arguments as Map<String, Object>
      result.success(if(validate(params["number"].toString())) "true" else "false" );
    } else {
      result.notImplemented()
    }
  }

  private fun paymentParams(checkoutId: String, brand: String, number: String, holder: String, expiryMonth: String, expiryYear: String, cvv: String) {
    val paymentParams = CardPaymentParams(checkoutId, brand, number, holder, expiryMonth, expiryYear, cvv);
    paymentParams.shopperResultUrl = "streamity://result"
  }

  private fun validate (number: String): Boolean {
    return CardPaymentParams.isNumberValid(number)
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
  }
}
