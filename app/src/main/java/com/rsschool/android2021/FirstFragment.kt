package com.rsschool.android2021


import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var listener: FragmentData? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentData) {
            listener = context
        } else {
            throw RuntimeException(
                "$context must implement FragmentData"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"
        val editTextMin = view.findViewById(R.id.min_value) as EditText
        val editTextMax = view.findViewById(R.id.max_value) as EditText



        generateButton?.setOnClickListener {
            var min: Int = 0
            var max: Int = 0


            if (editTextMin.text.isNullOrEmpty()) {
                Toast.makeText(activity, "Invalid number", Toast.LENGTH_LONG).show()
            } else min = editTextMin.text.toString().trim().toInt()

            if (editTextMax.text.isNullOrEmpty()) {
                Toast.makeText(activity, "Invalid number", Toast.LENGTH_LONG).show()
            } else max = editTextMax.text.toString().trim().toInt()

            if (min > max || min <= 0 || max <= 0) {
                Toast.makeText(activity, "Invalid number", Toast.LENGTH_LONG).show()

            } else {
                listener?.openSecondFragment(min, max)
            }


        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        public const val TAG = "FIRST_FRAGMENT"
        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}