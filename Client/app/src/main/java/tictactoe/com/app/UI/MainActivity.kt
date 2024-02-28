package tictactoe.com.app.UI

import android.content.Intent
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
        println("activity main")
        val btnLogin = findViewById<Button>(R.id.btnLogin)



        btnLogin.setOnClickListener {
            val username = findViewById<EditText>(R.id.txtUsername).text.toString();
            val password = findViewById<EditText>(R.id.txtPassword).text.toString()
            clientManager.startConnect(username, password)

        }
    }



    override fun update(p0: Observable?, p1: Any?) {
        Log.d("MainActivity", p1.toString())

        var result = JSONObject(p1.toString())
        var action = result.get("action")

        when (action) {
            Action.LOGIN -> {
                if (result.get("status") == "success") {
                    val username: String = result.get("username").toString()

                    runOnUiThread {
                        Toast.makeText(
                            this,
                            "Socket Connection Successful! " + result.get("username"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    val intent = Intent(this, Hall::class.java)

                    intent.putExtra("username", username)
                    startActivity(intent);
                    clientManager.deleteObserver(this)
                }

            }

            else -> println("Invalid value")
        }


    }
}