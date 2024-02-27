package tictactoe.com.app.UI

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import tictactoe.com.app.Core.Action
import tictactoe.com.app.Core.ClientManager
import tictactoe.com.app.R
import java.util.Observable
import java.util.Observer


class MainActivity : AppCompatActivity(), Observer {
    val clientManager = ClientManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnLogin = findViewById<Button>(R.id.btnLogin)

        var name = findViewById<EditText>(R.id.txtUsername).text.toString();

        btnLogin.setOnClickListener {
            clientManager.startConnect(name)
        }
    }



    override fun update(p0: Observable?, p1: Any?) {
        Log.d("MainActivity", p1.toString())

        var result = JSONObject(p1.toString())
        var action = result.get("action")

        when (action) {
            Action.LOGIN -> {

                runOnUiThread {
                    Toast.makeText(
                        this,
                        "Socket Connection Successful!  " + result.get("username"),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                clientManager.deleteObserver(this)
            }

            else -> println("Invalid value")
        }


    }
}