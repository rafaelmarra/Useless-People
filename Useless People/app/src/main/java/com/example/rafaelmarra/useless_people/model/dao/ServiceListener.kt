package com.example.rafaelmarra.useless_people.model.dao

interface ServiceListener {

    fun onSucess(obtained: Any)

    fun onError(throwable: Throwable)
}