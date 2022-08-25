package com.info.meisodeneme.view


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Patterns
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import com.google.android.gms.safetynet.SafetyNet
import com.google.android.gms.safetynet.SafetyNetApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.info.meisodeneme.R
import com.info.meisodeneme.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RegisterFragment : Fragment(), GoogleApiClient.ConnectionCallbacks {

    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
  //  private val userCollectionRef = Firebase.firestore.collection("users")
  //  private lateinit var user : ArrayList<User>
    private lateinit var googleApiClient : GoogleApiClient
    private var siteKey = "6Lejum4hAAAAAKR4RReLt5VfPiM0sxkgjyeFCIB5"
    private var isScrollContainer: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Google Recaptcha
    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        googleApiClient = GoogleApiClient.Builder(requireActivity().getApplicationContext())
            .addApi(SafetyNet.API)
            .addConnectionCallbacks(this)
            .build()
        googleApiClient.connect()


        binding.signupRecaptcha.setOnClickListener {
            val recaptcha = binding.signupRecaptcha
            if(recaptcha.isChecked) {
                SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,siteKey)
                    .setResultCallback(ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                        fun onResult(@NonNull recaptchaTokenResult: SafetyNetApi.RecaptchaTokenResult ) {
                            val status : Status = recaptchaTokenResult.getStatus()
                            if(status.isSuccess()) {
                                Toast.makeText(requireActivity().getApplicationContext(),"Successfully Varifeid...", Toast.LENGTH_LONG).show()

                                recaptcha.setTextColor(Color.GREEN)
                            }



                        }

                    })
            }else {
                recaptcha.setTextColor(Color.BLACK)
            }
        }

        //Specific word bold/ clickable
        val register_checkbox1_textView = binding.registerCheckbox1TextView
        val text = "“Üye Ol” butonuna basarak Mobven Üyelik Sözleşmesi’ni, kişiselleştirilmiş pazarlamaya ilişkin Açık Rıza Metni’ni, yurt dışına aktarıma ilişkin Açık Rıza Metni’ni okuduğunuzu ve kabul ettiğinizi onaylıyorsunuz."
         val spannableString = SpannableString(text)
        val clickableSpan : ClickableSpan  = object : ClickableSpan(){
            override fun onClick(p0: View) {
                val t = Toast.makeText(requireActivity().getApplicationContext(), "Üye Ol",Toast.LENGTH_LONG)
                t.setGravity(0,0,0)
                t.show()
            }
        }
        val clickableSpan2 : ClickableSpan  = object : ClickableSpan(){
            override fun onClick(p0: View) {
                val t = Toast.makeText(requireActivity().getApplicationContext(), "Üyeliş Sözleşmesi",Toast.LENGTH_LONG)
                t.setGravity(Gravity.BOTTOM, 0 ,0)
                t.show()
            }
        }
        val clickableSpan3 : ClickableSpan  = object : ClickableSpan(){
            override fun onClick(p0: View) {
                val t = Toast.makeText(requireActivity().getApplicationContext(), "Açık Rıza Metni",Toast.LENGTH_LONG)
                t.setGravity(Gravity.BOTTOM , 0 , 0)
                t.show()
            }
        }
        val boldSpan = StyleSpan(Typeface.BOLD)
        val underlineSpan = StyleSpan(Typeface.BOLD)
        val boldUnderLine = UnderlineSpan()
        val boldUnderlineSpan = StyleSpan(Typeface.BOLD)
        val underline = UnderlineSpan()

        with(spannableString) {
            setSpan(boldSpan, 1,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(clickableSpan,1,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(underlineSpan,33,51,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(boldUnderLine,33,51,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(clickableSpan2,33,51,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(underline,93,108,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(boldUnderlineSpan,93,108,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(clickableSpan3,93,108,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        register_checkbox1_textView.text = spannableString
        register_checkbox1_textView.movementMethod = LinkMovementMethod.getInstance()


        // call check
        nameFocusListener()
        emailFocusListener()
        passwordFocusListener()
        surnameFocusListener()


        val signup_button = binding.signupButton

        signup_button.setOnClickListener {

            val email = signup_mailET.text.toString()
            val password = signup_passwordET.text.toString()
            val name = signup_nameET.text.toString()
            val surname = signup_surnameET.text.toString()
           // val recaptcha = signup_recaptcha.isChecked
            val checkbox1 = register_checkbox1.isChecked
            val checkbox2 = register_checkbox2.isChecked
            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() && checkbox1 && checkbox2&& /*recaptcha &&*/
                    password.matches(".*[A-Z].*".toRegex()) && password.matches(".*[a-z].*".toRegex()) && password.matches(".*[0-9].*".toRegex())&&
                    surname.length >= 3 && name.length >= 3) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                           // saveUser(User(name))
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

            } else if (!checkbox1 || !checkbox2 || email.isEmpty() || password.isEmpty() ||/* !recaptcha || */name.isEmpty() || surname.isEmpty()) {
                 showCustomToast()
            }
        }

    }
     private fun showCustomToast() {
         val ll = view?.findViewById<LinearLayout>(R.id.info_layout)
         val layout = layoutInflater.inflate(R.layout.fragment_register, ll)

         if (layout.visibility == View.VISIBLE) {
             layout.setVisibility(View.INVISIBLE)
         } else {
             isScrollContainer = true
             layout.setVisibility(View.VISIBLE)

             }
         }



   private fun nameFocusListener() {
       binding.signupNameET.setOnFocusChangeListener { _, focused ->
           if (!focused) {
               binding.signupName.suffixText = validName()
           }
       }
   }

    @SuppressLint("SetTextI18n")
    private fun validName(): String? {
        val name = binding.signupNameET.text.toString()
            if(name.length < 3 ) {
                binding.infoCheck.setText("Ad uzunluğu 3 karakterden az olamaz!")
                showCustomToast()
            } else if (name.matches(".*[0-9].*".toRegex())) {
            binding.infoCheck.setText("Sayı kullanmayın!")
            showCustomToast()
        }else {
                binding.infoLayout.setVisibility(View.INVISIBLE)
        }

        return null
    }

    private fun surnameFocusListener() {
        binding.signupSurnameET.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.signupSurname.prefixText = validSurName()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun validSurName(): String? {
        val surname = binding.signupSurnameET.text.toString()
        if(surname.length < 3 ) {
            binding.infoCheck.setText("Soyad uzunluğu 3 karakterden az olamaz!")
            showCustomToast()
        } else if (surname.matches(".*[0-9].*".toRegex())) {
            binding.infoCheck.setText("Sayı kullanmayın!")
           showCustomToast()
        }else{
            binding.infoLayout.setVisibility(View.INVISIBLE)
        }

        return null
    }


   private fun passwordFocusListener() {

        binding.signupPasswordET.setOnFocusChangeListener { _, focused ->
                if (!focused) {
                    binding.signupPassword.prefixText = validPassword()
                }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun validPassword(): String? {
        val password = binding.signupPasswordET.text.toString()
        if (password.length < 6) {
            binding.infoCheck.setText("Şifre uzunluğu 6 karakterden az olamaz!")
            showCustomToast()
        }else if( !password.matches(".*[A-Z].*".toRegex())) {
            binding.infoCheck.setText("Şifre en az 1 büyük harf içermelidir !")
            showCustomToast()
        }else if(!password.matches(".*[a-z].*".toRegex())) {
            binding.infoCheck.setText("Şifre en az 1 küçük harf içermelidir !")
            showCustomToast()
        }else {
            binding.infoLayout.setVisibility(View.INVISIBLE)
        }

        return null
    }

    private fun emailFocusListener() {
        binding.signupMailET.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.signupMail.prefixText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.signupMailET.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            binding.infoCheck.setText("Mail boş bırakılamaz !")
            showCustomToast()
        }else {
            binding.infoLayout.setVisibility(View.INVISIBLE)
        }
        return null
    }

    // Recaptcha
    override fun onConnected(p0: Bundle?) {

    }

    override fun onConnectionSuspended(p0: Int) {

    }



}




/* private fun saveUser(user: User) = CoroutineScope(Dispatchers.IO).launch {
        try {
            userCollectionRef.add(user).await()
            withContext(Dispatchers.Main) {

            }

        }catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText( getActivity()?.getApplicationContext(), e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
*/





/*      val ll = view?.findViewById<LinearLayout>(R.id.info_layout)
       val layout = layoutInflater.inflate(R.layout.custom_toast_message, ll)

 with(Toast(getActivity()?.getApplicationContext())) {
               duration = Toast.LENGTH_LONG
               setGravity(Gravity.TOP, 0, 0)
               view = layout
               show()
      }
  */






