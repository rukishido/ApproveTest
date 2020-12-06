package com.example.approvetest.model

import com.example.approvetest.utils.DescriptionDeserializer
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//[
//{
//    "id": 0,
//    "name": "Посмотреть видео",
//    "one_time_execution": false,
//    "user_limit_count": 2,
//    "user_limit": 10,
//    "reward": 0.5,
//    "cooldown": 0,
//    "custom_data": "string",
//    "subtasks_total": "3",
//    "parent_closed": true
//}
//]


data class Task(
    @SerializedName("id")
    var id:Int? = null,
    @SerializedName("name")
    var name:String? = null,
    @SerializedName("one_time_execution")
    var oneTimeExecution:Boolean = false,
    @SerializedName("user_limit_count")
    var userLimitCount:Int? = null,
    @SerializedName("user_limit")
    var userLimit:Int? = null,
    @SerializedName("reward")
    var reward:Float? = null,
    @SerializedName("cooldown")
    var cooldown:Int? = null,
    @SerializedName("custom_data")
    var customData:String? = null,
    @SerializedName("subtasks_total")
    var subtasksTotal:Int? = null,
    @SerializedName("parent_closed")
    var parentClosed:Boolean = false
) :Serializable {
    data class CustomData(
        @SerializedName("description")
        var description:String? = null
    )
}

