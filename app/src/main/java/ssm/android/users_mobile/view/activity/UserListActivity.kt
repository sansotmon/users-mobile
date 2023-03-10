package ssm.android.users_mobile.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_user_list.*
import ssm.android.users_mobile.presenter.UserListPresenter
import ssm.android.users_mobile.presenter.UserListUI
import ssm.android.users_mobile.R.layout.activity_user_list
import ssm.android.users_mobile.view.adapter.UserListRecyclerAdapter


class UserListActivity: BaseActivity(), UserListUI {

    private var presenter: UserListPresenter? = null
    private var adapter: UserListRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_user_list)

        presenter = UserListPresenter(this, this)
        createRecycleAdapter()
        presenter?.getUsers()

        userSearch.setOnQueryTextListener (object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                text?.let {
                    presenter?.findUser(text)
                }
                return false
            }
        })
    }

    private fun createRecycleAdapter(){
        adapter = UserListRecyclerAdapter(presenter!!)
        userRecycler.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        userRecycler.adapter = adapter
        userRecycler.setHasFixedSize(true)
    }

    override fun showEmptyList(show: Boolean) {
        if (show) emptyText.visibility = View.VISIBLE else emptyText.visibility = View.GONE
    }

    override fun refreshRecycler() {
        runOnUiThread {
            adapter?.notifyDataSetChanged()
        }
    }

    override fun showUser(userJson: String) {
        startActivity(createRecipeDetailIntent(userJson))
    }

    private fun createRecipeDetailIntent(recipeJson: String): Intent {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra(UserDetailActivity.USER, recipeJson)
        return intent
    }
}