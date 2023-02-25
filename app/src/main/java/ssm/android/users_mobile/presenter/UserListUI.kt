package ssm.android.users_mobile.presenter

interface UserListUI: BaseUI {
    fun showUser(userJson: String)
    fun refreshRecycler()
    fun showEmptyList(show: Boolean)
}