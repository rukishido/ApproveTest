package com.example.approvetest.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.approvetest.R
import com.example.approvetest.databinding.TaskListItemBinding
import com.example.approvetest.model.Task

class TaskListAdapter() : RecyclerView.Adapter<TaskListAdapter.TaskItemViewHolder>() {

    inner class TaskItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = TaskListItemBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(differ.currentList[adapterPosition])
            }
        }

        fun bind(currentTask:Task){
            Log.d("main","bind from adapter")
            with(binding){
                taskIdTextView.text = currentTask.id.toString()
                taskName.text = currentTask.name
                rewardTextView.text = currentTask.reward.toString()

            }
        }
    }
    var onItemClick:((Task) -> Unit)? = null
    private val differCallback = object : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        Log.d("main","onCreateViewHolder")
        return TaskItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_list_item,parent,false))
    }

    override fun getItemCount(): Int {
        Log.d("main","getItemCount")
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
       holder.bind(differ.currentList[position])
    }
}