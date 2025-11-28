package com.example.themosapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

       val Questionfield = findViewById<EditText>(R.id.Edit_Question)
        val Reponsefield = findViewById<EditText>(R.id.Edit_REponse)
        val save_icon = findViewById<ImageView>(R.id.save_button)
        val cancel_icon = findViewById<ImageView>(R.id.cancel_button)

        cancel_icon.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        save_icon.setOnClickListener {
            val userquestion = Questionfield.text.toString()
            val userreponse = Reponsefield.text.toString()

            val data = Intent() // create a new Intent, this is where we will put our data

            data.putExtra(
                "Question_key",
                userquestion
            ) // puts one string into the Intent, with the key as 'string1'

            data.putExtra(
                "Reponse_key",
                userreponse
            ) // puts another string into the Intent, with the key as 'string2

            setResult(RESULT_OK, data) // set result code and bundle data for response

            finish()

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}