package com.example.kobers.activities.stock

import android.graphics.ColorSpace
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kobers.R
import com.example.kobers.utils.Stock
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_stock.*

class InputStockActivity : AppCompatActivity() {

    private var photoUrl = ""
    private var imagesUri: Uri? = null

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_stock)

        ref = FirebaseDatabase.getInstance().getReference("stock")

        img_barang.setOnClickListener {
            UploadItem()
        }

    }

    private fun UploadItem() {

        val nama_barang = nama_barang.text.toString()
        val desk_barang = desk_barang.text.toString()
        val kuantitas_barang = kuantitas.text
        val harga_barang = harga.text.toString()
        val userID = FirebaseAuth.getInstance().currentUser?.uid!!.toInt()

        val barang = Stock(desk_barang, nama_barang, null, harga_barang, null, userID )

//        val ref = FirebaseDatabase.getInstance().reference.child("stock")
//        val itemMap = HashMap<String, Any>()
//        itemMap ["description"] = desk_barang.text.toString()
//        itemMap ["name"] = nama_barang.text.toString()
//        itemMap ["photo"] = photoUrl
//        itemMap ["prize"] = harga.text.toString()
//        itemMap ["quantity"] = kuantitas
//        ref.push().setValue(itemMap)
//        desk_barang.text.clear()
//        nama_barang.text.clear()
//        harga.text.clear()
//        kuantitas.text.clear()
    }
}