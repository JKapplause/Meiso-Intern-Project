package com.info.meisodeneme.view


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.makeText
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.info.meisodeneme.R
import com.info.meisodeneme.databinding.FragmentSignInBinding
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    lateinit var preference: SharedPreferences
    private var isScrollContainer: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()


        emailFocusListener()
        passwordFocusListener()

        val remember = binding.signinCheckbox
        preference = this.requireActivity().getSharedPreferences("checkbox",Context.MODE_PRIVATE)
        val checkbox : String = preference.getString("remember", "")!!
        if(checkbox == "true") {
            val action =SliderFragmentDirections.actionSliderFragmentToHomeFragment()
            findNavController().navigate(action)
        }else if(checkbox == "false") {
            makeText(getActivity()?.getApplicationContext(),"Please click remember me",Toast.LENGTH_SHORT).show()
        }

        remember.setOnCheckedChangeListener { compoundButton, b ->
            if(compoundButton.isChecked) {
                preference = this.requireActivity().getSharedPreferences("checkbox",Context.MODE_PRIVATE)
                val editor = preference.edit()
                editor.putString("remember","true")
                editor.apply()
                makeText(getActivity()?.getApplicationContext(),"checked",Toast.LENGTH_SHORT).show()
        }else if(!compoundButton.isChecked){
                preference = this.requireActivity().getSharedPreferences("checkbox",Context.MODE_PRIVATE)
                val editor = preference.edit()
                editor.putString("remember","false")
                editor.apply()
                makeText(getActivity()?.getApplicationContext(),"unchecked",Toast.LENGTH_SHORT).show()
            }
        }

        val btn_signin = binding.signinButton
        btn_signin.setOnClickListener {
            val mail = signin_emailET.text.toString()
            val pass = signin_passwordET.text.toString()
            if (mail.isNotEmpty() && pass.isNotEmpty()) {
                auth.signInWithEmailAndPassword(
                    signin_emailET.text.toString(),
                    signin_passwordET.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val action = SliderFragmentDirections.actionSliderFragmentToHomeFragment()
                        findNavController(it).navigate(action)
                    }
                }.addOnFailureListener { exception ->
                    makeText(
                        getActivity(),
                        exception.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }else if (mail.isEmpty() || pass.isEmpty()) {
                binding.infoCheck.setText("Lütfen gerekli yerleri doldurun!")
                showCustomToast()
            }
        }
    }

    private fun showCustomToast() {
        val ll = view?.findViewById<LinearLayout>(R.id.info_layout)
        val layout = layoutInflater.inflate(R.layout.fragment_sign_in, ll)
        if (layout.visibility == View.VISIBLE) {
            layout.setVisibility(View.INVISIBLE)
        } else {
            isScrollContainer = true
            layout.setVisibility(View.VISIBLE)
        }
    }

    private fun emailFocusListener() {
        binding.signinEmailET.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.signinEmail.prefixText = validEmail()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun validEmail(): String? {
        val emailText = binding.signinEmailET.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            binding.infoCheck.setText("Mail boş bırakılamaz !")
            showCustomToast()
        }else {
            binding.infoLayout.setVisibility(View.INVISIBLE)
        }
        return null
    }

    private fun passwordFocusListener() {

        binding.signinPasswordET.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.signinPassword.prefixText = validPassword()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun validPassword(): String? {
        val password = binding.signinPasswordET.text.toString()
        if (password.isEmpty()) {
            binding.infoCheck.setText("Şifre boş bırakılamaz!")
            showCustomToast()
        }else {
            binding.infoLayout.setVisibility(View.INVISIBLE)
        }
        return null
    }
}









