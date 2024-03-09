package tictactoe.com.app.Core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tictactoe.com.app.R


class MyAdapter(var context: Context, var users: List<User>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameView.setText(users[position].username)
        holder.emailView.setText(users[position].ratting)
        holder.imageView.setImageResource(users[position].image)
    }

    public fun setAction(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return users.size
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var nameView: TextView
        var emailView: TextView
        var ivSolo: ImageView

        init {
            imageView = itemView.findViewById<ImageView>(R.id.imageview)
            nameView = itemView.findViewById<TextView>(R.id.name)
            emailView = itemView.findViewById<TextView>(R.id.email)

            ivSolo = itemView.findViewById(R.id.ivSolo)
            ivSolo.setOnClickListener {
                onItemClickListener?.onItemClick(users[adapterPosition])
            }
        }
    }

}