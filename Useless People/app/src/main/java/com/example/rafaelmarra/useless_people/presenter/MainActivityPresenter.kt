package com.example.rafaelmarra.useless_people.presenter

import com.example.rafaelmarra.useless_people.model.dao.ServiceListener
import com.example.rafaelmarra.useless_people.model.dao.UserDAO
import com.example.rafaelmarra.useless_people.model.user.User
import com.example.rafaelmarra.useless_people.util.EspressoIdlingResource
import com.example.rafaelmarra.useless_people.view.MainActivityView

class MainActivityPresenter
    (private var mainActivityView: MainActivityView?, private val userDAO: UserDAO) :
    ServiceListener, MainActivityPresenterContract {

    override fun goNext(presentId: Int) {

        if (presentId == 10) {
            mainActivityView?.cantGoFurther()
        } else {
            mainActivityView?.increasePage()

            getUserForFragment(presentId + 1)
        }
    }

    override fun goBack(presentId: Int) {

        if (presentId == 1) {
            mainActivityView?.cantGoBack()
        } else {
            mainActivityView?.decreasePage()
            getUserForFragment(presentId - 1)
        }
    }

    override fun onDestroy() {

        mainActivityView = null
    }

    override fun getUserForFragment(id: Int) {

        if (mainActivityView?.isConnected() == true) {

            userDAO.getUser(id, this)

        } else {

            mainActivityView?.errorOnNetwork()
        }
    }

    override fun onSucess(obtained: Any) {

        val user = obtained as User

        mainActivityView?.placeUserFragment(user)
    }

    override fun onError(throwable: Throwable) {

        /*Log.d("CALL", "ERROR")*/

        mainActivityView?.errorOnRequest()
    }
}