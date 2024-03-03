package tictactoe.com.app.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import tictactoe.com.app.Core.Constant
import tictactoe.com.app.Core.ClientManager
import tictactoe.com.app.Core.User
import tictactoe.com.app.R
import java.io.Serializable
import java.util.Observable
import java.util.Observer


class MainActivity : AppCompatActivity(), Observer, Serializable {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        println("activity main")
        val btnLogin = findViewById<Button>(R.id.btnLogin)


        Constant.clientManager.addObserver(this)
        btnLogin.setOnClickListener {
            val username = findViewById<EditText>(R.id.txtUsername).text.toString();
            val password = findViewById<EditText>(R.id.txtPassword).text.toString()
            Constant.clientManager.startConnect(username, password)

        }
    }



    override fun update(p0: Observable?, p1: Any?) {
        Log.d("MainActivity", p1.toString())

        val result = JSONObject(p1.toString())

        when (result.get("action")) {
            Constant.LOGIN -> {
                if (result.get("status") == "success") {

                    val intent = Intent(this, Hall::class.java);
                    startActivity(intent);

                }

            }

            else -> println("Invalid value")
        }


    }
}