package com.info.meisodeneme.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.info.meisodeneme.R
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val btn_signup = view.findViewById<Button>(R.id.signup_button)

        btn_signup?.setOnClickListener {
            val email = signup_mailET.text.toString()
            val password = signup_passwordET.text.toString()

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val action = SliderFragmentDirections.actionSliderFragmentToHomeFragment()
                    findNavController(it).navigate(action)

                    //findNavController().navigate(action)
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(
                    getActivity()?.getApplicationContext(),
                    exception.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }
}








