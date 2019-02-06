package com.example.rafaelmarra.useless_people

import com.example.rafaelmarra.useless_people.model.dao.UserDAO
import com.example.rafaelmarra.useless_people.model.user.Address
import com.example.rafaelmarra.useless_people.model.user.Company
import com.example.rafaelmarra.useless_people.model.user.Geo
import com.example.rafaelmarra.useless_people.model.user.User
import com.example.rafaelmarra.useless_people.presenter.MainActivityPresenter
import com.example.rafaelmarra.useless_people.view.MainActivityView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class MainActivityPresenterTest {

    @Mock
    private lateinit var mainActivityView: MainActivityView

    @Mock
    private lateinit var userDAO: UserDAO

    @Mock
    private lateinit var throwable: Throwable

    private lateinit var mainActivityPresenter: MainActivityPresenter

    private val user = User(
        Address("", Geo("", ""), "", "", ""),
        Company("", "", ""),
        "", 0, "", "", "", ""
    )

    @Before
    fun setupMainActivityPresenter() {
        MockitoAnnotations.initMocks(this)

        mainActivityPresenter = Mockito.spy(MainActivityPresenter(mainActivityView, userDAO))
    }

    @Test
    fun tryToGoToNextWhenLastPage() {
        mainActivityPresenter.goNext(10)

        verify(mainActivityView, times(1)).cantGoFurther()
    }

    @Test
    fun tryToGoToNextWhenNotLastPage() {
        mainActivityPresenter.goNext(9)

        verify(mainActivityView, times(1)).increasePage()
        verify(mainActivityPresenter, times(1)).getUserForFragment(10)
    }

    @Test
    fun tryToGoBackWhenFirstPage() {
        mainActivityPresenter.goBack(1)

        verify(mainActivityView, times(1)).cantGoBack()
    }

    @Test
    fun tryToGoBackWhenNotFirstPage() {
        mainActivityPresenter.goBack(2)

        verify(mainActivityView, times(1)).decreasePage()
        verify(mainActivityPresenter, times(1)).getUserForFragment(1)
    }

    @Test
    fun getUserFromApiAndPlaceIntoFragment() {

        Mockito.`when`(userDAO.getUser(1, mainActivityPresenter)).then { mainActivityPresenter.onSucess(user) }
        Mockito.`when`(mainActivityView.isConnected()).thenReturn(true)

        mainActivityPresenter.getUserForFragment(1)

        verify(userDAO, times(1)).getUser(1, mainActivityPresenter)
        verify(mainActivityView, times(1)).placeUserFragment(user)
    }

    @Test
    fun callApiWithoutConnection(){

        Mockito.`when`(userDAO.getUser(1, mainActivityPresenter)).then { mainActivityPresenter.onSucess(user) }
        Mockito.`when`(mainActivityView.isConnected()).thenReturn(false)

        mainActivityPresenter.getUserForFragment(1)

        verify(mainActivityView, times(1)).errorOnNetwork()
    }

    @Test
    fun callApiWithErrorResponse(){

        Mockito.`when`(userDAO.getUser(1, mainActivityPresenter)).then { mainActivityPresenter.onError(throwable) }
        Mockito.`when`(mainActivityView.isConnected()).thenReturn(true)

        mainActivityPresenter.getUserForFragment(1)

        verify(userDAO, times(1)).getUser(1, mainActivityPresenter)
        verify(mainActivityView, times(1)).errorOnRequest()
    }
}