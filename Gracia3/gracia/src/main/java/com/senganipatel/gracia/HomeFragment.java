package com.senganipatel.gracia;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.senganipatel.gracia.model.HomeImageAdapter;
import com.viewpagerindicator.CirclePageIndicator;

public class HomeFragment extends Fragment {
    private HomeImageAdapter galleryAdapter;
    private int[] imageResIDs = new int[]{R.drawable.home_0, R.drawable.home_1, R.drawable.home_2, R.drawable.home_3, R.drawable.home_4};
    private CirclePageIndicator mIndicator;
    private int pageNumber = 0;
    private ViewPager viewPager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        this.viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        this.viewPager.setBackgroundResource(R.drawable.background);
        this.viewPager.setPageMargin((int) TypedValue.applyDimension(1, 5.0f, getResources().getDisplayMetrics()));
        this.galleryAdapter = new HomeImageAdapter(getActivity(), this.imageResIDs, R.layout.layout_fullscreen_image, R.id.imgDisplay);
        this.viewPager.setAdapter(this.galleryAdapter);
        this.mIndicator = (CirclePageIndicator) rootView.findViewById(R.id.indicator);
        this.mIndicator.setViewPager(this.viewPager);
        this.mIndicator.setFillColor(getResources().getColor(R.color.gray_light));
        this.viewPager.setCurrentItem(this.pageNumber);
        return rootView;
    }
}
