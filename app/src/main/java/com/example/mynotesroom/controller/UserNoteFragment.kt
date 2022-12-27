package com.example.mynotesroom.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mynotesroom.R
import com.example.mynotesroom.model.Notes
import com.example.mynotesroom.model.viewmodel.ViewModelNotes
import com.example.mynotesroom.utils.ID_NOTE
import java.util.*

private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0

class UserNoteFragment : Fragment(), DateFragment.Callbacks {
    private lateinit var notes: Notes
    private lateinit var nTitleEdit: EditText
    private lateinit var nBtnDate: Button
    private val viewModel: ViewModelNotes by lazy {
        val provider = ViewModelProvider(this)[ViewModelNotes::class.java]
        provider
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notes = Notes()
        nTitleEdit = view.findViewById(R.id.titleEdit) as EditText
        nBtnDate = view.findViewById(R.id.btnDate) as Button
        val noteId: UUID = arguments?.getSerializable(ID_NOTE) as UUID
        viewModel.loadNote(noteId)
        viewModel.noteLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { note ->
            note?.let {
                this.notes = note
                update()

            }
        })
        nBtnDate.setOnClickListener {
            DateFragment.newInstance(notes.date).apply {
                setTargetFragment(this@UserNoteFragment, REQUEST_DATE)
                show(this@UserNoteFragment.requireFragmentManager(), DIALOG_DATE)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_note, container, false)
    }

    private fun update() {
        nTitleEdit.setText(notes.title)
        nBtnDate.text = notes.date.toString()

    }

    override fun onStop() {
        super.onStop()
        viewModel.saveNote(notes)
    }

    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                notes.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                // TODO("Not yet implemented")
            }

        }
        nTitleEdit.addTextChangedListener(titleWatcher)
    }


    companion object {

        fun newInstance(id: UUID): UserNoteFragment {
            val arg = Bundle().apply {
                putSerializable(ID_NOTE, id)
            }
            return UserNoteFragment().apply { arguments = arg }
        }
    }

    override fun onDateSelected(date: Date) {
        notes.date = date
        update()
    }
}