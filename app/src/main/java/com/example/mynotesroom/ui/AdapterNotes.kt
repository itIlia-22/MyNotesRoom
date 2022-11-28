package com.example.mynotesroom.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesroom.R
import com.example.mynotesroom.model.Notes

class AdapterNotes(
    private var notes: List<Notes>
) : RecyclerView.Adapter<AdapterNotes.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleText: TextView = view.findViewById(R.id.note_title)
        private val dateText: TextView = view.findViewById(R.id.note_date)
        lateinit var notes: Notes
        fun bind(notes: Notes) {
            this.notes = notes
            titleText.text = this.notes.title
            dateText.text = this.notes.date.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_notes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notes = notes[position]
        holder.bind(notes)
    }

    override fun getItemCount(): Int = notes.size
}