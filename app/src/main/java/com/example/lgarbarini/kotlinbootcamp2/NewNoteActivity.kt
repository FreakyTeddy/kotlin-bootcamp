package com.example.lgarbarini.kotlinbootcamp2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_note.*
import java.util.*

class NewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        title = "Nueva nota"

        doneButton.setOnClickListener { saveNote() }
    }

    private fun saveNote() {
        val newNote = Note(inputName.text.toString(), inputDetail.text.toString(), Date().toString())
        val resultIntent = Intent()
        resultIntent.putExtra("new_note", newNote)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
