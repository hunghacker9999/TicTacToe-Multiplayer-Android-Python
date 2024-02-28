package tictactoe.com.app.UI

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tictactoe.com.app.R


class Hall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hall)

        println("hall")
        val intent = this.intent

        val username = intent.getStringExtra("username")
        println("username $username")

        findViewById<TextView>(R.id.txtUsername).text = username

        var btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

    }
}