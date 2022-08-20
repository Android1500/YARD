@file:OptIn(DelicateCoroutinesApi::class)

package com.android1500.yard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android1500.yard.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.scottyab.rootbeer.RootBeer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {



    private val binding by lazy {
         ActivityMainBinding.inflate(layoutInflater)
    }
    private val checkForRoot = CheckForRoot(this)
    private val rootItemAdapter = RootItemAdapter()
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
        val isRooted = results.any { it.result }
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                results.forEachIndexed { index, rootItemResult ->
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