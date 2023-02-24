package ssm.android.users_mobile.view.activity

import android.os.Bundle
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
    }

    private fun createRecycleAdapter(){
        adapter = UserListRecyclerAdapter(presenter!!)
        userRecycler.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        userRecycler.adapter = adapter
        userRecycler.setHasFixedSize(true)
    }

    override fun refreshRecycler() {
        runOnUiThread {
            adapter?.notifyDataSetChanged()
        }
    }
}