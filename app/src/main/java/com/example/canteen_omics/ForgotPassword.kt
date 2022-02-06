package com.example.canteen_omics

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class ForgotPassword : Fragment() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText

    private lateinit var resetPasswordBtn: Button
    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        emailEt = view.findViewById(R.id.email_edt_text)

        resetPasswordBtn = view.findViewById(R.id.reset_pass_btn)
        back = view.findViewById(R.id.back_btn)

        back.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPassword_to_navigation_add)
        }

        resetPasswordBtn.setOnClickListener {
            var email: String = emailEt.text.toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(context, "Please enter email id", Toast.LENGTH_LONG).show()
            } else {
                auth.sendPasswordResetEmail(email).addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context,"Reset link sent to your email", Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(context, "Unable to send reset mail", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            }
        }
    }
}