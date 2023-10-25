package com.example.gemcode.view.create

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gemcode.R
import com.example.gemcode.viewmodel.CreateItemViewModel

class CreateItemFragment : Fragment() {

    companion object {
        fun newInstance() = CreateItemFragment()
    }

    private lateinit var viewModel: CreateItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_item, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateItemViewModel::class.java)
        // TODO: Use the ViewModel
    }

}