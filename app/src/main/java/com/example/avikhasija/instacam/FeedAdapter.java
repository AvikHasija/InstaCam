package com.example.avikhasija.instacam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Avik Hasija on 7/28/2015.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView mCaption;
        private final ImageView mPhoto;
        private final ImageView mAvatar;
        private final TextView mUsername;
        public ViewHolder(View itemView) {
            super(itemView);
            mCaption = (TextView)itemView.findViewById(R.id.feed_item_caption);
            mPhoto = (ImageView)itemView.findViewById(R.id.feed_item_photo);
            mAvatar = (ImageView)itemView.findViewById(R.id.feed_item_user_avatar);
            mUsername = (TextView)itemView.findViewById(R.id.feed_item_user_name);
        }
    }

    private List<Photo> mPhotos;
    private Context mContext;

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Photo photo = mPhotos.get(i);
        //fetch user from photo
        User user = photo.getUser();
        Picasso.with(mContext).load(photo.getFile()).into(viewHolder.mPhoto);
        viewHolder.mCaption.setText(photo.getCaption());
        //set name
        viewHolder.mUsername.setText(user.getFirstName() + " " +user.getLastName());

        //add profile picture as photo
        Picasso.with(mContext).load(user.getAvatarURL()).into(viewHolder.mAvatar);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public FeedAdapter(Context context, List<Photo> photos) {
        mContext = context;
        mPhotos = photos;
    }
}
