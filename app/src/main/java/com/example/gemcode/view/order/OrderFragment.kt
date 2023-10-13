package com.example.gemcode.view.order

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gemcode.R
import com.example.gemcode.databinding.FragmentOrderBinding
import com.example.gemcode.view.CustomBarcodeScannerActivity
import com.example.gemcode.viewmodel.OrderViewModel
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class OrderFragment : Fragment() {

    private var _binding : FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: OrderViewModel
    private lateinit var storeName : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        storeName = binding.storeName

        initViews()

        binding.storeName.setOnClickListener {
            findNavController().navigate(R.id.action_orderFragment_to_orderAccountFragment)
        }
        val bundle = arguments
        if (bundle != null) {
            val storeNameBundle = bundle.getString("selectedStoreName")
            storeName.text = storeNameBundle
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun checkPermissionAndShowActivity(context : Context) {
        if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED) {
            showCamera()
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            showPermissionRationaleDialog(context)
        } else {
            requestCameraPermission()
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                showCamera()
            } else {

                // 권한이 거부된 경우
                val rationaleMessage = "카메라 권한이 필요합니다. 카메라로 바코드를 스캔하기 위해 권한을 승인해주세요."

                // AlertDialog를 사용하여 사용자에게 권한 필요 메시지를 표시
                AlertDialog.Builder(requireContext())
                    .setTitle("권한 필요")
                    .setMessage(rationaleMessage)
                    .setPositiveButton("권한 요청") { dialog, which ->
                        // 권한 요청 다이얼로그를 띄우는 코드
                        requestCameraPermission()
                    }
                    .setNegativeButton("취소") { dialog, which ->
                        // 사용자가 권한을 승인하지 않고 취소한 경우 처리할 로직 추가
                        Toast.makeText(requireContext(), "카메라 권한을 승인해야 합니다.", Toast.LENGTH_LONG).show()
                    }
                    .show()
            }
        }

    private val barcodeCodeLauncher =
        registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
            if (result.contents == null) {
                Toast.makeText(context, "취소", Toast.LENGTH_LONG).show()
            } else {
                val bundle = Bundle()
                bundle.putString("model_number",result.contents)
                findNavController().navigate(R.id.action_orderFragment_to_orderDetailFragment, bundle)
//                setResult(result.contents)
            }
        }

    // 결과 테스트 코드
    private fun setResult(contents: String) {
        binding.textView4.text = contents
    }

    private fun showCamera() {
        val scanOptions = ScanOptions()
        scanOptions.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
        scanOptions.setCameraId(0)
        scanOptions.setBeepEnabled(false)
        scanOptions.setBarcodeImageEnabled(true)
        scanOptions.setOrientationLocked(false)
        scanOptions.captureActivity = CustomBarcodeScannerActivity::class.java
        barcodeCodeLauncher.launch(scanOptions)

    }

    private fun initViews() {
        binding.barcodeScanLayout.setOnClickListener { view: View? -> checkPermissionAndShowActivity(
            requireContext()
        ) }
    }

    private fun showPermissionRationaleDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("권한 필요")
            .setMessage("카메라 권한이 필요합니다. 카메라로 바코드를 스캔하기 위해 권한을 승인해주세요.")
            .setPositiveButton("권한 요청") { _, _ ->
                requestCameraPermission()
            }
            .setNegativeButton("취소") { _, _ ->
                Toast.makeText(context, "카메라 권한을 승인해야 합니다.", Toast.LENGTH_LONG).show()
            }
            .show()
    }

    private fun requestCameraPermission() {
        // 권한 요청 다이얼로그 띄우기
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

}