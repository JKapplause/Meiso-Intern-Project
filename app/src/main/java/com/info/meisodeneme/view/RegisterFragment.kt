package com.info.meisodeneme.view


import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
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


        val signup_button = view.findViewById<Button>(R.id.signup_button)

        signup_button.setOnClickListener {
            val email = signup_mailET.text.toString()
            val password = signup_passwordET.text.toString()
            val name = signup_nameET.text.toString()
            val surname = signup_surnameET.text.toString()
            val recaptcha = signup_recaptcha.isChecked
            val checkbox1 = register_checkbox1.isChecked
            val checkbox2 = register_checkbox2.isChecked



                if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() && checkbox1 && checkbox2 && recaptcha) {

                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {

                                    val action = SliderFragmentDirections.actionSliderFragmentToHomeFragment()
                                    findNavController(it).navigate(action)
                                }
                            }.addOnFailureListener { exception ->
                                Toast.makeText(
                                    getActivity()?.getApplicationContext(),
                                    exception.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                            }

            } else if(!checkbox1 || !checkbox2 || email.isEmpty() || password.isEmpty() || !recaptcha || name.isEmpty() || surname.isEmpty()){
                    showCustomToast()
                }

        }
    }

    private fun showCustomToast() {
        val ll = view?.findViewById<LinearLayout>(R.id.info_layout)
        val layout = layoutInflater.inflate(R.layout.custom_toast_message, ll)

        with(Toast(getActivity()?.getApplicationContext())) {
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.TOP, 10,450)
            view = layout
            show()
        }
    }
}
























