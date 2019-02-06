package com.example.rafaelmarra.useless_people

import com.example.rafaelmarra.useless_people.model.user.Address
import com.example.rafaelmarra.useless_people.model.user.Company
import com.example.rafaelmarra.useless_people.model.user.Geo
import com.example.rafaelmarra.useless_people.model.user.User
import com.example.rafaelmarra.useless_people.presenter.UserFragmentPresenter
import com.example.rafaelmarra.useless_people.view.UserFragmentView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class UserFragmentPresenterTest {

    @Mock
    private lateinit var userFragmentView: UserFragmentView

    private lateinit var userFragmentPresenter: UserFragmentPresenter

    private val user = User(
        Address("", Geo("", ""), "", "", ""),
        Company("", "", ""),
        "", 0, "", "", "", ""
    )

    @Before
    fun setupMainActivityPresenter() {
        MockitoAnnotations.initMocks(this)

        userFragmentPresenter = UserFragmentPresenter(userFragmentView)
    }

    @Test
    fun fillTextsTest(){
        userFragmentPresenter.fillTexts(user)

        verify(userFragmentView, times(1)).fillUserTexts(user)
    }

}