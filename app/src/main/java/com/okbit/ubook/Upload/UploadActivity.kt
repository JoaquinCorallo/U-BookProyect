package com.okbit.ubook.Upload

import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.okbit.ubook.R
import com.okbit.ubook.main.MainActivity
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


private var FILE_NAME = "Book" + SimpleDateFormat("yyyyMMdd_HHmmss_", Locale.getDefault()).format(Date())
private const val REQUEST_CODE = 13
private lateinit var photofile: File
var isPhotoTaken = false
var bitmapPhotoTaken = ""

class UploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        // Form values to control
        val alertBookTitle = findViewById<TextInputLayout>(R.id.tituloInput)
        val staticSpinner = findViewById<View>(R.id.tipoTransaccionSpinner) as Spinner
        val staticSpinner2 = findViewById<View>(R.id.condicionSpinner) as Spinner
        val controlLanguage = findViewById<TextInputLayout>(R.id.idiomaInput)
        val controlAutor = findViewById<TextInputLayout>(R.id.autorInput)
        val alertImageView = findViewById<ImageView>(R.id.photo)
        val button = findViewById<Button>(R.id.agregarFoto)

        // Floating Action Button
        val actionButton = findViewById<FloatingActionButton>(R.id.addBook)
        actionButton.setOnClickListener {
            val alertBookThumbnail = (alertImageView.drawable)
            val alertBookTitleText = alertBookTitle.editText?.text.toString()
            val controlLanguageText = controlLanguage.editText?.text.toString()
            val controlAutorText = controlAutor.editText?.text.toString()
            val controlTipoTransaccionText = staticSpinner.selectedItem.toString()
            val controlCondicionText = staticSpinner2.selectedItem.toString()
            AlertDialog.Builder(this)
                    .setTitle(alertBookTitleText)
                    .setMessage("Estas seguro que deseas publicar este libro?")
                    .setIcon(alertBookThumbnail)
                    .setPositiveButton("Si", DialogInterface.OnClickListener { _, _ ->
                        confirm(alertBookTitleText, controlLanguageText, controlAutorText, controlTipoTransaccionText, controlCondicionText, isPhotoTaken
                        )
                    })
                    .setNegativeButton("Volver a editar", null).show()
        }

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
        button.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photofile = getPhotoFile(FILE_NAME)

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photofile)
            val fileProvider = FileProvider.getUriForFile(this, "com.okbit.ubook.fileprovider", photofile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
            try {
                startActivityForResult(takePictureIntent, REQUEST_CODE)
                isPhotoTaken = true
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(applicationContext, "Camera not found", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun confirm(titleText: String, languajeText: String, autorText: String, tipoTransaccion: String, condicion: String, photo: Boolean) {
        if (titleText != "" && languajeText != "" && autorText != "" && tipoTransaccion != "" && condicion != "" && photo) {
            print("subo a la base de datos")
            Toast.makeText(this@UploadActivity, "Libro cargado con exito", Toast.LENGTH_LONG).show()
            Log.d("Imagen en base 64", bitmapPhotoTaken)
            // decodifico base64
            val data = Base64.getDecoder().decode(bitmapPhotoTaken)
            val bitmapDecodedImage: Bitmap
            val opt = BitmapFactory.Options()
            opt.inMutable = true
            bitmapDecodedImage = BitmapFactory.decodeByteArray(data, 0, data.size, opt)
            //fin de la decocdificacion
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this@UploadActivity, "Debes llenar todos los campos obligatorios marcados con *", Toast.LENGTH_LONG).show()
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
            // convert bitmap to base 64
            val baos = ByteArrayOutputStream()
            fullImage.compress(Bitmap.CompressFormat.JPEG, 50, baos)
            val bai = baos.toByteArray()
            val base64Image = Base64.getEncoder().encodeToString(bai)
            bitmapPhotoTaken = base64Image
            Log.d("Path de la image", photofile.absolutePath)
            val imageView = findViewById<ImageView>(R.id.photo)
            imageView.setImageBitmap(takenImage)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}