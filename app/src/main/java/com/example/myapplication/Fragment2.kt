package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

val urlList: ArrayList<String?> = listOfAllImage
lateinit var context2: Context
class Fragment2: Fragment() {

    override fun onAttach(context: Context) {
        context2 = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val activity =  inflater.inflate(R.layout.fragment2, container,
            false)
        val recycler_view = activity.findViewById<RecyclerView>(R.id.recycler_view2)
        val adapter = Adapter2(urlList, inflater)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(context, 2)

        return activity
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu2, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_refresh2 -> {
                val intent = Intent(context, MainActivity::class.java)
                startActivityForResult(intent, 200)
                return true
            }
            else -> return true
        }
    }



}

class Adapter2(
    val urlList: ArrayList<String?>,
    val inflater : LayoutInflater
): RecyclerView.Adapter<Adapter2.ViewHolder2>(){
    class ViewHolder2(itemview: View):RecyclerView.ViewHolder(itemview){
        val icon : ImageView
        val icon_name: TextView
        init{
            icon = itemView.findViewById<ImageView>(R.id.icon2)
            icon_name = itemView.findViewById<TextView>(R.id.icon2_name)
            itemview.setOnClickListener{
                var current = 0
                for (i in 0 until exifName.size){
                    if (exifName.get(i) == icon_name.getText().toString()){
                        current= i
                    }
                }
                val intent = Intent(context2, Specific_info2::class.java)
                intent.putExtra("name", icon_name.getText().toString())
                intent.putExtra("index", current)
                context2.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter2.ViewHolder2 {
        val view = inflater.inflate(R.layout.item_view2, parent, false )
        return ViewHolder2(view)
    }

    override fun onBindViewHolder(holder: Adapter2.ViewHolder2, position: Int) {
        val uri: Uri = Uri.parse(urlList.get(position))
        holder.icon.setImageURI(uri)
        holder.icon_name.setText(exifName.get(position))
    }

    override fun getItemCount(): Int {
        return urlList.size
    }

}