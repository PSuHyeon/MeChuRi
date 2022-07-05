package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.security.AccessController.getContext

var delcond = 0
var DiaryList = ArrayList<Diary>()
val Deletename = ArrayList<String>()



lateinit var context1: Context

lateinit var sp1 : SharedPreferences
lateinit var editor : SharedPreferences.Editor
val gson = GsonBuilder().create()
lateinit var groupListType: Type


class Fragment1: Fragment() {




    override fun onAttach(context: Context) {
        super.onAttach(context)
        context1 = context
        sp1 = context.getSharedPreferences("userList", Context.MODE_PRIVATE)
        editor = sp1.edit()
        groupListType= object : TypeToken<ArrayList<Diary?>?>(){}.type
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

        if (take == "0") {
            for (i in 0 until numbook.size) {
                DiaryList.add(
                    Diary(
                        namebook.get(i),
                        numbook.get(i),
                        emailbook.get(i),
                        photobook.get(i),
                        addressbook.get(i),
                        notebook.get(i)
                    ,null
                    )
                )
            }

            val newdiary = gson.toJson(DiaryList,groupListType)
            editor.putString("messageList", newdiary)
            editor.apply()
        }


        val activity =  inflater.inflate(R.layout.activity_main, container,
            false)
        val recycler_view = activity.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = Adapter(DiaryList, inflater)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(context, 2)

        return activity
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recycler_view)
        if (requestCode == 200){
            val newdiary = gson.toJson(DiaryList,groupListType)
            editor.putString("messageList", newdiary)
            editor.apply()
            recyclerView.adapter = Adapter(DiaryList, layoutInflater)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_add -> {
                val intent = Intent(context, MainActivity::class.java)
                startActivityForResult(intent, 200)
                return true
            }
            R.id.nav_delete -> {
                if (delcond == 0){
                    delcond = 1
                    item.setIcon(R.drawable.ic_check)

                }
                else if (delcond == 1){
                    item.setIcon(R.drawable.ic_delete)
                    while(Deletename.size != 0) {
                        for (i in DiaryList) {
                            if (i.name in Deletename) {
                                DiaryList.remove(i)
                                Deletename.remove(i.name)
                                break
                            }
                        }
                    }
                    val newdiary = gson.toJson(DiaryList,groupListType)
                    editor.putString("messageList", newdiary)
                    editor.apply()
                    delcond = 0
                    val recyclerView = requireView().findViewById<RecyclerView>(R.id.recycler_view)
                    recyclerView.adapter = Adapter(DiaryList, layoutInflater)
                }
                return true
            }
            R.id.nav_refresh -> {
                for (i in 0 until namebook.size){
                    var found = false
                    for (j in DiaryList){
                        if (j.name == namebook.get(i)){
                            found = true
                        }
                    }
                    if (!found){
                        DiaryList.add(
                            Diary(
                                namebook.get(i),
                                numbook.get(i),
                                emailbook.get(i),
                                photobook.get(i),
                                addressbook.get(i),
                                notebook.get(i),null
                            )
                        )
                    }
                }
                val newdiary = gson.toJson(DiaryList,groupListType)
                editor.putString("messageList", newdiary)
                editor.apply()
                val recyclerView = requireView().findViewById<RecyclerView>(R.id.recycler_view)
                recyclerView.adapter = Adapter(DiaryList, layoutInflater)
                return true
            }
            else -> return true
        }
    }
}

class Adapter(
    val DiaryList: ArrayList<Diary>,
    val inflater : LayoutInflater
): RecyclerView.Adapter<Adapter.ViewHolder>(){
    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val name : TextView
        val num : TextView
        val icon : ImageView
        init{
            name = itemView.findViewById<TextView>(R.id.name)
            num = itemView.findViewById<TextView>(R.id.number)
            icon = itemView.findViewById<ImageView>(R.id.icon)
            itemview.setOnClickListener{
                if (delcond == 1) {
                    val names = name.getText().toString()
                    if (names in Deletename){
                        Deletename.remove(names)
                        name.setTextColor(Color.GRAY)
                    }
                    else{
                        Deletename.add(names)
                        name.setTextColor(Color.rgb(200,0,0))
                    }
                }
                else{
                    val intent = Intent(context1, Specific_info::class.java)

                    intent.putExtra("name", name.getText().toString())
                    intent.putExtra("num", num.getText().toString())
                    for (i in DiaryList){
                        if (i.name == name.getText().toString() && i.number == num.getText().toString()){
                            intent.putExtra("email", i.email)
                            intent.putExtra("loc", i.loc)
                            intent.putExtra("note", i.note)
                            break
                        }
                    }
                    context1.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_view, parent, false )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(DiaryList.get(position).name)
        holder.num.setText(DiaryList.get(position).number)
        if (DiaryList.get(position).uri == null){
            for (i in 0 until namebook.size){
                if (DiaryList.get(position).name == namebook.get(i)){
                    if (photobook.get(i) != null){
                        holder.icon.setImageBitmap(photobook.get(i))
                    }
                }
            }
        }
        else{
            holder.icon.setImageURI(Uri.parse(DiaryList.get(position).uri))
        }


    }

    override fun getItemCount(): Int {
        return DiaryList.size
    }
}