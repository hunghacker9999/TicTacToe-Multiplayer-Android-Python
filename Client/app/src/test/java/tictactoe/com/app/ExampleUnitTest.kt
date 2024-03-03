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
    object DataManager {
        var sharedData: String = "Hello"
    }
}
fun main() {
    var myApp: MyApp = MyApp()

    myApp.setvalue()
    print(ExampleUnitTest.DataManager.sharedData)
}