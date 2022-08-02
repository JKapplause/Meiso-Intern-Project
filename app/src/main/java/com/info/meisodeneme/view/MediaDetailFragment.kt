package com.info.meisodeneme.view

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.info.meisodeneme.model.MediaViewModel
import com.info.meisodeneme.R
import com.info.meisodeneme.model.VerticalMediaViewModel
import kotlinx.android.synthetic.main.fragment_media_detail.*
import java.sql.Time
import java.text.DateFormat
import java.util.*

class MediaDetailFragment : Fragment() {

    private lateinit var viewModel : MediaViewModel
    private lateinit var viewModelV: VerticalMediaViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_media_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Instant DateTime

        back_media.setOnClickListener {
            val action = MediaDetailFragmentDirections.actionMediaDetailFragmentToHomeFragment()
            Navigation.findNavController(it).navigate(action)
        }


        val calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance().format(calendar.time)

        val textView : TextView = view.findViewById(R.id.editTextDate)
        textView.setText(currentDate)

        //Media Player

        val mediaPlayer = MediaPlayer.create(getActivity()?.getApplicationContext(),R.raw.music)
        play_button.setOnClickListener {
            if(!mediaPlayer.isPlaying) {
                mediaPlayer.start()

                play_button.setImageResource(R.drawable.ic_pause_button)
            }else{
                mediaPlayer.pause()
                play_button.setImageResource(R.drawable.ic_play_button)
            }
        }



        viewModelV = ViewModelProvider(this).get(VerticalMediaViewModel::class.java)
        viewModelV.getDataVertical()
        observeLiveData2()

        //MediaViewModel / horiz / vertic
        viewModel = ViewModelProvider(this).get(MediaViewModel::class.java)
        viewModel.getData()




        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.horizontalLiveData.observe(viewLifecycleOwner) {
            it?.let {
                media_text.text = it.horizontal_titlee
            }

        }
    }
    private fun observeLiveData2() {
        viewModelV.verticalLiveData.observe(viewLifecycleOwner) { vertical->
            vertical?.let {
                media_text.text = it.title
            }
        }
    }

    }