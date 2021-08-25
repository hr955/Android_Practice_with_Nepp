package com.example.pizzaorderapp_20210825.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pizzaorderapp_20210825.EditNicknameActivity
import com.example.pizzaorderapp_20210825.R
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    private val REQUEST_CODE = 100

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        edtNicknameBtn.setOnClickListener {
            startActivityForResult(Intent(requireContext(), EditNicknameActivity::class.java), REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                nicknameTxt.text = data!!.getStringExtra("Nickname")
            }
        }
    }
}