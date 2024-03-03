package tictactoe.com.app.Core

import android.app.Application
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject
import tictactoe.com.app.networking.MyWebsocket
import java.io.Serializable
import java.util.Observable
import java.util.Observer

class ClientManager : Observable(), Serializable {
    lateinit var webSocket: WebSocket
    public lateinit var username: String
    var host: String = "192.168.1.7"
    override fun notifyObservers(arg: Any?) {
        super.setChanged()
        super.notifyObservers(arg)
    }


    fun logout(txt: String) {
        webSocket.send(txt)
    }


    fun startConnect(name: String, pass: String) {
        this.username = name
        print("nameSocket: $name  /   $pass")
        var url: String = "ws://$host:8000/login/$name/$pass"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        val webSocketListener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                println("WebSocket opened")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                val jsonObject: JSONObject = JSONObject(text)
                Constant.clientManager.notifyObservers(jsonObject)
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                println("WebSocket closing: $reason")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                println("WebSocket failure: ${t.message}")
            }
        }

        webSocket = client.newWebSocket(request, webSocketListener)

        runBlocking {  }
    }

}