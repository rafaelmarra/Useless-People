package com.example.rafaelmarra.useless_people.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.rafaelmarra.useless_people.R
import com.example.rafaelmarra.useless_people.model.user.User
import com.example.rafaelmarra.useless_people.presenter.UserFragmentPresenter
import com.example.rafaelmarra.useless_people.presenter.UserFragmentPresenterContract
import kotlinx.android.synthetic.main.fragment_user.view.*

fun newUserFragmentInstance(user: User): UserFragment {

    val args = Bundle()
    args.putSerializable("user", user)

    val fragment = UserFragment()
    fragment.arguments = args

    return fragment
}

class UserFragment : Fragment(), UserFragmentView {

    private val userFragmentPresenterContract: UserFragmentPresenterContract = UserFragmentPresenter(this)
    private lateinit var fragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentView = inflater.inflate(R.layout.fragment_user, container, false)

        val user = arguments?.getSerializable("user") as User
        userFragmentPresenterContract.fillTexts(user)

        return fragmentView
    }

    override fun fillUserTexts(user: User) {

        fragmentView.name.text = user.name
        fragmentView.email.text = user.email
        fragmentView.phone.text = user.phone
        fragmentView.street.text = user.address.street
        fragmentView.suite.text = user.address.suite
        fragmentView.city.text = user.address.city
        fragmentView.zipcode.text = user.address.zipcode
        fragmentView.website.text = user.website
    }
}