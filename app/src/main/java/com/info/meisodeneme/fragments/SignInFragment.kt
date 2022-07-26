package com.info.meisodeneme.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.info.meisodeneme.R


class SignInFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         return inflater.inflate(R.layout.fragment_sign_in, container, false)

    }
}


        //  val signin_button = view.findViewById<Button>(R.id.signin_button)

        /* signin_button.setOnClickListener {

            auth.signInWithEmailAndPassword(login_emailET.text.toString(),login_passwordET.text.toString()).addOnCompleteListener {  task->
                if(task.isSuccessful) {
                    val currentUser = auth.currentUser?.email.toString()
                    Toast.makeText(this,"Welcome: ${currentUser}", Toast.LENGTH_LONG).show()



                }
            }.addOnFailureListener { exception->
                Toast.makeText(this,exception.localizedMessage, Toast.LENGTH_LONG).show()
            }


        }
        return view
    }*/


