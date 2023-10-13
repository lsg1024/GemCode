package com.example.gemcode.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gemcode.R
import com.example.gemcode.databinding.FragmentOrderDetailBinding
import com.example.gemcode.viewmodel.OrderDetailViewModel

class OrderDetailFragment : Fragment() {

    private var _binding : FragmentOrderDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: OrderDetailViewModel
    private lateinit var modelNumber : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOrderDetailBinding.inflate(inflater, container, false)

        val bundle = arguments
        modelNumber = binding.modelNumberEditText
        if (bundle != null) {
            val modelBundle = bundle.getString("model_number")
            modelNumber.setText(modelBundle)
        }

        return binding.root
    }


}