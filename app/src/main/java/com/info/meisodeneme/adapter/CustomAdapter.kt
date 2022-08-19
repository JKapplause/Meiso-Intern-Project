package com.info.meisodeneme.adapter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.info.meisodeneme.databinding.HorizontalLayoutBinding
import com.info.meisodeneme.model.DataModel
import com.info.meisodeneme.util.downloadFromUrl
import com.info.meisodeneme.util.placeholderProgressBar


class CustomAdapter(val cardlist:List<DataModel>, val onClick:(DataModel)->Unit) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    inner class ViewHolder (var binding : HorizontalLayoutBinding ) : RecyclerView.ViewHolder(binding.root){
         fun bind(item : DataModel) {
             binding.selectH = item
             binding.horizontalCardView.setOnClickListener {
                 onClick.invoke(item)
             }
         }
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
         val listItemViewBinding = HorizontalLayoutBinding.inflate(inflater,parent,false)
         return ViewHolder(listItemViewBinding)

    }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(cardlist[position])
         holder.binding.horizontalImage.downloadFromUrl(cardlist[position].image, placeholderProgressBar(holder.itemView.context))

     }

     override fun getItemCount(): Int {
         return cardlist.size
     }
 }