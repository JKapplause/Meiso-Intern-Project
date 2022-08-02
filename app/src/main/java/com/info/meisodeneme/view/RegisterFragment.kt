package com.info.meisodeneme.view


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.FragmentManager.TAG
import androidx.navigation.Navigation.findNavController
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.safetynet.SafetyNet
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.info.meisodeneme.R
import com.info.meisodeneme.model.User
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.util.concurrent.Executor


class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private val userCollectionRef = Firebase.firestore.collection("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()


    }

    @SuppressLint("RestrictedApi")
    fun onClick(view: View) {
        SafetyNet.getClient(requireActivity().getApplicationContext()).verifyWithRecaptcha("6LeEsjwhAAAAAFeJRNT9N3ktabWcUtAugzMo2hr8")
            .addOnSuccessListener(this as Executor, OnSuccessListener { response ->

                val userResponseToken = response.tokenResult
                if (response.tokenResult?.isNotEmpty() == true) {

                }
            })
            .addOnFailureListener(this as Executor, OnFailureListener { e ->
                if (e is ApiException) {

                    Log.d(TAG, "Error: ${CommonStatusCodes.getStatusCodeString(e.statusCode)}")
                } else {

                    Log.d(TAG, "Error: ${e.message}")
                }
            })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_register, container, false)

    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var currentUser = auth.currentUser




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

                                    saveUser(User(name, email, surname,password))
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

    private fun saveUser(user: User) = CoroutineScope(Dispatchers.IO).launch {
        try {
            userCollectionRef.add(user).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(getActivity()?.getApplicationContext(),"Successfully saved data",Toast.LENGTH_LONG).show()
            }

        }catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText( getActivity()?.getApplicationContext(), e.message, Toast.LENGTH_LONG).show()
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


























