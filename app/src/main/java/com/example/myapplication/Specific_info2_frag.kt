package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
class Specific_info2_frag(val index: Int) : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity =  inflater.inflate(R.layout.activity_specific_info2, container, false)

        val spec_icon = activity.findViewById<ImageView>(R.id.spec_icon)
        val spec_name = activity.findViewById<TextView>(R.id.spec_pic_name)
        val spec_gps = activity.findViewById<TextView>(R.id.spec_pic_gps)
        val spec_date= activity.findViewById<TextView>(R.id.spec_pic_date)
        val name = exifName.get(index)
        val gps = exifGPS.get(index)
        val date = exifDate.get(index)

        val uri = Uri.parse(urlList.get(index))
        spec_icon.setImageURI(uri)
        if (name != null){
            spec_name.setText(name)
        }
        else{
            spec_name.setText(" ")
        }
        if (gps != null){
            spec_gps.setText(gps)
        }
        else{
            spec_gps.setText(" ")
        }
        if (date != null){
            spec_date.setText(date)
        }
        else{
            spec_date.setText(" ")
        }
        return activity
    }
}