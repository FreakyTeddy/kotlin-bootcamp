package com.example.lgarbarini.kotlinbootcamp2

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val noteList: MutableList<Note> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { addNewNote() }

        title = "Kotlin notes"

        noteListView.adapter
    }

    private fun addNewNote() {
        startActivityForResult(Intent(this, NewNoteActivity::class.java), 1234)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
            val note = data!!.getParcelableExtra<Note>("new_note")
            noteList.add(note)
            Snackbar.make(coordinator, "Nueva tarea: ${note.name}", Toast.LENGTH_SHORT).show()
        }
    }

    class NoteAdapter(val notes: List<Note>) : RecyclerView.Adapter<NoteViewHolder>() {

        override fun onBindViewHolder(holder: NoteViewHolder?, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NoteViewHolder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemCount(): Int {
            return notes.size
        }

    }

    class NoteViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    /*

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings ->
                return true
            else -> super.onOptionsItemSelected(item)
        }
    }
    */
}
