package com.okbit.ubook.main.upload

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.okbit.ubook.R


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
    }
}