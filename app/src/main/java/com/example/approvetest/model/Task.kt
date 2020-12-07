package com.example.approvetest.model

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.reflect.Type


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
    @JsonAdapter(Task.CustomData.Deserializer::class)
    var customData:CustomData? = null,
    @SerializedName("subtasks_total")
    var subtasksTotal:Int? = null,
    @SerializedName("parent_closed")
    var parentClosed:Boolean = false
) :Serializable {
    data class CustomData(
        @SerializedName("description")
        var description:String? = null
    ) {
        companion object Deserializer : JsonDeserializer<CustomData>{
            override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): CustomData {
                return Gson().fromJson(json?.asString,CustomData::class.java)
            }
        }
    }
}

