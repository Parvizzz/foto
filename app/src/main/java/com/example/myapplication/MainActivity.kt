package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    private var imageURI: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button  = findViewById<Button>(R.id.button)
        button.setOnClickListener { requestPermission() }
        imageView = findViewById(R.id.image)

        }
    private fun haveStoragePermission() =  ActivityCompat.checkSelfPermission(
        this, android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
    private fun requestPermission() {
        if (!haveStoragePermission()) {
            val permissions  = arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            ActivityCompat.requestPermissions(this, permissions,100)
            Log.e("if", "requestPermission: ", )
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent,100)
            Log.e("else", "requestPermission: ", )
        }
        Toast.makeText(this, "Salon", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {

            imageURI = data?.data
            imageView.setImageURI(imageURI)
        }
    }
}