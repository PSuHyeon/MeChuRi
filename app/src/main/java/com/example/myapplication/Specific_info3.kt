package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

lateinit var place_catch: ArrayList<Place>
class Specific_info3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment3_pager)
        val menu_name = intent.getStringExtra("menu_name")
        val pager = findViewById<ViewPager>(R.id.view_pager3)
        val temp_list = ArrayList<Place>()
        for (i in place_list){
            if (i.menu_name == menu_name){
                temp_list.add(i)
            }
        }
        place_catch = temp_list
        pager.adapter = PagerAdapter4(supportFragmentManager, temp_list.size)
        pager.setPageTransformer(true, ZoomOutPageTransformer())
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_specific_info3)
//
//        val menu_name = intent.getStringExtra("menu_name")
//        val name = findViewById<TextView>(R.id.spec_place_name)
//        val loc = findViewById<TextView>(R.id.spec_place_loc)
//        val num = findViewById<TextView>(R.id.spec_place_num)
//        val dis = findViewById<TextView>(R.id.spec_place_dis)
//        val time = findViewById<TextView>(R.id.spec_place_time)
//        val back = findViewById<ImageView>(R.id.background)
//        val link_button = findViewById<Button>(R.id.link_button)
//
//        for (i in place_list){
//            if (i.menu_name == menu_name){
//                if (i.pic != null){
//                    back.setImageResource(i.pic)
//                }
//                name.setText(i.place_name)
//                loc.setText(i.loc)
//
//                link_button.setOnClickListener {
//                    val intent = Intent(this, Specific_location::class.java)
//                    var index = 0
//                    for (i in 0 until place_list.size){
//                        if (place_list.get(i).menu_name == menu_name){
//                            index = i
//                        }
//                    }
//                    intent.putExtra("index" , index)
//                    startActivity(intent)
//                }
//
//                num.setOnClickListener{
//                    val dialIntent = Intent(Intent.ACTION_DIAL)
//                    dialIntent.data = Uri.parse("tel:" + i.num)
//                    startActivity(dialIntent)
//                }
//
//                num.setText(i.num)
//                dis.setText(i.menu_name)
//                time.setText(i.time)
//            }
//        }
//
    }

}

class PagerAdapter4(
    fragmentManager: FragmentManager,
    val tabCount: Int
): FragmentStatePagerAdapter(fragmentManager){
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return Specific_info3_frag(position)
    }
}