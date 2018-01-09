package com.example.lgarbarini.kotlinbootcamp2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lgarbarini.kotlinbootcamp2.api.RestApi
import com.example.lgarbarini.kotlinbootcamp2.api.categories.CategoriesResponseDto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.note_list_item.view.*

class MainActivity : AppCompatActivity() {

    private val noteList: MutableList<Note> = ArrayList()
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { addNewNote() }

        title = "Kotlin notes"

        noteListView.layoutManager = LinearLayoutManager(this)
        noteListView.adapter = NoteAdapter(noteList) {
            startActivity(Intent(this, NoteDetailActivity::class.java).putExtra("note_detail", it))
        }

        loadNotesFromApi()
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

    private fun addNewNote() {
        startActivityForResult(Intent(this, NewNoteActivity::class.java), 1234)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
            val note = data!!.getParcelableExtra<Note>("new_note")
            noteList.add(0, note)
            noteListView.adapter.notifyDataSetChanged()
        }
    }

    class NoteAdapter(private val notes: List<Note>, private val listener: (Note) -> Unit) : RecyclerView.Adapter<NoteViewHolder>() {

        override fun onBindViewHolder(holder: NoteViewHolder?, position: Int) {
            holder?.bind(notes[position], listener)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NoteViewHolder {
            return NoteViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.note_list_item, parent, false))
        }

        override fun getItemCount(): Int {
            return notes.size
        }

    }

    class NoteViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {


        fun bind(note: Note, listener: (Note) -> Unit) = with(itemView) {
            noteTitle.text = note.name
            noteTime.text = note.createdDate
            setOnClickListener { listener(note) }
        }
    }

    private fun loadNotesFromApi() {
        disposable = RestApi().loadNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> showCategoriesAsNotes(result) },
                        { error -> showLoadingError(error.message ?: "unknown") }
                )
    }

    private fun showCategoriesAsNotes(categories: CategoriesResponseDto) {
        categories.children_categories.map { childrenCategoryDto ->
            noteList.add(Note(childrenCategoryDto.name, childrenCategoryDto.id, "Total: ${childrenCategoryDto.total_items_in_this_category}"))
        }

        noteListView.adapter.notifyDataSetChanged()
    }

    private fun showLoadingError(errorMessage: String) {
        Snackbar.make(coordinator, "No se pudo cargar notas desde la red. Error: $errorMessage", Snackbar.LENGTH_LONG).show()
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
