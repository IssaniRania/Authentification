package com.example.authentification

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        val tableLayout = findViewById<TableLayout>(R.id.tablelayout) // Récupération du TableLayout depuis le XML

        val sharedPrefs = getSharedPreferences("MonFichier", Context.MODE_PRIVATE)
        val userCount = sharedPrefs.getInt("userCount", 0)

        for (i in 1..userCount) {
            val cin = sharedPrefs.getString("cin$i", "")
            val nomUtilisateur = sharedPrefs.getString("name$i", "")
            val email = sharedPrefs.getString("email$i", "")

            val row = TableRow(this)

            val cinTextView = TextView(this, null, 0, R.style.MyTextViewStyle)
            cinTextView.text = cin

            val nomTextView = TextView(this, null, 0, R.style.MyTextViewStyle)
            nomTextView.text = nomUtilisateur

            val emailTextView = TextView(this, null, 0, R.style.MyTextViewStyle)
            emailTextView.text = email

            row.addView(cinTextView)
            row.addView(nomTextView)
            row.addView(emailTextView)

            tableLayout.addView(row)
        }
    }
}