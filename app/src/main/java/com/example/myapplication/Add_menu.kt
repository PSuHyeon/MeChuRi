package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.naver.maps.geometry.LatLng
import java.util.*

class Add_menu : AppCompatActivity() {
    lateinit var image : ImageView
    var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dinner_menu)

        val menu_name = findViewById<EditText>(R.id.menu_name)
        val place_name = findViewById<EditText>(R.id.place_name)
        val place_num = findViewById<EditText>(R.id.place_num)
        val place_time = findViewById<EditText>(R.id.place_time)
        val menu_add = findViewById<Button>(R.id.menu_add)
        val upload = findViewById<Button>(R.id.menu_upload)
        image = findViewById<ImageView>(R.id.add_menu_icon)
        val ty = intent.getStringExtra("time")

        upload.setOnClickListener{
            val gallery = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }
        menu_add.setOnClickListener{
            if (menu_name.text.toString() == "" || place_name.text.toString() == ""){
                menu_name.setHintTextColor(Color.RED)
                place_name.setHintTextColor(Color.RED)
            }
            else{
                if (ty == "lunch"){

                    val geoCoder = Geocoder(this, Locale.KOREA)

                    var address = geoCoder.getFromLocationName(place_name.text.toString(), 10)


                    var test = address.get(0).getAddressLine(0)


                    lunch_menuList.add(menu_name.text.toString())
                        place_list.add(Place(menu_name.text.toString(), place_name.text.toString(), place_num.text.toString(),
                            address.get(0).latitude, address.get(0).longitude, place_time.text.toString()
                            , null,imageUri, test ))


                }
                else{
                    dinner_menuList.add(menu_name.text.toString())
                    if (place_name.text.toString() != ""){
                        place_list.add(Place(menu_name.text.toString(), place_name.text.toString(), place_num.text.toString(),
                            0.0, 0.0, place_time.text.toString()
                        , null, imageUri, "주소"))
                    }
                }
                finish()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            imageUri = data?.data
            image.setImageURI(imageUri)
        }
    }
}