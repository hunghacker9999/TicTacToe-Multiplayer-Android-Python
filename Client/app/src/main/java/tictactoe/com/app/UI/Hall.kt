package tictactoe.com.app.UI

import android.R.attr.name
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONException
import org.json.JSONObject
import tictactoe.com.app.Core.Constant
import tictactoe.com.app.Core.User
import tictactoe.com.app.R
import java.util.Observable
import java.util.Observer


class Hall : AppCompatActivity(), Observer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hall)
        Constant.clientManager.addObserver(this)

        initUi()
        initActionListener()
        Log.d("Hall", "this is Hall")


        val jsonObject = JSONObject()
        jsonObject.put("action", Constant.MESSAGE)
        jsonObject.put("message", "Hello World")
        println(jsonObject.toString())
        Constant.clientManager.webSocket.send(jsonObject.toString())


    findViewById<TextView>(R.id.txtUsername).text = Constant.clientManager.username

        val btnBack = findViewById<Button>(R.id.btnBack)





    }

    private fun initActionListener() {
        btnChat.setOnClickListener {
            val jsonObject = JSONObject()
            try {
                jsonObject.put("action", Constant.MESSAGE)
                jsonObject.put("message", mess.text)

                Constant.clientManager.webSocket.send(jsonObject.toString())
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

    }

    private fun initUi() {
        edtOpp = findViewById(R.id.edtOpMess)
        mess = findViewById(R.id.edtMess)
        btnChat = findViewById(R.id.btnChat)
    }

    lateinit var edtOpp: EditText
    lateinit var mess: EditText
    lateinit var btnChat: Button

    override fun update(p0: Observable?, p1: Any?) {
        val result = JSONObject(p1.toString())
        when (result.get("action")) {
            Constant.MESSAGE -> {
                runOnUiThread {
                    edtOpp.setText(result.get("message").toString())
                }

            }
        }
    }
}