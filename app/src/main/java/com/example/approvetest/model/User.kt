package com.example.approvetest.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id:Int? = null,
    @SerializedName("balance")
    var balance:Float? = null,
    @SerializedName("promoCode")
    var promoCode:String? = null,
    @SerializedName("countReferrers")
    var countReferrers:Int? = null,
    @SerializedName("sumRewardsForReferrers")
    var sumRewardsForReferrers:Float? = null
)

