package com.ibtikar.mvvm_starter_koin_coroutines.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.ActivityContainerBinding

class ContainerActivity : AppCompatActivity() {
    private var _binding: ActivityContainerBinding? = null
    internal val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
