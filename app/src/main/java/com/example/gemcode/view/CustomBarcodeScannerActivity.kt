package com.example.gemcode.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gemcode.R
import com.example.gemcode.databinding.ActivityCustomBarcodeScannerBinding
import com.example.gemcode.databinding.ActivityMainBinding
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class CustomBarcodeScannerActivity : AppCompatActivity() {

    private var _binding : ActivityCustomBarcodeScannerBinding? = null
    private val binding get() = _binding!!
    private lateinit var captureManager : CaptureManager
    private lateinit var decoratedBarcodeView: DecoratedBarcodeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCustomBarcodeScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        decoratedBarcodeView = binding.decoratedBarcodeView

        captureManager = CaptureManager(this, decoratedBarcodeView)
        captureManager.initializeFromIntent(intent, savedInstanceState)
        captureManager.setShowMissingCameraPermissionDialog(true, "카메라 권한 요청")
        captureManager.decode() // 바코드 스캔 후 콜백 메서드

    }

    override fun onResume() {
        super.onResume()
        captureManager.onResume()
    }

    override fun onPause() {
        super.onPause()
        captureManager.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        captureManager.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        captureManager.onSaveInstanceState(outState)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        captureManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}