@file:OptIn(DelicateCoroutinesApi::class)

package com.android1500.yard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android1500.yard.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {



    private val binding by lazy {
         ActivityMainBinding.inflate(layoutInflater)
    }
    private val checkForRoot = CheckForRoot(this)
    private val rootItemAdapter = RootItemAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        resetView()

    }

    private fun resetView() {
        rootItemAdapter.clear()
    }
    private fun initView() {
        setSupportActionBar(binding.toolbar)
        binding.run.setOnClickListener { checkForRoot1() }
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = rootItemAdapter
    }

    private fun checkForRoot1() {
        resetView()
        lifecycleScope.launch {
            val results = checkForRoot.invoke()
            animateResults(results)
        }
    }

    private fun animateResults(results: List<RootItemResult>) {
        val multiplier = 10
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                results.forEachIndexed { _, rootItemResult ->
                    for (i in 1..multiplier){
                        delay(50)
                        withContext(Dispatchers.Main){
                            if (i == multiplier) {
                                rootItemAdapter.add(rootItemResult)
                            }
                        }
                    }
                }

            }

        }


    }












}