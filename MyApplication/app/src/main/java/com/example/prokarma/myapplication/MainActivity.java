package com.example.prokarma.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;

import com.example.prokarma.myapplication.widget.slidinglayout.SlidingUpPanelLayout;

public class MainActivity extends AppCompatActivity implements SlidingPanelToggleDelegate, FragmentManagerDelegate,
        SlidingUpPanelLayout.PanelSlideListener {

    public static final float SLIDING_PANEL_ANCHOR_POINT = 0.7F;

    private SlidingUpPanelLayout mSlidingPanel = null;

    private int mActivityContainerId = R.id.mainLayoutContainer;

    private int mSlidingContainerId = R.id.slidingLayoutContainer;

    private Toolbar mContactViewToolBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpSlidingPanel();

        inflateHomeFragment();
    }

    private void setUpSlidingPanel() {
        mSlidingPanel = (SlidingUpPanelLayout) findViewById(R.id.slidingPanel);
        mSlidingPanel.setGravity(Gravity.BOTTOM);
        mSlidingPanel.setPanelHeight(0);
        mSlidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        mSlidingPanel.setAnchorPoint(SLIDING_PANEL_ANCHOR_POINT);
        mSlidingPanel.setShadowHeight((int) toDp(5));
        mSlidingPanel.setParalaxOffset(0);
        mSlidingPanel.setOverlayed(true);

        mSlidingPanel.setMinFlingVelocity((int) toDp(1800));     // Fling velocity should be 600 dps per second

        mSlidingPanel.setPanelSlideListener(this);
    }

    private void inflateHomeFragment() {
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setFragmentManagerDelegate(this);
        homeFragment.setSlidingPanelDelegate(this);
        replaceFragment(getFragmentContainerId(), homeFragment, "HomeFragment");
    }


    private float toDp(int value) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density;
        return (value * density);
    }

    @Override
    public void openPanel() {
        if (mSlidingPanel != null) {
            mSlidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
        }
    }

    @Override
    public void closePanel() {
        if (mSlidingPanel != null) {
            mSlidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        }
    }

    @Override
    public int getSlidingContainedId() {
        return R.id.slidingLayoutContainer;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.mainLayoutContainer;
    }

    @Override
    public Fragment findFragmentByTag(String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        return fragment;
    }

    @Override
    public void replaceFragment(int containerId, Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment, tag);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
        if (mContactViewToolBar != null) {
            mContactViewToolBar.setAlpha(slideOffset);
        }
    }

    @Override
    public void onPanelCollapsed(View panel) {
        setTouchMaskVisible(View.GONE);
    }

    @Override
    public void onPanelExpanded(View panel) {
        setTouchMaskVisible(View.VISIBLE);
    }

    @Override
    public void onPanelAnchored(View panel) {
        setTouchMaskVisible(View.VISIBLE);

        if (mContactViewToolBar == null) {
            ContactViewFragment slidingFragment =
                    (ContactViewFragment) getSupportFragmentManager().findFragmentByTag("ContactViewFragment");
            if (slidingFragment != null) {
                View contentView = slidingFragment.getView();
                mContactViewToolBar = (Toolbar) ((contentView != null) ? contentView.findViewById(
                        R.id.contact_view_toolbar) : null);
            }
        }
    }

    @Override
    public void onPanelHidden(View panel) {
        setTouchMaskVisible(View.GONE);
    }

    /**
     * TouchMaskView is a dummy view which is used to avoid dispatching the User touch to the MainLayoutFragment,
     * when the SlidingPanel is visible (anchored or expanded.). This method will set the visibility of the touch
     * mask view, so that when slidingPanelLayout is visible, the touch mas view is also visible. This is in the case
     * that when SlidingPanelLayout is anchored to half of the screen height, user cannot touch the background
     * shadowed region.
     * @param visibility View.visibility constant.
     */
    private void setTouchMaskVisible(int visibility) {
        findViewById(R.id.maskMainLayoutTouch).setVisibility(visibility);
    }
}