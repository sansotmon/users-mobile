package ssm.android.users_mobile.presenter

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import ssm.android.users_mobile.R
import ssm.android.users_mobile.model.User

class UserListPresenter(val context: Context, val ui: UserListUI) : BasePresenter() {

    private var users: ArrayList<User> = arrayListOf()

    fun getUsers(){
        ui.showToast(context.getString(R.string.activity_user_list_loading))
        okInteractor.getUsers({responseBody ->
            val userListDict: JsonArray = JsonParser().parse(responseBody).asJsonArray
            for (userDict in userListDict){
                val user = Gson().fromJson(userDict, User::class.java)
                users.add(user)
            }
            if( users.isNotEmpty()) ui.refreshRecycler()
        }, {error->
            error?.let {
                ui.showMessageDialog(it)
            }
        })
    }

    fun getUser(index: Int, data: (user:User) -> Unit) {
        val user = users[index]
        data(user)
    }

    fun getTotalUsers(): Int = users.size
}