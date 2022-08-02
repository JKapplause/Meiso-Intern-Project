package com.info.meisodeneme.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.info.meisodeneme.R
import com.info.meisodeneme.model.DataModel
import com.info.meisodeneme.model.DataObject
import com.info.meisodeneme.view.HomeFragmentDirections
import kotlinx.android.synthetic.main.horizontal_layout.view.*

class CustomAdapter(private val cardlist :List<DataObject>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

        val horizontal_title : TextView = itemView.findViewById(R.id.horizontal_title)
        val horizontal_desc : TextView = itemView.findViewById(R.id.horizontal_desc)
        val horizontal_image : ImageView = itemView.findViewById(R.id.horizontal_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_layout, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datah = cardlist[position]
        holder.horizontal_title.text = datah.horizontal_titlee
        holder.horizontal_desc.text = datah.horizontal_descc
        holder.horizontal_image.setImageResource(datah.horizontal_imagee)

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToMediaDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
            return cardlist.size
    }


}