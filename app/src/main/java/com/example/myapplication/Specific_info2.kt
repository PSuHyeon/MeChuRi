package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager


lateinit var pager2: ViewPager
class Specific_info2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val index = intent.getIntExtra("index", 0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_pager)
        pager2 = findViewById(R.id.pager2)
        pager2.adapter = PagerAdapter3(supportFragmentManager, urlList.size)
        pager2.setPageTransformer(true, ZoomOutPageTransformer())
        Handler().postDelayed(Runnable { pager2.setCurrentItem(index) }, 100)
    }
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_specific_info2)
//        val name_info = intent.getStringExtra("name")
//        val index = exifName.indexOf(name_info)
//        val spec_icon = findViewById<ImageView>(R.id.spec_icon)
//        val spec_name = findViewById<TextView>(R.id.spec_pic_name)
//        val spec_gps = findViewById<TextView>(R.id.spec_pic_gps)
//        val spec_date= findViewById<TextView>(R.id.spec_pic_date)
//        val name = exifName.get(index)
//        val gps = exifGPS.get(index)
//        val date = exifDate.get(index)
//
//        val uri = Uri.parse(urlList.get(index))
//        spec_icon.setImageURI(uri)
//        if (name != null){
//            spec_name.setText(name)
//        }
//        else{
//            spec_name.setText(" ")
//        }
//        if (gps != null){
//            spec_gps.setText(gps)
//        }
//        else{
//            spec_gps.setText(" ")
//        }
//        if (date != null){
//            spec_date.setText(date)
//        }
//        else{
//            spec_date.setText(" ")
//        }
//    }


}

class PagerAdapter3(
    fragmentManager: FragmentManager,
    val tabCount: Int
): FragmentStatePagerAdapter(fragmentManager){
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return Specific_info2_frag(position)
    }
}

class ZoomOutPageTransformer : ViewPager.PageTransformer {
    private val MIN_SCALE = 0.85f
    private val MIN_ALPHA = 0.5f

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 1 -> { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // Fade the page relative to its size.
                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }
        }
    }
}