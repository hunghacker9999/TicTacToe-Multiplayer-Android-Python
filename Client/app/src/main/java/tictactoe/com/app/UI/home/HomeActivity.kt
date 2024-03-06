package tictactoe.com.app.UI.home

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tictactoe.com.app.R
import tictactoe.com.app.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    lateinit var fabButton: FloatingActionButton


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
                R.id.friends -> replaceFragment(FriendFragment())
                R.id.setting -> replaceFragment(SettingFragment())
            }
            true
        }
        fabButton = findViewById(R.id.fabButton)
        fabButton.setOnClickListener {
            replaceFragment(PlayFragment())

        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
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

}