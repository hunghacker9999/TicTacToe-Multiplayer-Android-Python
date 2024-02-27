package tictactoe.com.app

import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

}
var host: String = "192.168.1.7"
fun main() {
    val request = Request.Builder().url("ws://$host:8000/login/12300").build()
    val client = OkHttpClient()

    val webSocketListener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            println("WebSocket opened")
            // Perform any necessary actions when the WebSocket connection is opened
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            println("Received message: $text")
            // Handle the received message
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            println("WebSocket closing: $reason")
            // Perform any necessary actions when the WebSocket connection is closing
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            println("WebSocket failure: ${t.message}")
            // Handle any errors that occur during the WebSocket connection
        }
    }

    val webSocket = client.newWebSocket(request, webSocketListener)

    // To send a message, you can use webSocket.send("Your message")

    // Keep the program running to continue receiving messages
    runBlocking {  }
}