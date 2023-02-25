package ssm.android.users_mobile.presenter

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import ssm.android.users_mobile.R
import ssm.android.users_mobile.model.Post
import ssm.android.users_mobile.model.User

class UserDetailPresenter(val context: Context, val ui: UserDetailUI): BasePresenter(){
    private var user: User? = null

    fun getPostList(idUser: String){
        ui.showToast(context.getString(R.string.activity_user_detail_loading))
        okInteractor.getPostsByUser(idUser, {responseBody ->
            val postListDict: JsonArray = JsonParser().parse(responseBody).asJsonArray
            for (postDict in postListDict){
                val post = Gson().fromJson(postDict, Post::class.java)
                user?.posts?.add(post)
            }
            user?.posts?.let {
                if( it.isNotEmpty()){

                }
            }
        }, {error->
            error?.let {
                ui.showMessageDialog(it)
            }
        })
    }

    fun showUser(userJson:String){
        user = Gson().fromJson(userJson, User::class.java)
        user?.let {
            ui.showUser(it.name!!, it.email!!, it.phone!!)
        }
    }
}