package com.example.rafaelmarra.useless_people.presenter

import com.example.rafaelmarra.useless_people.model.user.User
import com.example.rafaelmarra.useless_people.view.UserFragmentView

class UserFragmentPresenter(private val userFragmentView: UserFragmentView) : UserFragmentPresenterContract {

    override fun fillTexts(user: User) {

        userFragmentView.fillUserTexts(user)
    }
}