package com.liebxsantos.pexelswallpapersapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.liebxsantos.pexelswallpapersapp.R
import com.liebxsantos.pexelswallpapersapp.ui.popularviewmodel.PopularViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: PopularViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            viewModel.popularWallpapers().collect{
                Log.d("PAGINACAO", it.toString())
            }
        }
    }
}