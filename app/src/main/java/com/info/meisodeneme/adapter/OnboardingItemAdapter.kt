package com.info.meisodeneme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.info.meisodeneme.model.OnboardingItem
import com.info.meisodeneme.databinding.OnboardingItemContainerBinding

class OnboardingItemAdapter(private val onboardingItem: List<OnboardingItem>) :
    RecyclerView.Adapter<OnboardingItemAdapter.OnboardingItemViewHolder>()
{
    inner class OnboardingItemViewHolder(var binding: OnboardingItemContainerBinding) : RecyclerView.ViewHolder(binding.root) {

        private val imageOnboarding = binding.imageBoard
        private val textTitle = binding.textTitle
        private val textDescription = binding.textDescription

        fun bind( onboardingItem : OnboardingItem) {
            imageOnboarding.setImageResource(onboardingItem.onboardingImage)
            textTitle.text = onboardingItem.title
            textDescription.text = onboardingItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemViewBinding = OnboardingItemContainerBinding.inflate(inflater,parent,false)
        return OnboardingItemViewHolder(listItemViewBinding)
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onboardingItem[position])
    }

    override fun getItemCount(): Int {
        return onboardingItem.size
    }
}