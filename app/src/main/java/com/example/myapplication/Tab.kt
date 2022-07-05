package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type
import kotlin.math.round

var numbook : ArrayList<String> = ArrayList<String>()
var namebook : ArrayList<String> = ArrayList<String>()
var photobook : ArrayList<Bitmap?> = ArrayList<Bitmap?>()
var emailbook : ArrayList<String> = ArrayList<String>()
var readtime: Int = 0
var addressbook : ArrayList<String> = ArrayList<String>()
var notebook : ArrayList<String> = ArrayList<String>()
var take: String? = "1"
var listOfAllImage : ArrayList<String?> = ArrayList<String?>()
var exifName : ArrayList<String?> = ArrayList<String?>()
var exifDate : ArrayList<String?> = ArrayList<String?>()
var exifGPS : ArrayList<String?> = ArrayList<String?>()
class Tab : AppCompatActivity() {

    val PERMISSIONS_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        val sp1 = getSharedPreferences("userList", AppCompatActivity.MODE_PRIVATE)
        val editor = sp1.edit()
        val gson = GsonBuilder().create()
        val groupListType: Type = object : TypeToken<ArrayList<Diary>>(){}.type
        val temp = sp1.getString("messageList", "none")

        if (temp == "none"){
            Log.d("forch", "none found")
            editor.putString("take", "0")
            take = "0"
        }
        else {
            Log.d("forch", "found")
            take = "1"

            DiaryList = gson.fromJson(temp, groupListType)
        }
        editor.apply()

