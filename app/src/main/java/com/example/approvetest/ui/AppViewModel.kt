package com.example.approvetest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.approvetest.Repository
import com.example.approvetest.model.Task
import com.example.approvetest.model.User
import com.example.approvetest.utils.Resource
import com.google.gson.Gson
import com.google.gson.JsonDeserializer
import kotlinx.coroutines.launch
import retrofit2.Response

class AppViewModel(
    val repository: Repository
) : ViewModel(){
    var user:MutableLiveData<Resource<User>> = MutableLiveData()
    var tasks:MutableLiveData<Resource<ArrayList<Task>>> = MutableLiveData()

    init {
        getUser()
        getTasks()
    }

    fun getUser() = viewModelScope.launch {
        user.postValue(Resource.Loading())
        val response = repository.authUser()
        user.postValue(handleAuthResponse(response))
    }

    fun getTasks() = viewModelScope.launch {
        val response = repository.getTasks()
        tasks.postValue(response.body()?.let { result ->
            Resource.Success(result) })
    }


    private fun handleAuthResponse(response:Response<User>) : Resource<User>{
        if(response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(null,response.message())
    }
}