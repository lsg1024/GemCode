package com.example.gemcode.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gemcode.R
import com.example.gemcode.databinding.FragmentOrderBinding
import com.example.gemcode.viewmodel.OrderViewModel

class OrderFragment : Fragment() {

    private var _binding : FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}