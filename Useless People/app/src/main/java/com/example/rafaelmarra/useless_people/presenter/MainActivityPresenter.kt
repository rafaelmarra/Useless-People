package com.example.rafaelmarra.useless_people.presenter

import android.content.Context
import android.util.Log
import com.example.rafaelmarra.useless_people.model.dao.ServiceListener
import com.example.rafaelmarra.useless_people.model.dao.UserDAO
import com.example.rafaelmarra.useless_people.model.user.User
import com.example.rafaelmarra.useless_people.view.MainActivityView

class MainActivityPresenter (private var mainActivityView: MainActivityView?, private val context: Context): ServiceListener{

    private val userDAO = UserDAO()

    fun goNext(presentId: Int){

        if (presentId == 10){
            mainActivityView?.cantGoFurther(context)
        }else{
            getUserForFragment(presentId+1)
            mainActivityView?.increasePage()
        }
    }

    fun goBack(presentId: Int){

        if (presentId == 1){
            mainActivityView?.cantGoBack(context)
        }else{
            getUserForFragment(presentId-1)
            mainActivityView?.decreasePage()
        }
    }

    fun onDestroy(){

        mainActivityView = null
    }

    fun getUserForFragment(id: Int){

        userDAO.getUser(context, id, this)
    }

    override fun onSucess(obtained: Any) {

        val user = obtained as User

        mainActivityView?.placeUserFragment(user)
    }

    override fun onError(throwable: Throwable) {

        Log.d("CALL", "ERROR")
    }
}