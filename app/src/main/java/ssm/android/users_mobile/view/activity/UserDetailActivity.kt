package ssm.android.users_mobile.view.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_detail.*
import ssm.android.users_mobile.R.layout.activity_user_detail
import ssm.android.users_mobile.presenter.UserDetailPresenter
import ssm.android.users_mobile.presenter.UserDetailUI
import ssm.android.users_mobile.view.adapter.PostListRecyclerAdapter
class UserDetailActivity: BaseActivity(), UserDetailUI {

    private var presenter: UserDetailPresenter? = null
    private var adapter: PostListRecyclerAdapter? = null
    companion object {
        val USER = "userJson"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_user_detail)

        presenter = UserDetailPresenter(this, this)
        createRecycleAdapter()

        if(intent.hasExtra(USER)){
            val recipeJson = intent.getStringExtra(USER)
            recipeJson?.let {
                presenter?.showUser(it)
            }
        }

    }

    private fun createRecycleAdapter(){
        adapter = PostListRecyclerAdapter(presenter!!)
        postRecycler.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        postRecycler.adapter = adapter
        postRecycler.setHasFixedSize(true)
    }

    override fun refreshRecycler() {
        runOnUiThread {
            adapter?.notifyDataSetChanged()
        }
    }

    override fun showUser(name: String, email: String, phone: String) {
        nameText.text = name
        emailText.text = email
        phoneText.text = phone
    }

}