package tictactoe.com.app.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import tictactoe.com.app.Core.ClientManager
import tictactoe.com.app.R
import java.util.Observable
import java.util.Observer
import android.view.View;
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class MainActivity : AppCompatActivity(), Observer {
    val clientManager = ClientManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            clientManager.startConnect()
        }
    }



    override fun update(p0: Observable?, p1: Any?) {
        Log.d("MainActivity", p1.toString())
        findViewById<EditText>(R.id.editText).setText(p1.toString())
        print("Main")
    }
}