        val permissioncheck1 = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)
        val permissioncheck2 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_CONTACTS)
        val permissioncheck3 = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
        val permissioncheck4 = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_MEDIA_LOCATION)
        val permissioncheck5 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val permissioncheck6 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        val permissioncheck7 = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)

        if(permissioncheck1 != PackageManager.PERMISSION_GRANTED || permissioncheck2 != PackageManager.PERMISSION_GRANTED
            || permissioncheck3 != PackageManager.PERMISSION_GRANTED || permissioncheck4 != PackageManager.PERMISSION_GRANTED
            || permissioncheck5 != PackageManager.PERMISSION_GRANTED || permissioncheck6 != PackageManager.PERMISSION_GRANTED
            || permissioncheck7 != PackageManager.PERMISSION_GRANTED) {

            var permissions = arrayOf(android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.WRITE_CONTACTS,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.ACCESS_MEDIA_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.INTERNET)

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_CONTACTS) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_MEDIA_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.INTERNET)){

                //Toast.makeText(this,"앱 실행을 위한 권한 설정이 필요합니다", Toast.LENGTH_LONG).show()

                ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_REQUEST_CODE)

            }

            else
                ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_REQUEST_CODE)

        }

            read()
            gallery()


        val tab_layout = findViewById<TabLayout>(R.id.tab_layout)
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.telephone))
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.gallery))
        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.restaurant))

        val pagerAdapter = PagerAdapter(supportFragmentManager, 3)
        val view_pager = findViewById<ViewPager>(R.id.view_pager)
        view_pager.adapter = pagerAdapter
        view_pager.setPageTransformer(true, DepthPageTransformer())

        tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))


    }
    @SuppressLint("NewApi")
    fun gallery() {

        var c : Cursor? = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null)

        c?.let{

            var columnIndexData = c.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

            var i = 0

            while(it.moveToNext()){

                var absolutePathOfImage = c.getString(columnIndexData)

                //imageview에 사진 넣기
                //var uri : Uri = Uri.parse(absolutePathOfImage)
                //imageView.setImageURI(uri)

                listOfAllImage.add(absolutePathOfImage)

                //사진 이름
                var token1 = absolutePathOfImage.split('/')
                var token2 = token1[token1.size - 1].split('.')

                exifName.add(token2[0])

                //사진 정보들
                var exif : ExifInterface = ExifInterface(absolutePathOfImage)
                //날짜
                var dateAttribute = exif.getAttribute(ExifInterface.TAG_DATETIME)
                if(dateAttribute != null){

                    exifDate.add(dateAttribute)

                }else{

                    exifDate.add("Unknown")

                }

                //GPS
                var latiAttribute = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE)
                var latiREFAttribute = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF)
                var longAttribute = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE)
                var longREFAttribute = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF)

                if((latiAttribute != null) && (latiREFAttribute != null) && (longAttribute != null) && (longREFAttribute != null)){

                    var realLatitude : String
                    var realLongtude : String

                    if(latiREFAttribute.equals("N")){

                        realLatitude = convertToDegree(latiAttribute).toString()

                    } else{

                        realLatitude = (0.0 - convertToDegree(latiAttribute)).toString()

                    }

                    if(longREFAttribute.equals("E")){

                        realLongtude = convertToDegree(longAttribute).toString()

                    } else{

                        realLongtude = (0.0 - convertToDegree(longAttribute)).toString()

                    }

                    var gps = realLatitude + ", " + realLongtude

                    exifGPS.add(gps)

                } else{

                    exifGPS.add("Unknown")

                }

            }

            c.close()

        }

    }

    //위도 경도 변환기
    private fun convertToDegree(Data: String): Double {

        var dms = Data.split(',')

        var dmsD = dms[0].split('/')
        var d = dmsD[0].toDouble() / dmsD[1].toDouble()

        var dmsM = dms[1].split('/')
        var m = dmsM[0].toDouble() / dmsM[1].toDouble()

        var dmsS = dms[2].split('/')
        var s = dmsS[0].toDouble() / dmsS[1].toDouble()

        var result = d + m / 60 + s / 3600
        return round(result * 1000) / 1000

    }

    @SuppressLint("Range")
    fun read(){

        var cur : Cursor? = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        if(cur!!.count > 0){

            //For name
            var line1 : String = ""
            //For Number
            var line2 : String = ""

            while(cur.moveToNext()){

                var id = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts._ID))

                var name : String = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                line2 +=  "$name"

                if(("1").equals(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))){

                    var pCur : Cursor? = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id.toString()),null)
                    var i = 0
                    var pCount = pCur!!.count

                    val phoneNum : Array<String?> = arrayOfNulls(pCount)
                    val phoneType : Array<String?> = arrayOfNulls(pCount)

                    while(pCur.moveToNext()){

                        phoneNum[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        line1 += " " + phoneNum[i]
                        phoneType[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE))
                        i++

                    }

                }

                numbook.add(line1)
                line1 = ""
                namebook.add(line2)
                line2 = ""


                var photoid = cur.getInt(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID))
                var bmap = queryContactImage(photoid)
                if (bmap != null) {
                    photobook.add(bmap)
                }else{
                    photobook.add(null)
                }
                bmap = null

                var emailLine : String? = getEmailFrom(id)
                if (emailLine != null) {
                    emailbook.add(emailLine)
                }
                emailLine = ""

                var addressLine : String? = getAddressFrom(id)
                if(addressLine != null) {
                    addressbook.add(addressLine)
                }else{
                    addressbook.add("")
                }
                addressLine = ""

                var noteLine : String? = getNoteFrom(id)
                if(noteLine != null){

                    notebook.add(noteLine)

                }else{
                    notebook.add("")
                }
                noteLine = ""

            }

        }

    }
    @SuppressLint("Range")
    private fun getNoteFrom(id: Int): String? {

        var c : Cursor? = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ? ",
            arrayOf(id.toString(),ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE),null)

        var note : String? = ""

        if(c != null){

            if(c.moveToFirst()){

                note = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE))

            }

            c.close()

        }

        if(note != null){

            return note

        }
        return null

    }

    @SuppressLint("Range")
    private fun getAddressFrom(id: Int): String? {

        var c : Cursor? = contentResolver.query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, null, ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = ? ",
            arrayOf(id.toString()),null)

        var address : String? = ""

        if (c != null) {

            if(c.moveToFirst()){

                address = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS))

            }

            c.close()

        }

        if(address != null){

            return address

        }

        return ""
    }

    @SuppressLint("Range")
    private fun getEmailFrom(id: Int): String? {

        var c : Cursor? = contentResolver.query(
            ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ? ",
            arrayOf(id.toString()),null)

        var email : String? = ""

        if (c != null) {

            if(c.moveToFirst()){

                email = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))

            }

            c.close()

        }


        if(email != null){

            return email

        }

        return " "

    }

    private fun queryContactImage(photoid: Int): Bitmap? {

        var c : Cursor? = contentResolver.query(
            ContactsContract.Data.CONTENT_URI, arrayOf(ContactsContract.CommonDataKinds.Photo.PHOTO),
            ContactsContract.Data._ID + "=?",
            arrayOf(photoid.toString()),null)

        var imageBytes : ByteArray? = null

        if (c != null){

            if (c.moveToFirst()){

                imageBytes = c.getBlob(0)

            }

            c.close()

        }

        if(imageBytes != null) {

            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        }

        return null

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == PERMISSIONS_REQUEST_CODE){

            if(grantResults.size > 0){

                for(grant in grantResults){

                    //if(grant == PackageManager.PERMISSION_DENIED) System.exit(0)

                }

            }
            if(grantResults.size > 0){

                for(grant in grantResults){

                    if(grant == PackageManager.PERMISSION_DENIED){

                        //Toast.makeText(this, "앱 실행을 위한 권한 설정이 필요합니다", Toast.LENGTH_LONG).show()
                        //System.exit(0)

                    } else{

                        //Toast.makeText(this,"엡 실행을 위한 권한이 설정되었습니다", Toast.LENGTH_LONG).show()

                    }

                }

            }
        }
    }

}

class PagerAdapter(
    fragmentManager: FragmentManager,
   val tabCount: Int
): FragmentStatePagerAdapter(fragmentManager){
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {
                return Fragment1()
            }
            1 -> {
                return Fragment2()
            }
            2 -> {
                return Fragment3()
            }
            else -> return Fragment1()
        }
    }
}


class Diary(val name: String, val number: String, val email: String, var pic: Bitmap?, val loc: String, val note: String, val uri: String?){

}


class DepthPageTransformer : ViewPager.PageTransformer {
    private val MIN_SCALE = 0.75f
    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 0 -> { // [-1,0]
                    // Use the default slide transition when moving to the left page
                    alpha = 1f
                    translationX = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                position <= 1 -> { // (0,1]
                    // Fade the page out.
                    alpha = 1 - position

                    // Counteract the default slide transition
                    translationX = pageWidth * -position

                    // Scale the page down (between MIN_SCALE and 1)
                    val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position)))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }
        }
    }
}