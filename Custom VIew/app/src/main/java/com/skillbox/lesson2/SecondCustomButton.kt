package com.skillbox.lesson2

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.skillbox.lesson2.databinding.CustomButtonBinding

class SecondCustomButton
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val binding = CustomButtonBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }
}