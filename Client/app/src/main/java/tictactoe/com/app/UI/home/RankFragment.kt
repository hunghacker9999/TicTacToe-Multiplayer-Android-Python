package tictactoe.com.app.UI.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject
import tictactoe.com.app.Core.Constant
import tictactoe.com.app.Core.MyAdapter
import tictactoe.com.app.Core.OnItemClickListener
import tictactoe.com.app.Core.User
import tictactoe.com.app.R
import java.util.Observable
import java.util.Observer

class RankFragment : Fragment(), Observer {
    private lateinit var rootView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.bottomsheetlayout, container, false)
        rootView.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        var recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerview);

        val users: MutableList<User> = ArrayList<User>()

        users.add(User("garen", "Garen","4123",R.drawable.d));
        users.add(User("hung", "Hung hansome","2222", R.drawable.e))



        recyclerView.layoutManager = LinearLayoutManager(inflater.context)

        val adapter = MyAdapter(inflater.context, users)
        adapter.setAction(object : OnItemClickListener {
            override fun onItemClick(user: User) {
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("action", Constant.SOLO)
                    jsonObject.put("to", user.username)
                    jsonObject.put("message", "want to solo with you")

                    Constant.clientManager.webSocket.send(jsonObject.toString())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

        })

        recyclerView.adapter = adapter

        return rootView
    }

    override fun update(p0: Observable?, p1: Any?) {
    }
}