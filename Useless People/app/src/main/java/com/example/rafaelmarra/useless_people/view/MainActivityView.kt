package com.example.rafaelmarra.useless_people.view

import android.content.Context
import com.example.rafaelmarra.useless_people.model.user.User

interface MainActivityView {

    fun placeUserFragment(user: User)

    fun cantGoFurther(context: Context)

    fun cantGoBack(context: Context)

    fun increasePage()

    fun decreasePage()
}