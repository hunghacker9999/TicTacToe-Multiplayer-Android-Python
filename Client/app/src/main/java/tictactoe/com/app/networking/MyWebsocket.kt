package tictactoe.com.app.networking

import android.R.attr
import android.app.Application
import android.util.Log
import kotlinx.coroutines.runBlocking
import okhttp3.*
import org.json.JSONObject
import tictactoe.com.app.Core.ClientManager
import java.io.Serializable
import java.net.NetworkInterface
import java.util.Observable


class MyWebsocket(private var clientManager: ClientManager) : Application() {

    var host: String = "192.168.1.7"
    lateinit var webSocket: WebSocket

    fun getIPAddress(): String? {
        val interfaces = NetworkInterface.getNetworkInterfaces()
        while (interfaces.hasMoreElements()) {
            val networkInterface = interfaces.nextElement()
            val addresses = networkInterface.inetAddresses
            while (addresses.hasMoreElements()) {
                val address = addresses.nextElement()
                if (!address.isLoopbackAddress && address.isSiteLocalAddress) {
                    return address.hostAddress
                }
            }
        }
        return ""
    }




}
