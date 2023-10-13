package com.example.gemcode.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.gemcode.databinding.FragmentOrderFormBinding
import com.example.gemcode.viewmodel.OrderDetailViewModel

class OrderDetailFormFragment : Fragment() {

    private var _binding : FragmentOrderFormBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: OrderDetailViewModel
    private lateinit var modelNumber : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOrderFormBinding.inflate(inflater, container, false)

        val bundle = arguments
        modelNumber = binding.modelNumberEditText
        if (bundle != null) {
            val modelBundle = bundle.getString("model_number")
            modelNumber.setText(modelBundle)
        }

        return binding.root
    }


}