package com.bignerdranch.android.prak22

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_DATE = "date"
class DatePickerFragment : DialogFragment() {
    interface Callbacks {
        fun onDateSelected(date: Date)
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val dateListener =
            DatePickerDialog.OnDateSetListener {
                    _: DatePicker, year: Int,
                    month: Int, day: Int ->
                val resultDate : Date =
                    GregorianCalendar(year, month, day,hour,min).time
                targetFragment?.let { fragment ->
                    (fragment as Callbacks).onDateSelected(resultDate)
                }
            }

        val date = arguments?.getSerializable(ARG_DATE) as Date
        calendar.time = date
        val initialYear =
            calendar.get(Calendar.YEAR)
        val initialMonth =
            calendar.get(Calendar.MONTH)
        val initialDay =
            calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )

    }
    companion object {
        fun newInstance(date: Date):
                DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }
            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }

}