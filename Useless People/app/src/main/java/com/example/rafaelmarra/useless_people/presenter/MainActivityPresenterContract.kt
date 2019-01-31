package com.example.rafaelmarra.useless_people.presenter

interface MainActivityPresenterContract {

    fun goNext(presentId: Int)

    fun goBack(presentId: Int)

    fun onDestroy()

    fun getUserForFragment(id: Int)
}