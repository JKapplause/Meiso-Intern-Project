package com.info.meisodeneme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.info.meisodeneme.model.DataModel
import com.info.meisodeneme.R
import com.info.meisodeneme.view.HomeFragmentDirections

class ImageAdapter(var context : Context) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    var dataList = emptyList<DataModel>()

    internal fun setDataList(dataList : List<DataModel>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var image : ImageView
        var title : TextView
        var desc : TextView

        init {
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.desc)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.image_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = dataList[position]

        holder.title.text = data.title
        holder.desc.text = data.desc

        holder.image.setImageResource(data.image)

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToMediaDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = dataList.size




}
