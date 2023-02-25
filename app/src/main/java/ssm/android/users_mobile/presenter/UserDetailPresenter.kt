package ssm.android.users_mobile.presenter

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import ssm.android.users_mobile.R
import ssm.android.users_mobile.model.Post
import ssm.android.users_mobile.model.User

class UserDetailPresenter(private val context: Context, private val ui: UserDetailUI): BasePresenter(){
    private var user: User? = null
    private var posts: ArrayList<Post> = arrayListOf()

    fun showUser(userJson:String){
        user = Gson().fromJson(userJson, User::class.java)
        user?.let {
            ui.showUser(it.name!!, it.email!!, it.phone!!)
            getPostList(it.id)
        }

    }

    private fun getPostList(idUser: String){
        ui.showToast(context.getString(R.string.activity_user_detail_loading))
        okInteractor.getPostsByUser(idUser, {responseBody ->
            val postListDict: JsonArray = JsonParser().parse(responseBody).asJsonArray
            for (postDict in postListDict){
                val post = Gson().fromJson(postDict, Post::class.java)
                posts.add(post)
            }
            user?.posts = posts
            user?.posts?.let {
                if(it.isNotEmpty()){
                    posts = it
                    ui.refreshRecycler()
                }
            }
        }, {error->
            error?.let {
                ui.showMessageDialog(it)
            }
        })
    }

    fun getTotalUsers(): Int = posts.size

    fun getPost(index: Int, data: (post:Post) -> Unit) {
        val post = posts[index]
        data(post)
    }
}