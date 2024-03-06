package tictactoe.com.app.UI.home

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import tictactoe.com.app.R
import tictactoe.com.app.UI.component.MyDialogFragment
import java.util.Observable
import java.util.Observer

class PlayFragment : Fragment(), Observer {
    lateinit var btnPlay: Button
    lateinit var btnPlaWithFriend: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi(view)
        initListener()
    }

    private fun initListener() {
        btnPlaWithFriend.setOnClickListener {

            val dialogFragment = MyDialogFragment()

            dialogFragment.show(parentFragmentManager, "jee")

        }
    }

    private fun initUi(view: View) {
        btnPlaWithFriend = view.findViewById(R.id.btnPlayWithFriend)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_play, container, false)
    }

    override fun update(p0: Observable?, p1: Any?) {

    }

    private fun showBottomDialog() {

    }

}