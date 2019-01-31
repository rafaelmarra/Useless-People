package com.example.rafaelmarra.useless_people.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.rafaelmarra.useless_people.R
import com.example.rafaelmarra.useless_people.model.dao.UserDAO
import com.example.rafaelmarra.useless_people.model.user.User
import com.example.rafaelmarra.useless_people.presenter.MainActivityPresenter
import com.example.rafaelmarra.useless_people.presenter.MainActivityPresenterContract
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView {

    private var userCounter = 1
    private val fragmentManager = supportFragmentManager
    private var isGoingForward = true
    private val userDAO = UserDAO()
    private val mainActivityPresenterContract: MainActivityPresenterContract = MainActivityPresenter(this, this, userDAO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityPresenterContract.getUserForFragment(userCounter)

        buttonNext.setOnClickListener {
            mainActivityPresenterContract.goNext(userCounter)
        }

        buttonPrevious.setOnClickListener {
            mainActivityPresenterContract.goBack(userCounter)
        }
    }

    override fun placeUserFragment(user: User) {

        val userFragment = newUserFragmentInstance(user)

        if (isGoingForward) {
            fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_left_enter, R.anim.slide_left_exit)
                .replace(container.id, userFragment)
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_right_enter, R.anim.slide_right_exit)
                .replace(container.id, userFragment)
                .commit()
        }
    }

    override fun cantGoFurther(context: Context) {
        Toast.makeText(context, "This is the last page", Toast.LENGTH_SHORT).show()
    }

    override fun cantGoBack(context: Context) {
        Toast.makeText(context, "This is the first page", Toast.LENGTH_SHORT).show()
    }

    override fun increasePage() {
        isGoingForward = true
        userCounter++
        txtUserCounter.text = userCounter.toString()
    }

    override fun decreasePage() {
        isGoingForward = false
        userCounter--
        txtUserCounter.text = userCounter.toString()

    }

    override fun onDestroy() {
        mainActivityPresenterContract.onDestroy()
        super.onDestroy()
    }
}
