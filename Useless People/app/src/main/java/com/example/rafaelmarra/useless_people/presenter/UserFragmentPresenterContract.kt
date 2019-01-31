package com.example.rafaelmarra.useless_people.presenter

import com.example.rafaelmarra.useless_people.model.user.User

interface UserFragmentPresenterContract {

    fun fillTexts(user: User)
}