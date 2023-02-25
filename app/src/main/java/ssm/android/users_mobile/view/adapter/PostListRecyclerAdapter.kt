package ssm.android.users_mobile.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_list_post.view.*
import ssm.android.users_mobile.R
import ssm.android.users_mobile.model.Post
import ssm.android.users_mobile.presenter.UserDetailPresenter

class PostListRecyclerAdapter(val presenter: UserDetailPresenter): RecyclerView.Adapter<PostListRecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) = with(itemView) {
            post.title?.let {
                titleText.text = it
            }
            post.body?.let{
                bodyText.text = it
            }
        }
    }

    private fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.row_list_post))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.getPost(position) { post ->
            holder.bind(post)
        }
    }

    override fun getItemCount(): Int = presenter.getTotalUsers()
}