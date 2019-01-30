package com.example.rafaelmarra.useless_people.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.rafaelmarra.useless_people.R
import com.example.rafaelmarra.useless_people.model.user.User
import com.example.rafaelmarra.useless_people.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView {

    private val mainActivityPresenter = MainActivityPresenter(this, this)
    private var userCounter = 1
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityPresenter.getUserForFragment(userCounter)

        buttonNext.setOnClickListener {
            mainActivityPresenter.goNext(userCounter)
        }

        buttonPrevious.setOnClickListener {
            mainActivityPresenter.goBack(userCounter)
        }
    }

    override fun placeUserFragment(user: User) {

        val userFragment = newUserFragmentInstance(user)

        fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_left_enter, R.anim.slide_left_exit)
            .replace(container.id, userFragment)
            .commit()

    }

    override fun cantGoFurther(context: Context) {
        Toast.makeText(context, "This is the last page", Toast.LENGTH_SHORT).show()
    }

    override fun cantGoBack(context: Context) {
        Toast.makeText(context, "This is the first page", Toast.LENGTH_SHORT).show()
    }

    override fun increasePage() {
        userCounter++
        txtUserCounter.text = userCounter.toString()
    }

    override fun decreasePage() {
        userCounter--
        txtUserCounter.text = userCounter.toString()

    }

    override fun onDestroy() {
        mainActivityPresenter.onDestroy()
        super.onDestroy()
    }
}
