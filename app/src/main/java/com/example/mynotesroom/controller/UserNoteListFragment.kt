package com.example.mynotesroom.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesroom.R
import com.example.mynotesroom.model.viewmodel.ViewModelListNotes
import com.example.mynotesroom.ui.AdapterNotes


class UserNoteListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var adapter: AdapterNotes? = null
    private val viewModel: ViewModelListNotes by lazy {
        val provider = ViewModelProvider(this)[ViewModelListNotes::class.java]
        provider
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        update()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_note_list, container, false)
    }

    private fun update() {
        val note = viewModel.note
        adapter = AdapterNotes(note)
        recyclerView.adapter = adapter
    }

    companion object {

        fun newInstance() = UserNoteListFragment()
    }
}