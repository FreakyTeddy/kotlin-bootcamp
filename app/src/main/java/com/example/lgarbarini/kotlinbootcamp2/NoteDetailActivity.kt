package com.example.lgarbarini.kotlinbootcamp2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note_detail.*

class NoteDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        val note = intent.getParcelableExtra<Note>("note_detail")
        title = note.name
        noteDetail.text = note.detail
        noteTime.text = note.createdDate
    }
}
