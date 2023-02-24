package ssm.android.users_mobile.presenter

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import ssm.android.users_mobile.R
import ssm.android.users_mobile.model.User

class UserListPresenter(val context: Context, val ui: UserListUI) : BasePresenter() {

    private var users: List<User> = arrayListOf()

    fun getUsers(){
        ui.showToast(context.getString(R.string.activity_user_list_loading))
        okInteractor.getUsers({responseBody ->
            val json: JsonArray = JsonParser().parse(responseBody).asJsonArray
            if(json.get(1) == null){
                val response = Gson().fromJson(responseBody, User.Users::class.java)
                users = response.users!!
                users.forEach { user ->
                    val obj = User(user.id, user.name, user.username, user.email, user.phone, user.website)
                    ui.showToast(obj.id)
                }
            }else{
                ui.showMessageDialog(json.get(1).toString())
            }
        }, {error->
            error?.let {
                ui.showMessageDialog(it)
            }
        })
    }
}