package ssm.android.users_mobile.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ssm.android.users_mobile.model.User
import ssm.android.users_mobile.presenter.UserListPresenter
import kotlinx.android.synthetic.main.row_list_user.view.*
import ssm.android.users_mobile.R.layout.row_list_user

class UserListRecyclerAdapter(val presenter: UserListPresenter): RecyclerView.Adapter<UserListRecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) = with(itemView) {
            user.name?.let {
                nameText.text = it
            }
            user.phone?.let{
                phoneText.text = it
            }
            user.email?.let{
                emailText.text = it
            }
        }
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(row_list_user))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.getUser(position) { user ->
            holder.bind(user)
        }
    }

    override fun getItemCount(): Int = presenter.getTotalUsers()
}