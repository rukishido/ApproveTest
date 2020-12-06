package com.example.approvetest.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.approvetest.R
import com.example.approvetest.adapters.TaskListAdapter
import com.example.approvetest.databinding.FragmentTaskListBinding
import com.example.approvetest.databinding.TaskListItemBinding
import com.example.approvetest.model.Task
import com.example.approvetest.ui.AppViewModel
import com.example.approvetest.ui.TaskActivity
import com.example.approvetest.utils.Constants
import com.example.approvetest.utils.Resource


class TaskListFragment : Fragment(R.layout.fragment_task_list) {
    private lateinit var binder:FragmentTaskListBinding
    private lateinit var taskAdapter:TaskListAdapter
    private lateinit var viewModel:AppViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binder = FragmentTaskListBinding.inflate(inflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        viewModel = (activity as TaskActivity).viewModel
        viewModel.tasks.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let{ taskResponse ->
                        taskAdapter.differ.submitList(taskResponse)
                        Log.d("main","${taskAdapter.differ.currentList}")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
                is Resource.Error ->{
                    Log.d("main","Error in TaskListFragment, message : ${response.message}")
                }
            }
        })

    }

    private fun setupRecycler() {
        taskAdapter = TaskListAdapter()
        taskAdapter.onItemClick = {task ->
            val bundle:Bundle = Bundle()
            bundle.putSerializable(Constants.TASK_TAG,task)
            val toFragment = TaskDetailsFragment()
            toFragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.activityFrameLayout,toFragment)
                addToBackStack(null)
                commit()
            }

        }
        with(binder.taskListRecycler){
            this.adapter = taskAdapter
        }
    }
    private fun hideProgressBar(){
        binder.taskListProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar(){
        binder.taskListProgressBar.visibility = View.VISIBLE
    }
}