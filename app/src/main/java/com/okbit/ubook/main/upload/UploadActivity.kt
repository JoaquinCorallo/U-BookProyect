package com.okbit.ubook.main.upload

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.okbit.ubook.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

private var FILE_NAME = "Book" + SimpleDateFormat("yyyyMMdd_HHmmss_",Locale.getDefault()).format(Date())
private const val REQUEST_CODE = 13
private lateinit var photofile: File

class UploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        val staticSpinner = findViewById<View>(R.id.tipoTransaccionSpinner) as Spinner

        // Create an ArrayAdapter using the string array and a default spinner

        // Create an ArrayAdapter using the string array and a default spinner
        val staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.tipoTransaccionArray,
                        android.R.layout.simple_spinner_item)

        // Specify the layout to use when the list of choices appears

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner

        // Apply the adapter to the spinner
        staticSpinner.adapter = staticAdapter

        val staticSpinner2 = findViewById<View>(R.id.condicionSpinner) as Spinner

        // Create an ArrayAdapter using the string array and a default spinner

        // Create an ArrayAdapter using the string array and a default spinner
        val staticAdapter2 = ArrayAdapter
                .createFromResource(this, R.array.condicionArray,
                        android.R.layout.simple_spinner_item)

        // Specify the layout to use when the list of choices appears

        // Specify the layout to use when the list of choices appears
        staticAdapter2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner

        // Apply the adapter to the spinner
        staticSpinner2.adapter = staticAdapter2

        // Button to take pictures
        val button = findViewById<Button>(R.id.agregarFoto)
        button.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photofile = getPhotoFile(FILE_NAME)

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photofile)
            val fileProvider = FileProvider.getUriForFile(this, "com.okbit.ubook.fileprovider", photofile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
            try {
                startActivityForResult(takePictureIntent, REQUEST_CODE)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(applicationContext,"Camera not found",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val takenImage = data?.extras?.get("data") as Bitmap
            val fullImage = BitmapFactory.decodeFile(photofile.absolutePath)
            print(fullImage)
            print(photofile.absolutePath)
            Log.d("Path de la image", photofile.absolutePath)
            var imageView = findViewById<ImageView>(R.id.photo)
            imageView.setImageBitmap(takenImage)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

}