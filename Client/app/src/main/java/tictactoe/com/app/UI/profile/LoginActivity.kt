package tictactoe.com.app.UI.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import org.json.JSONObject
import tictactoe.com.app.Core.Constant
import tictactoe.com.app.R
import tictactoe.com.app.UI.Hall
import tictactoe.com.app.UI.home.HomeActivity
import java.util.Observable
import java.util.Observer

class LoginActivity : AppCompatActivity(), Observer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        initNetworking()
        val intent = Intent(this, HomeActivity::class.java);
        startActivity(intent);

        initUi()
        initListener()
    }

    private fun initNetworking() {
        Constant.clientManager.addObserver(this)
    }

    private fun initListener() {
        btnLogin.setOnClickListener{
            val username = findViewById<EditText>(R.id.etUsername).text.toString();
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            Constant.clientManager.startConnect(username, password)


        }
    }

    private fun initUi() {
        btnLogin = findViewById(R.id.btnLogin)
    }

    lateinit var btnLogin: Button
    override fun update(p0: Observable?, p1: Any?) {
        Log.d("Home", p1.toString())

        val result = JSONObject(p1.toString())

        when (result.get("action")) {
            Constant.LOGIN -> {
                if (result.get("status") == "success") {

                    val intent = Intent(this, HomeActivity::class.java);
                    startActivity(intent);

                }

            }

            else -> println("Invalid value")
        }


    }
}