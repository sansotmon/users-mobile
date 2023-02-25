package ssm.android.users_mobile.presenter

interface UserDetailUI: BaseUI{
    fun showUser(name:String, email: String, phone: String)
    fun refreshRecycler()
}