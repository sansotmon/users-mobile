package ssm.android.users_mobile.view.activity

import android.os.Bundle
import ssm.android.users_mobile.presenter.UserListPresenter
import ssm.android.users_mobile.presenter.UserListUI
import ssm.android.users_mobile.R.layout.activity_user_list

class UserListActivity: BaseActivity(), UserListUI {

    private var presenter: UserListPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_user_list)

        presenter = UserListPresenter(this, this)
    }

}