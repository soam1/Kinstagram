package com.akashsoam.instagramcloneapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.instagramcloneapp.Model.User
import com.akashsoam.instagramcloneapp.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(
    private var mContext: Context,
    private var mUser: List<User>,
    private var isFragment: Boolean = false
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.user_item_layout, parent, false)
        return UserAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mUser.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mUser[position]
        holder.usernameTextView.text = user.getUsername()
        holder.userfullnameTextView.text = user.getFullname()
        Picasso.get().load(user.getImage()).placeholder(R.drawable.profile).into(holder.userProfileImage)
        holder.userFollowButton.setOnClickListener { }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var usernameTextView: TextView = itemView.findViewById(R.id.user_name_search)
        var userfullnameTextView: TextView = itemView.findViewById(R.id.user_full_name_search)
        var userFollowButton: Button = itemView.findViewById(R.id.follow_btn_search)
        var userProfileImage: CircleImageView =
            itemView.findViewById(R.id.user_profile_image_search)

    }
}