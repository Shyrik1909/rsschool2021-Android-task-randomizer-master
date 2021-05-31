package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random


class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null
    private var generateValue: Int = 0
    private var listener: FragmentData? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0
        generateValue = generate(min, max)
        result?.text = generateValue.toString()
        arguments?.putInt(PREVIOUS_RESULT_KEY, generateValue)
        backButton?.setOnClickListener {
            listener?.openFirstFragment(generateValue)

        }
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

    private fun generate(min: Int, max: Int): Int {

        var randomNumber: Int = Random.nextInt(min, max)
        Log.d("generate", "$randomNumber")
        return randomNumber
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args


            return fragment
        }

        public const val TAG = "SECOND_FRAGMENT"
        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
        public const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}