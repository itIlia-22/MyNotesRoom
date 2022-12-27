package com.example.mynotesroom.controller

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DateFragment : DialogFragment() {
    interface Callbacks {
        fun onDateSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val result: Date = GregorianCalendar(year, month, dayOfMonth).time
            targetFragment.let { fm ->
                (fm as Callbacks).onDateSelected(result)

            }
        }
        val date = arguments?.getSerializable("ARG_DATE") as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialDay,
            initialMonth
        )
    }

    companion object {

        @JvmStatic
        fun newInstance(date: Date): DateFragment {
            val args = Bundle().apply {
                putSerializable("ARG_DATE", date)
            }
            return DateFragment().apply {
                arguments = args
            }

        }
    }
}