package com.example.prokarma.myapplication;

import android.support.v4.app.Fragment;

/**
 * Created by prokarma on 4/26/2015.
 */
public interface FragmentManagerDelegate {

    public int getSlidingContainedId();

    public int getFragmentContainerId();

    public Fragment findFragmentByTag(String tag);

    public void replaceFragment(int containerId, Fragment fragment, String tag);
}
