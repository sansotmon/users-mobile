package ssm.android.users_mobile.presenter

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import ssm.android.users_mobile.R
import ssm.android.users_mobile.model.User

class UserListPresenter(private val context: Context, private val ui: UserListUI) : BasePresenter() {

    private var users: ArrayList<User> = arrayListOf()
    private var tempUsers: List<User> = arrayListOf()

    fun getUsers(){
        ui.showToast(context.getString(R.string.activity_user_list_loading))
        okInteractor.getUsers({responseBody ->
            val userListDict: JsonArray = JsonParser().parse(responseBody).asJsonArray
            for (userDict in userListDict){
                val user = Gson().fromJson(userDict, User::class.java)
                users.add(user)
            }
            if( users.isNotEmpty()){
                tempUsers = users
                ui.refreshRecycler()
            }
        }, {error->
            error?.let {
                ui.showMessageDialog(it)
            }
        })
    }

    fun getUser(index: Int, data: (user:User) -> Unit) {
        val user = tempUsers[index]
        data(user)
    }

    fun getTotalUsers(): Int = tempUsers.size

    fun findUser(text: String) {
        tempUsers = users.filter { s -> s.name!!.contains(text, ignoreCase = true)}
        if(tempUsers.isEmpty()) ui.showEmptyList(true) else ui.showEmptyList(false)
        ui.refreshRecycler()
    }

    fun actionUser(index: Int) {
        val recipe = tempUsers[index]
        ui.showUser(Gson().toJson(recipe))
    }
}