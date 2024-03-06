package tictactoe.com.app.UI.component

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tictactoe.com.app.Core.Item
import tictactoe.com.app.Core.MyAdapter
import tictactoe.com.app.R


class MyDialogFragment : BottomSheetDialogFragment() {
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.bottomsheetlayout, container, false)
        rootView.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        var recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerview);

        val items: MutableList<Item> = ArrayList<Item>()

        items.add(Item("John wick","john.wick@email.com",R.drawable.d));
        items.add(Item("Robert j", "robert.j@email.com", R.drawable.d))
        items.add(Item("James Gunn", "james.gunn@email.com", R.drawable.e))
        items.add(Item("Ricky tales", "rickey.tales@email.com", R.drawable.d))
        items.add(Item("Micky mose", "mickey.mouse@email.com", R.drawable.e))
        items.add(Item("Pick War", "pick.war@email.com", R.drawable.e))
        items.add(Item("John wick","john.wick@email.com",R.drawable.d));
        items.add(Item("Robert j", "robert.j@email.com", R.drawable.d))
        items.add(Item("James Gunn", "james.gunn@email.com", R.drawable.e))
        items.add(Item("Ricky tales", "rickey.tales@email.com", R.drawable.d))
        items.add(Item("Micky mose", "mickey.mouse@email.com", R.drawable.e))
        items.add(Item("Pick War", "pick.war@email.com", R.drawable.e))
        items.add(Item("John wick","john.wick@email.com",R.drawable.d));
        items.add(Item("Robert j", "robert.j@email.com", R.drawable.d))
        items.add(Item("James Gunn", "james.gunn@email.com", R.drawable.e))
        items.add(Item("Ricky tales", "rickey.tales@email.com", R.drawable.d))
        items.add(Item("Micky mose", "mickey.mouse@email.com", R.drawable.e))
        items.add(Item("Pick War", "pick.war@email.com", R.drawable.e))

        recyclerView.layoutManager = LinearLayoutManager(inflater.context)

        recyclerView.adapter = MyAdapter(inflater.context, items)



//        val videoLayout = rootView.findViewById<LinearLayout>(R.id.layoutVideo)
//        val shortsLayout = rootView.findViewById<LinearLayout>(R.id.layoutShorts)
//        val liveLayout = rootView.findViewById<LinearLayout>(R.id.layoutLive)
//                    //        val cancelButton = rootView.findViewById<ImageView>(R.id.cancelButton)
//        videoLayout.setOnClickListener {
//            dismiss()
//            println("A")
//        }
//        shortsLayout.setOnClickListener {
//            dismiss()
//            println("A")
//        }
//        liveLayout.setOnClickListener {
//            dismiss()
//        }
//        cancelButton.setOnClickListener { dismiss() }


        return rootView
    }

}