package tictactoe.com.app.UI.home

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject
import tictactoe.com.app.Core.Constant
import tictactoe.com.app.R
import tictactoe.com.app.databinding.ActivityHomeBinding
import java.util.Observable
import java.util.Observer

class HomeActivity : AppCompatActivity(), Observer {
    lateinit var binding: ActivityHomeBinding
    init {
        Constant.clientManager.addObserver(this)
    }
//    lateinit var fabButton: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(PlayFragment())

        binding.bottomNavigationView.setBackground(null)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.rank -> replaceFragment(RankFragment())
                R.id.store -> replaceFragment(StoreFragment())
                R.id.play -> replaceFragment(PlayFragment())
                R.id.friends -> replaceFragment(FriendFragment())
                R.id.setting -> replaceFragment(SettingFragment())
            }
            true
        }
//        fabButton = findViewById(R.id.fabButton)
//        fabButton.setOnClickListener {
//            replaceFragment(PlayFragment())
//
//        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    fun showDialogSolo(context: Context, result: JSONObject) {
        val alertDialogBuilder = AlertDialog.Builder(context)

        // Set dialog title and message
        alertDialogBuilder.setTitle("Challenge")
        alertDialogBuilder.setMessage("${result.get("from")} + ${result.get("message")}")

        // Set positive button with click listener
        alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
            // Handle button click here
            // You can perform any action on button click
        }

        // Set negative button with click listener
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            // Handle button click here
            // You can perform any action on button click
        }


        // Create and show the dialog
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun showBottomDialog() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheetlayout)
//        val videoLayout = dialog.findViewById<LinearLayout>(R.id.layoutVideo)
//        val shortsLayout = dialog.findViewById<LinearLayout>(R.id.layoutShorts)
//        val liveLayout = dialog.findViewBy val videoLayout = dialog.findViewById<LinearLayout>(R.id.layoutVideo)
//        val shortsLayout = dialog.findViewById<LinearLayout>(R.id.layoutShorts)
//        val liveLayout = dialog.findViewById<LinearLayout>(R.id.layoutLive)
//        val cancelButton = dialog.findViewById<ImageView>(R.id.cancelButton)
//        videoLayout.setOnClickListener {
//            dialog.dismiss()
//            Toast.makeText(this@HomeActivity, "Upload a Video is clicked", Toast.LENGTH_SHORT)
//                .show()
//        }
//        shortsLayout.setOnClickListener {
//            dialog.dismiss()
//            Toast.makeText(this@HomeActivity, "Create a short is Clicked", Toast.LENGTH_SHORT)
//                .show()
//        }
//        liveLayout.setOnClickListener {
//            dialog.dismiss()
//            Toast.makeText(this@HomeActivity, "Go live is Clicked", Toast.LENGTH_SHORT).show()
//        }
//        cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }

    override fun update(p0: Observable?, p1: Any?) {
        Log.d("Home Activity", p1.toString())

        val result = JSONObject(p1.toString())

        when (result.get("action")) {
            Constant.SOLO -> {
                runOnUiThread{
                    showDialogSolo(this, result)
                }

            }

            else -> println("Invalid value")
        }
    }

}