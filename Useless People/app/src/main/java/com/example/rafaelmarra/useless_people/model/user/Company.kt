package com.example.rafaelmarra.useless_people.model.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Company(
    @SerializedName("bs")
    val bs: String,
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @SerializedName("name")
    val name: String

) : Serializable