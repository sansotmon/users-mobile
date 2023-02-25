package ssm.android.users_mobile.presenter

import android.content.Context
import com.google.gson.Gson
import ssm.android.users_mobile.model.User

class UserDetailPresenter(val context: Context, val ui: UserDetailUI) {
    private var user: User? = null

    fun showUser(userJson:String){
        user = Gson().fromJson(userJson, User::class.java)
        user?.let {
            ui.showUser(it.name!!, it.email!!, it.phone!!)
        }
    }
}