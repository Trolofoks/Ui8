package com.example.ui8.presentation

import android.text.Editable
import android.text.TextWatcher

abstract class SimpleTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    abstract override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
}