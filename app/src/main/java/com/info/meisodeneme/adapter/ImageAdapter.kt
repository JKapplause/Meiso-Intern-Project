package com.info.meisodeneme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.info.meisodeneme.R
import com.info.meisodeneme.databinding.HorizontalLayoutBinding
import com.info.meisodeneme.databinding.ImageLayoutBinding
import com.info.meisodeneme.model.DataModel

import com.info.meisodeneme.view.HomeFragmentDirections

class ImageAdapter(var dataList: List<DataModel>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>(), ClickListener {

    class ViewHolder(var binding : ImageLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: DataModel) {
            binding.selectV = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemViewBinding = ImageLayoutBinding.inflate(inflater,parent,false)
        return ViewHolder(listItemViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.binding.clickV = this

    }

    override fun getItemCount() = dataList.size
    override fun onCardClick(v: View) {
        val action = HomeFragmentDirections.actionHomeFragmentToMediaDetailFragment()
        Navigation.findNavController(v).navigate(action)
    }


}
