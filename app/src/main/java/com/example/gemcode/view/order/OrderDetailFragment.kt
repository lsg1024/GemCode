package com.example.gemcode.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gemcode.R
import com.example.gemcode.viewmodel.OrderDetailViewModel

class OrderDetailFragment : Fragment() {

    companion object {
        fun newInstance() = OrderDetailFragment()
    }

    private lateinit var viewModel: OrderDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}