package com.example.gemcode.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gemcode.adapter.StoreAdapter
import com.example.gemcode.databinding.FragmentOrderAccountBinding
import com.example.gemcode.viewmodel.OrderAccountViewModel

class OrderAccountFragment : Fragment() {

    private var _binding : FragmentOrderAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var storeAdapter: StoreAdapter
    private lateinit var viewModel: OrderAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderAccountBinding.inflate(inflater, container, false)

        // ViewModel 초기화
        viewModel = ViewModelProvider(this).get(OrderAccountViewModel::class.java)

        // 화면 호출시 가게 목록 호출 + RecyclerView Adapter 전달

        // 1. 리사이클러뷰 초기화
        val recyclerView : RecyclerView = binding.orderAccountRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // StoreAdapter 초기화
        storeAdapter = StoreAdapter()

        // RecyclerView Adapter 연결
        recyclerView.adapter = storeAdapter

        val storeList = viewModel.getStoreList()

        storeAdapter.submitList(storeList)

        // 검색 기능 + 필터 기능


        // 선택 완료 기능


        return binding.root
    }



}