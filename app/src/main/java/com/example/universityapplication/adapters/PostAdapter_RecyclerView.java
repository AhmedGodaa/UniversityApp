package com.example.universityapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universityapplication.R;
import com.example.universityapplication.models.Post;
import com.example.universityapplication.models.Teacher;

import java.util.ArrayList;

public class PostAdapter_RecyclerView extends RecyclerView.Adapter<PostAdapter_RecyclerView.PostHolder>  implements  RecyclerViewOnClickListener{
        ArrayList<Post> data ;
        RecyclerViewOnClickListener listener;


        public PostAdapter_RecyclerView(ArrayList<Post> data) {
                this.data = data;
                this.listener =listener;

        }


        @NonNull

        @Override
        public PostHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout,null,false);
                PostHolder viewholder = new PostHolder(view);
                return viewholder;
        }

        @Override
        public void onBindViewHolder(@NonNull  PostAdapter_RecyclerView.PostHolder holder, int position) {
                Post post = data.get(position);
                holder.post_image.setImageResource(post.getPost_image());
                holder.personal_image.setImageResource(post.getPersonal_image());
                holder.written_content.setText(post.getWritten_content());


        }

        @Override
        public int getItemCount() {
                return data.size();
        }

        @Override
        public void OnClick(Teacher teacher) {

        }

        class PostHolder extends RecyclerView.ViewHolder {
                ImageView personal_image;
                ImageView post_image ;
                Teacher teacher;
                TextView written_content;

                public PostHolder(@NonNull  View itemView) {
                        super(itemView);
                        post_image = itemView.findViewById(R.id.post_iv_post_image);
                        personal_image = itemView.findViewById(R.id.post_iv_personal_photo);
                        written_content = itemView.findViewById(R.id.post_tv_written_content);
                        itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        listener.OnClick(teacher);

                                }
                        });
                }
        }
}
