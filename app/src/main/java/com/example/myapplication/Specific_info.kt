package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class Specific_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_info)
        val name = intent.getStringExtra("name")
        val num = intent.getStringExtra("num")
        var email = intent.getStringExtra("email")
        var loc = intent.getStringExtra("loc")
        var note = intent.getStringExtra("note")

        val phone_but = findViewById<LinearLayout>(R.id.spec_phone_button)
        val message_but = findViewById<LinearLayout>(R.id.spec_message_button)
        val mail_but = findViewById<LinearLayout>(R.id.spec_email_button)

        phone_but.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + num)
            startActivity(dialIntent)
        }
        message_but.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_VIEW)
            dialIntent.data = Uri.parse("sms:" + num)
            startActivity(dialIntent)
        }
        mail_but.setOnClickListener {
            try{
                if (email != ""){
                    val dialIntent = Intent(Intent.ACTION_SENDTO)
                    dialIntent.data = Uri.parse("mailto:" + email)
                    startActivity(dialIntent)
                }
            }
            catch(e: Exception){
                Toast.makeText(this, "이메일을 보낼 수 있는 어플리케이션이 설치되지 않았습니다", Toast.LENGTH_SHORT).show()
            }
        }


        val spec_name = findViewById<TextView>(R.id.spec_name)
        val spec_num = findViewById<TextView>(R.id.spec_num)
        val spec_email = findViewById<TextView>(R.id.spec_email)
        val spec_loc = findViewById<TextView>(R.id.spec_loc)
        val spec_note = findViewById<TextView>(R.id.spec_note)
        val spec_pic = findViewById<ImageView>(R.id.spec_pic)


        for (i in DiaryList){
            if (i.name == name){
                if (i.uri != null){
                    Log.d("debug", "in uri")
                    spec_pic.setImageURI(Uri.parse(i.uri))
                }
                else if (i.pic != null){
                    Log.d("debug", "in pic")
                    spec_pic.setImageBitmap(i.pic)
                }
            }
        }

        spec_name.setText(name)
        spec_num.setText(num)
        if (email == ""){
            email = "이메일 정보 없음"
        }
        if (loc == ""){
            loc = "주소 정보 없음"
        }
        if (note == ""){
            note = "기타 정보 없음"
        }
        spec_email.setText(email)
        spec_loc.setText(loc)
        spec_note.setText(note)

    }
}