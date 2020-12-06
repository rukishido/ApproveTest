package com.example.approvetest.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

object DescriptionDeserializer :JsonDeserializer<String> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): String {
        return json?.asJsonObject?.get("description")?.asString!!
    }
}