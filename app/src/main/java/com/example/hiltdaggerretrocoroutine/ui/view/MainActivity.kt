package com.example.hiltdaggerretrocoroutine.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiltdaggerretrocoroutine.R
import com.example.hiltdaggerretrocoroutine.data.model.User
import com.example.hiltdaggerretrocoroutine.databinding.ActivityMainBinding
import com.example.hiltdaggerretrocoroutine.ui.adapter.MainAdapter
import com.example.hiltdaggerretrocoroutine.ui.viewmodel.MainViewModel
import com.example.hiltdaggerretrocoroutine.utiles.Statues
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val mainViewModel:MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
      setupUI()
        setupObserver()
    }
    private fun setupUI(){

        adapter= MainAdapter(arrayListOf())
        binding.recyclerView.adapter=adapter
    }
    private fun setupObserver(){
        mainViewModel.users.observe(this, Observer{
            when(it.status){
                Statues.SUCCESS ->{
                    binding. progressBar.visibility=View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding. recyclerView.visibility=View.VISIBLE
                }
                Statues.LOADING -> {
                    binding. progressBar.visibility=View.VISIBLE
                    binding. recyclerView.visibility=View.GONE
                }
                Statues.ERROR -> {
                    binding. progressBar.visibility=View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }

            }
        })

    }
    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}