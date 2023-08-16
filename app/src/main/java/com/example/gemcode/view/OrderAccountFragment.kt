package com.example.gemcode.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gemcode.R
import com.example.gemcode.databinding.FragmentOrderAccountBinding
import com.example.gemcode.viewmodel.OrderAccountViewModel

class OrderAccountFragment : Fragment() {

    private var _binding : FragmentOrderAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: OrderAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderAccountBinding.inflate(inflater, container, false)



        return binding.root
    }

}