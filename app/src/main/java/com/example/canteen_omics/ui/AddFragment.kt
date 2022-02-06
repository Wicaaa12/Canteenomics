package com.example.canteen_omics.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.navigation.fragment.findNavController
import com.example.canteen_omics.MainActivity
import com.example.canteen_omics.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class AddFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var loginBtn: Button
    private lateinit var resetPasswordTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailEt = view.findViewById(R.id.email_edt_text)
        passwordEt = view.findViewById(R.id.pass_edt_text)

        loginBtn = view.findViewById(R.id.login_btn)

        resetPasswordTv = view.findViewById(R.id.reset_pass_tv)

        auth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            var email: String = emailEt.text.toString()
            var password: String = passwordEt.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(context, "Please fill all the fields", LENGTH_LONG).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Successfully Logged In", LENGTH_LONG).show()
                            findNavController().navigate(R.id.action_navigation_add_to_adminFragment)
                        } else {
                            Toast.makeText(context, "Login Failed", LENGTH_LONG).show()
                        }
                    }
            }
        }
        resetPasswordTv.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_add_to_forgotPassword)
        }

    }
}