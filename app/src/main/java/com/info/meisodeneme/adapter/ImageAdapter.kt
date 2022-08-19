package com.info.meisodeneme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.info.meisodeneme.databinding.ImageLayoutBinding
import com.info.meisodeneme.model.DataModel
import com.info.meisodeneme.util.downloadFromUrl
import com.info.meisodeneme.util.placeholderProgressBar


class ImageAdapter(var dataList: List<DataModel>,val onClick :(DataModel)->Unit) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

   inner class ViewHolder(var binding : ImageLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: DataModel) {
            binding.selectV = item
            binding.LinearLayoutVertical.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemViewBinding = ImageLayoutBinding.inflate(inflater,parent,false)
        return ViewHolder(listItemViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.binding.image.downloadFromUrl(dataList[position].image, placeholderProgressBar(holder.itemView.context))
    }

    override fun getItemCount() = dataList.size

}
