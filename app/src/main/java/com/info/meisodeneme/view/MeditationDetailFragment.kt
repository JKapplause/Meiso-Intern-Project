package com.info.meisodeneme.view

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.info.meisodeneme.R
import com.info.meisodeneme.databinding.FragmentMeditationDetailBinding
import com.info.meisodeneme.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_meditation_detail.*
import java.text.DateFormat
import java.util.*

class MeditationDetailFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var dataBinding: FragmentMeditationDetailBinding
    private val args : MeditationDetailFragmentArgs by navArgs()
    private var backPressed = 0L
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


        val back_media = dataBinding.backMedia

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

        back_media.setOnClickListener {
            MeditationDetailFragmentDirections.actionMediaDetailFragmentToHomeFragment()
            findNavController().popBackStack()
            mediaPlayer.pause()

        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(backPressed + 2000 > System.currentTimeMillis()) {
                    mediaPlayer.pause()
                    findNavController().popBackStack() 
                }else {
                    Toast.makeText(getActivity()?.getApplicationContext(), "Press back again to pause media", Toast.LENGTH_SHORT).show()
                }
                backPressed = System.currentTimeMillis()
                /*mediaPlayer.pause()
                findNavController().navigate(R.id.action_mediaDetailFragment_to_homeFragment)*/
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getDatas()
        observeLiveData()
    }



   private fun observeLiveData() {
        viewModel.LiveData.observe(viewLifecycleOwner) {
            it?.let {
                
               dataBinding.selectItem = args.argDataModel
            }
        }
      /* viewModel.Loading.observe(viewLifecycleOwner) { loading ->
           loading?.let {
               if (loading) {
                   LoadingH.visibility = View.VISIBLE
               }else
               {
                   LoadingH.visibility = View.GONE
               }
           }
       }*/
    }
}










