package com.example.approvetest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.approvetest.R
import com.example.approvetest.databinding.FragmentTaskDetailsBinding
import com.example.approvetest.model.Task
import com.example.approvetest.utils.Constants

class TaskDetailsFragment : Fragment(R.layout.fragment_task_details) {
    private lateinit var binder:FragmentTaskDetailsBinding
   private lateinit var task: Task
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binder = FragmentTaskDetailsBinding.inflate(inflater)
        task = arguments?.getSerializable(Constants.TASK_TAG) as Task
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.apply {
            textDescriptionTextView.text = task.customData
            rewardTextView.text = task.reward.toString()
            taskName.text = task.name
            taskIdTextView.text = task.id.toString()

        }

    }


}