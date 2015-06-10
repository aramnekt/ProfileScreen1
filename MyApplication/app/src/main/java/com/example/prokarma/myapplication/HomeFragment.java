package com.example.prokarma.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by prokarma on 4/26/2015.
 */
public class HomeFragment extends Fragment {

    private FragmentManagerDelegate mFragManager = null;

    private SlidingPanelToggleDelegate mSlidingPanelDelegate = null;


    public HomeFragment() {}

    public void setFragmentManagerDelegate(FragmentManagerDelegate delegate) {
        mFragManager = delegate;
    }

    public void setSlidingPanelDelegate(SlidingPanelToggleDelegate delegate) {
        mSlidingPanelDelegate = delegate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button showContact = (Button) view.findViewById(R.id.showContactView);
        showContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactScreen();
            }
        });

        if (mFragManager != null) {
            String tag = "ContactViewFragment";

            Fragment contactViewFragment = mFragManager.findFragmentByTag(tag);
            if (contactViewFragment == null) {
                contactViewFragment = new ContactViewFragment();
            }
            mFragManager.replaceFragment(mFragManager.getSlidingContainedId(), contactViewFragment, tag);
        }
    }

    private void showContactScreen() {
        if (mSlidingPanelDelegate != null) {
            mSlidingPanelDelegate.openPanel();
        }
    }
}
