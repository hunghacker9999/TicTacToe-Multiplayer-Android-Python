package tictactoe.com.app.Core

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tictactoe.com.app.R


class MyAdapter(var context: Context, var items: List<Item>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameView.setText(items[position].name)
        holder.emailView.setText(items[position].email)
        holder.imageView.setImageResource(items[position].image)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}