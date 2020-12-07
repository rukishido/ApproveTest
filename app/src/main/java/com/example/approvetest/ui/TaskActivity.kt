package com.example.approvetest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.approvetest.R
import com.example.approvetest.Repository
import com.example.approvetest.databinding.TaskActivityBinding
import com.example.approvetest.ui.fragments.TaskListFragment
import com.example.approvetest.utils.Resource

class TaskActivity : AppCompatActivity() {

    lateinit var viewModel: AppViewModel
    lateinit var binder:TaskActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = TaskActivityBinding.inflate(layoutInflater)
        setContentView(binder.root)
        val taskListFragment = TaskListFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activityFrameLayout,taskListFragment)
            commit()
        }
        val repository = Repository()
        val viewModelProviderFactory = AppViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(AppViewModel::class.java)
        viewModel.user.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    binder.mailTextView.text = response.data?.id.toString()
                    binder.balanceTextView.text = response.data?.balance.toString()
                }
                is Resource.Error -> {
                    Log.d("main", response.message ?: "Ошибка, пусто")
                }
            }
        })
    }
}