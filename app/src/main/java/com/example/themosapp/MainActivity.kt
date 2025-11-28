package com.example.themosapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent? = result.data
        if (data != null) { // Check that we have data returned
            val Question = data.getStringExtra("Question_key") // 'string1' needs to match the key we used when we put the string in the Intent
            val Reponse = data.getStringExtra("Reponse_key")

            // Log the value of the strings for easier debugging
            Log.i("MainActivity", "Question: $Question")
            Log.i("MainActivity", "Reponse: $Reponse")

            findViewById<TextView>(R.id.Question).text=Question
            findViewById<TextView>(R.id.Reponse).text=Reponse
        } else {
            Log.i("MainActivity", "Returned null data from AddCardActivity")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val QuestionText = findViewById<TextView>(R.id.Question)
        val ReponseText = findViewById<TextView>(R.id.Reponse)
        val icon = findViewById<ImageView>(R.id.add_button)
        icon. setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            resultLauncher.launch(intent)
        }

        QuestionText. setOnClickListener {
            QuestionText.visibility = View.INVISIBLE
            ReponseText.visibility = View.VISIBLE
        }
        ReponseText. setOnClickListener {
            ReponseText.visibility = View.INVISIBLE
            QuestionText.visibility = View.VISIBLE
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}