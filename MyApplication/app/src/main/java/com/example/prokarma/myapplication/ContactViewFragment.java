package com.example.prokarma.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by prokarma on 4/26/2015.
 */
public class ContactViewFragment extends Fragment implements Toolbar.OnMenuItemClickListener {

    private Toolbar mToolBar = null;

    private RecyclerView mFaceBookPhotoGallery = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_contact_view, container, false);
        return inflater.inflate(R.layout.fragment_contact_view_new, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setUpToolBar(view);
        //setUpFacebookPhotoGallery(view);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.cv_collapsingToolBar);
        collapsingToolbar.setTitle("this is contact");
    }

    private void setUpToolBar(View contentView) {
        mToolBar = (Toolbar) contentView.findViewById(R.id.contact_view_toolbar);
        mToolBar.setTitle("Contact Name");
        mToolBar.setAlpha(MainActivity.SLIDING_PANEL_ANCHOR_POINT);
        mToolBar.inflateMenu(R.menu.menu_contact_view);
        mToolBar.setOnMenuItemClickListener(this);
    }

    private void setUpFacebookPhotoGallery(View contentView) {
        LinearLayoutManager horizontalListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);

        mFaceBookPhotoGallery = (RecyclerView) contentView.findViewById(R.id.facebook_rv_photoGallery);
        mFaceBookPhotoGallery.setHasFixedSize(true);
        mFaceBookPhotoGallery.setLayoutManager(horizontalListManager);
        mFaceBookPhotoGallery.setAdapter(mFacebookGalleryImageAdapter);
        mFaceBookPhotoGallery.getItemAnimator().setSupportsChangeAnimations(true);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int menuId = item.getItemId();

        switch (menuId) {
            case R.id.action_contact_view_follow:
                Log.d("ContactViewFragment", "follow");
                return true;

            case R.id.action_contact_view_settings:
                Log.d("ContactViewFragment", "settings");
                return true;

            default:
                return false;
        }
    }

    private class FacebookGalleryPhotoViewHolder extends RecyclerView.ViewHolder {

        ImageView mPhoto = null;

        public FacebookGalleryPhotoViewHolder(View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.facebook_gallery_iv_photo);
        }

        public ImageView getPhoto() {
            return mPhoto;
        }
    }

    private RecyclerView.Adapter mFacebookGalleryImageAdapter = new RecyclerView.Adapter<FacebookGalleryPhotoViewHolder>() {
        @Override
        public FacebookGalleryPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.facebook_gallery_imageview,
                    parent, false);
            FacebookGalleryPhotoViewHolder viewHolder = new FacebookGalleryPhotoViewHolder(contentView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(FacebookGalleryPhotoViewHolder holder, int position) {
            // holder.mPhoto.setImageDrawable(getResources().getDrawable(R.drawable.ic_contacts_default));
            Picasso.with(getActivity()).load("http://developer.android.com/assets/images/android_logo.png").into(
                    holder.mPhoto);
        }

        @Override
        public int getItemCount() {
            return 8;
        }
    };

}


