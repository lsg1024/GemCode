package com.example.gemcode.view.order

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gemcode.R
import com.example.gemcode.adapter.StoreAdapter
import com.example.gemcode.databinding.FragmentOrderAccountBinding
import com.example.gemcode.dto.FactoryAndStore
import com.example.gemcode.interFace.TextListener
import com.example.gemcode.viewmodel.OrderAccountViewModel

class OrderAccountFragment : Fragment(){

    private var _binding : FragmentOrderAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var storeAdapter: StoreAdapter
    private lateinit var viewModel: OrderAccountViewModel
    private var selectedPosition = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderAccountBinding.inflate(inflater, container, false)

        // ViewModel 초기화
        viewModel = ViewModelProvider(this)[OrderAccountViewModel::class.java]

        // 화면 호출시 가게 목록 호출 + RecyclerView Adapter 전달

        // 1. 리사이클러뷰 초기화
        val recyclerView : RecyclerView = binding.orderAccountRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // StoreAdapter 초기화
        storeAdapter = StoreAdapter(object : StoreAdapter.OnStoreSelectedListener {
            override fun onStoreSelected(factoryAndStore: FactoryAndStore) {
                binding.orderAccountRegisterBtn.setOnClickListener {
                    if (factoryAndStore != null) {
                        val bundle = Bundle()
                        bundle.putString("selectedStoreName", factoryAndStore.name)

                        // 다른 Fragment로 이동
                    findNavController().navigate(R.id.action_orderAccountFragment_to_orderFragment, bundle)
                    } else {
                        Toast.makeText(context, "가게를 선택해주세요!!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        // RecyclerView Adapter 연결
        recyclerView.adapter = storeAdapter

        val storeList = viewModel.getStoreList()

        storeAdapter.submitOriginalList(storeList)

        // 검색 기능 + 필터 기능
        val searchEditText = binding.orderAccountEditText
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                storeAdapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        // 선택 완료 기능

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}