package com.example.rafaelmarra.useless_people

import android.content.Context
import com.example.rafaelmarra.useless_people.model.dao.ServiceListener
import com.example.rafaelmarra.useless_people.model.dao.UserDAO
import com.example.rafaelmarra.useless_people.model.user.Address
import com.example.rafaelmarra.useless_people.model.user.Company
import com.example.rafaelmarra.useless_people.model.user.Geo
import com.example.rafaelmarra.useless_people.model.user.User
import com.example.rafaelmarra.useless_people.presenter.MainActivityPresenter
import com.example.rafaelmarra.useless_people.view.MainActivityView
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class MainActivityPresenterTest {

    @Mock
    private lateinit var mainActivityView: MainActivityView

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var userDAO: UserDAO

    @Captor
    private lateinit var userCallbackCaptor: ArgumentCaptor<ServiceListener>

    private lateinit var mainActivityPresenter: MainActivityPresenter

    val USER = User(
        Address("", Geo("", ""), "", "", ""),
        Company("", "", ""),
        "", 0, "", "", "", "")

    @Before
    fun setupMainActivityPresenter(){
        MockitoAnnotations.initMocks(this)

        mainActivityPresenter = MainActivityPresenter(mainActivityView, context, userDAO)
    }

    @Test
    fun tryToGoToNextWhenLastPage(){
        mainActivityPresenter.goNext(10)

        verify(mainActivityView).cantGoFurther(context)
    }

    @Test
    fun tryToGoToNextWhenNotLastPage(){
        mainActivityPresenter.goNext(9)

        verify(mainActivityView).increasePage()
    }

    @Test
    fun getUserFromApiAndPlaceIntoFragment(){
        mainActivityPresenter.getUserForFragment(1)

        verify(userDAO).getUser(context, 1, userCallbackCaptor.capture())
        userCallbackCaptor.value.onSucess(USER)

        verify(mainActivityView).placeUserFragment(USER)
    }



}