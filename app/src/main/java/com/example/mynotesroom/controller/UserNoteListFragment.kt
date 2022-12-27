package com.example.mynotesroom.controller

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesroom.R
import com.example.mynotesroom.model.Notes
import com.example.mynotesroom.model.viewmodel.ViewModelListNotes
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class UserNoteListFragment : Fragment() {
    private var callbacks: Callbacks? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var aadBtn: FloatingActionButton
    private var adapter: AdapterNotes? = AdapterNotes(emptyList())
    private val viewModel: ViewModelListNotes by lazy {
        val provider = ViewModelProvider(this)[ViewModelListNotes::class.java]
        provider
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview) as RecyclerView
        aadBtn = view.findViewById(R.id.fnbAdd) as FloatingActionButton
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.noteLiveData.observe(
            viewLifecycleOwner,
            Observer { notes ->
                notes?.let {
                    update(notes)
                }
            }
        )
        addNoteClick()

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    interface Callbacks {
        fun onNoteSelected(id: UUID)
    }

    private fun addNoteClick() {
        aadBtn.setOnClickListener {
            val note = Notes()
            viewModel.addNotes(note)
            callbacks?.onNoteSelected(note.id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_note_list, container, false)
    }

    private fun update(note: List<Notes>) {
        adapter = AdapterNotes(note)
        recyclerView.adapter = adapter
    }

    companion object {

        fun newInstance() = UserNoteListFragment()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val titleText: TextView = view.findViewById(R.id.note_title)
        private val dateText: TextView = view.findViewById(R.id.note_date)
        private val delete: Button = view.findViewById(R.id.delete)
        private lateinit var notes: Notes

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(notes: Notes) {
            this.notes = notes
            titleText.text = this.notes.title
            dateText.text = this.notes.date.toString()
            delete.setOnClickListener {
                viewModel.delNotes(notes)
            }
        }

        override fun onClick(v: View?) {
            callbacks?.onNoteSelected(notes.id)

        }

    }

    inner class AdapterNotes(
        private var notes: List<Notes>,

        ) : RecyclerView.Adapter<ViewHolder>() {


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
}