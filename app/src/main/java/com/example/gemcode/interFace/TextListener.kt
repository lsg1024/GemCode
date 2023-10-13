package com.example.gemcode.interFace

import android.widget.Adapter
import android.widget.EditText

interface TextListener {
    fun onTextChange(text: CharSequence, editText: EditText, adapter: Adapter)
}