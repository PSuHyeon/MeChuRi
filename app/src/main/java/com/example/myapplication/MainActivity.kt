package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var image: ImageView
    var imageUri2: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.information)
        var names = ""
        var nums = ""
        var emails = ""
        var locs = ""
        var notes = ""
        val name = findViewById<EditText>(R.id.info_name)
        val num = findViewById<EditText>(R.id.info_num)
        val email = findViewById<EditText>(R.id.info_email)
        val loc = findViewById<EditText>(R.id.info_loc)
        val note = findViewById<EditText>(R.id.info_note)
        val but = findViewById<Button>(R.id.info_add)
        image = findViewById<ImageView>(R.id.add_icon)
        val upload = findViewById<ImageView>(R.id.ic_upload)
        upload.setOnClickListener{
            val gallery = Intent(Intent.ACTION_OPEN_DOCUMENT,  MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }
        val resultIntent = Intent()
        resultIntent.putExtra("ddd", 383883)
        but.setOnClickListener{
            names = name.getText().toString()
            nums = num.getText().toString()
            emails = email.getText().toString()
            locs = loc.getText().toString()
            notes = note.getText().toString()
            if (names == ""){
                name.setHintTextColor(Color.RED)
            }
            if (nums == ""){
                num.setHintTextColor(Color.RED)
            }
            else{
                DiaryList.add(Diary(names, nums, emails, null, locs, notes, imageUri2.toString()))

                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            imageUri2 = data?.data
            contentResolver.takePersistableUriPermission(imageUri2!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            image.setImageURI(imageUri2)
        }
    }
}



