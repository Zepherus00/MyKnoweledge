package com.skillbox.lesson_13

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.gson.Gson
import com.skillbox.lesson_13.databinding.FragmentBlankBinding
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlankFragment : Fragment() {

    private lateinit var bg: FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bg = FragmentBlankBinding.inflate(inflater, container, false)
        return  bg.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitServices.searchImageApi.getCatImageList().enqueue(object :
            Callback<List<CatImageModel>> {
            override fun onResponse(
                call: Call<List<CatImageModel>>,
                response: Response<List<CatImageModel>>
            ) {
                if (response.isSuccessful) {
                    val cat = response.body()?.first() ?: return
                    bg.imageView.load(cat.url)
                }
            }

            override fun onFailure(call: Call<List<CatImageModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}