package com.sarada.userdetails

import android.R.attr.left
import android.R.attr.right
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.marginBottom
import com.sarada.networkutils.data.UserData


class UserDetailsFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_form)

        var user = UserData("","", listOf())

        val linearLayout: LinearLayoutCompat = findViewById(R.id.user_details_form_linear_layout)

        if(intent.hasExtra("user")){
            user = intent.getParcelableExtra("user")!!

            val uiData = user.uiData

            //Using indices to avoid IndexOutOfBound errors caused when using until or range
            for(i in uiData.indices){
                when(uiData[i].uiType){
                    "label" -> {
                        val textView = TextView(this)
                        textView.layoutParams= LinearLayoutCompat.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        textView.text = uiData[i].value
                        textView.tag = uiData[i].key
                        linearLayout.addView(textView)
                    }
                    "edittext" -> {
                        val editText = EditText(this)
                        editText.layoutParams= LinearLayoutCompat.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        editText.hint = uiData[i].hint
                        editText.tag = uiData[i].key
                        linearLayout.addView(editText)
                    }
                    "button" -> {
                        val button = Button(this)
                        button.layoutParams= LinearLayoutCompat.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                        button.text = uiData[i].value
                        linearLayout.addView(button)
                    }
                }
            }
        }else{
            val textView = TextView(this)
            textView.layoutParams= LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            textView.text = "There are no views to display"
            linearLayout.addView(textView)
        }

    }
}