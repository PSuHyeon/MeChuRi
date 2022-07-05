package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
class Specific_info3_frag(val gm: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val activity = inflater.inflate(R.layout.activity_specific_info3, container, false)

        if (gm >= place_catch.size){
            return activity
        }
        val name = activity.findViewById<TextView>(R.id.spec_place_name)
        val loc = activity.findViewById<TextView>(R.id.spec_place_loc)
        val num = activity.findViewById<TextView>(R.id.spec_place_num)
        val dis = activity.findViewById<TextView>(R.id.spec_place_dis)
        val time = activity.findViewById<TextView>(R.id.spec_place_time)
        val back = activity.findViewById<ImageView>(R.id.background)
        val link_button = activity.findViewById<Button>(R.id.link_button)
        val call_button = activity.findViewById<Button>(R.id.link_button2)

        val menu_name = place_catch.get(gm).menu_name
        val num1 = place_catch.get(gm).num
        val place_name = place_catch.get(gm).place_name
        dis.setText(menu_name)
        name.setText(place_catch.get(gm).place_name)
        loc.setText(place_catch.get(gm).loc)
        num.setText(place_catch.get(gm).num)
        time.setText(place_catch.get(gm).time)
        if (place_catch.get(gm).pic != null){
            place_catch.get(gm)!!.pic?.let { back.setImageResource(it) }
        }



                link_button.setOnClickListener {
                    val intent = Intent(context, Specific_location::class.java)
                    var index = 0
                    for (i in 0 until place_list.size){
                        if (place_list.get(i).place_name == place_name){
                            index = i
                        }
                    }
                    intent.putExtra("index" , index)
                    startActivity(intent)
                }

                call_button.setOnClickListener{
                    val dialIntent = Intent(Intent.ACTION_DIAL)
                    dialIntent.data = Uri.parse("tel:" + num1)
                    startActivity(dialIntent)
                }

        return activity
            }


}