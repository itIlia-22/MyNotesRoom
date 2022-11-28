package com.example.mynotesroom.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.mynotesroom.R
import com.example.mynotesroom.model.Notes


class UserNoteFragment : Fragment() {
    private lateinit var userNotes: Notes
    private lateinit var nTitleEdit: EditText
    private lateinit var nBtnDate: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userNotes = Notes()
        nTitleEdit = view.findViewById(R.id.titleEdit) as EditText
        nBtnDate = view.findViewById(R.id.btnDate) as Button
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_note, container, false)
    }

    companion object {

        fun newInstance() = UserNoteFragment()
    }
}