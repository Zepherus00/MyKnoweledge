package com.skillbox.lesson2

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.skillbox.lesson2.databinding.MyCustomButtonBinding

class CustomButton
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = MyCustomButtonBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }
}