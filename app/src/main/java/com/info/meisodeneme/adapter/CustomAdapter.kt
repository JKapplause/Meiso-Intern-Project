
 package com.info.meisodeneme.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.info.meisodeneme.databinding.HorizontalLayoutBinding
import com.info.meisodeneme.model.DataModel
import com.info.meisodeneme.view.HomeFragmentDirections


 class CustomAdapter(val cardlist :List<DataModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(), ClickListener {
     class ViewHolder (var binding : HorizontalLayoutBinding ) : RecyclerView.ViewHolder(binding.root){
         fun bind(item: DataModel) {
             binding.selectH = item

         }
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
         val listItemViewBinding = HorizontalLayoutBinding.inflate(inflater,parent,false)
         return ViewHolder(listItemViewBinding)
    }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(cardlist[position])
         holder.binding.clickH = this

     }

     override fun getItemCount(): Int {
         return cardlist.size
     }

     override fun onCardClick(v: View) {

         val action = HomeFragmentDirections.actionHomeFragmentToMediaDetailFragment()
         Navigation.findNavController(v).navigate(action)
     }


 }