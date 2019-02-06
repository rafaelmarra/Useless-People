package com.example.rafaelmarra.useless_people.view

import com.example.rafaelmarra.useless_people.model.user.User

interface MainActivityView {

    fun placeUserFragment(user: User)

    fun cantGoFurther()

    fun cantGoBack()

    fun increasePage()

    fun decreasePage()

    fun errorOnRequest()

    fun errorOnNetwork()

    fun isConnected(): Boolean

}