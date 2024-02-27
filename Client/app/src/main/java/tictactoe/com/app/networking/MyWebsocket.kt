package tictactoe.com.app.networking

import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.runBlocking
import okhttp3.*
import tictactoe.com.app.Core.ClientManager
import java.net.NetworkInterface


class MyWebsocket(private var clientManager: ClientManager) {

    var host: String = "192.168.1.7"

    fun getIPAddress(): String {
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

    fun start(name: String) {
        val ipAddress = getIPAddress().toString()

        Log.d("IP", ipAddress)
        val request = Request.Builder().url("ws://$host:8000/login/$name").build()
        val client = OkHttpClient()

        val webSocketListener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                println("WebSocket opened")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                println("Received message: $text")
                clientManager.notifyObservers(text)
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                println("WebSocket closing: $reason")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                println("WebSocket failure: ${t.message}")
            }
        }

        val webSocket = client.newWebSocket(request, webSocketListener)

        runBlocking {  }
    }
}
