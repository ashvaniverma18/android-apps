package com.senganipatel.gracia.model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.senganipatel.gracia.R;

public class HomeImageAdapter extends PagerAdapter {
    private Activity activity;
    private int imageResourceId;
    private int[] img;
    private LayoutInflater inflater;
    private int layoutResource;

    public HomeImageAdapter(Activity activity, int[] imageResource, int layoutFile, int imageResoureId) {
        this.activity = activity;
        this.img = imageResource;
        this.layoutResource = layoutFile;
        this.imageResourceId = imageResoureId;
    }

    public int getCount() {
        return this.img.length;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Bitmap largeIcon = BitmapFactory.decodeResource(this.activity.getResources(), this.img[position]);
        DisplayMetrics displayMetrics = this.activity.getResources().getDisplayMetrics();
        Bitmap new_bm = Bitmap.createScaledBitmap(largeIcon, displayMetrics.widthPixels + 100, displayMetrics.heightPixels, true);
        ImageView imageView = new ImageView(this.activity);
        imageView.setBackgroundResource(R.drawable.background);
        imageView.setImageBitmap(new_bm);
        imageView.setScaleType(ScaleType.FIT_XY);
        ((ViewPager) container).addView(imageView);
        return imageView;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
