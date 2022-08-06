package com.info.meisodeneme.view

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs

import com.info.meisodeneme.R
import com.info.meisodeneme.databinding.FragmentMeditationDetailBinding
import com.info.meisodeneme.model.DataModel
import com.info.meisodeneme.model.HomeViewModel


import kotlinx.android.synthetic.main.fragment_meditation_detail.*
import kotlinx.android.synthetic.main.image_layout.*
import java.text.DateFormat
import java.util.*

class MeditationDetailFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var dataBinding: FragmentMeditationDetailBinding
    private val args : MeditationDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
    dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_meditation_detail,container,false)
    return dataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        back_media.setOnClickListener {
            val action =
                MeditationDetailFragmentDirections.actionMediaDetailFragmentToHomeFragment()
            Navigation.findNavController(it).navigate(action)
        }


        val calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance().format(calendar.time)

        val textView: TextView = view.findViewById(R.id.editTextDate)
        textView.setText(currentDate)

        //Media Player

        val mediaPlayer = MediaPlayer.create(getActivity()?.getApplicationContext(), R.raw.music)
        play_button.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()

                play_button.setImageResource(R.drawable.ic_pause_button)
            } else {
                mediaPlayer.pause()
                play_button.setImageResource(R.drawable.ic_play_button)
            }
        }

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getData()
        observeLiveData()



    }
   private fun observeLiveData() {
        viewModel.LiveData.observe(viewLifecycleOwner) {
            it?.let {
               dataBinding.selectItem = args.argDataModel
            }

        }

    }
            }










