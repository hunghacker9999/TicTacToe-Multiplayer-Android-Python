package tictactoe.com.app.Core

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tictactoe.com.app.R


class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView: ImageView
    var nameView: TextView
    var emailView: TextView

    init {
        imageView = itemView.findViewById<ImageView>(R.id.imageview)
        nameView = itemView.findViewById<TextView>(R.id.name)
        emailView = itemView.findViewById<TextView>(R.id.email)
    }
}