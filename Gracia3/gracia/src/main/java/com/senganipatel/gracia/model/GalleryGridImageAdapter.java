package com.senganipatel.gracia.model;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.List;

public class GalleryGridImageAdapter extends BaseAdapter {
    private FavoriteUpdateCallBack callBack;
    private List<GalleryItem> galleryItem;
    private GalleryType galleryType;
    private Context mContext;

    public GalleryGridImageAdapter(Context c, GalleryType galleryType, FavoriteUpdateCallBack callBack) {
        this.mContext = c;
        this.galleryItem = GalleryItemProvider.getGalleryItems(c, galleryType);
        this.galleryType = galleryType;
        this.callBack = callBack;
    }

    public int getCount() {
        return this.galleryItem.size();
    }

    public Object getItem(int position) {
        return this.galleryItem.get(position);
    }

    public long getItemId(int position) {
        return (long) ((GalleryItem) this.galleryItem.get(position)).getItemId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        GalleryItem item = (GalleryItem) this.galleryItem.get(position);
        ImageView imageView = new ImageView(this.mContext);
        imageView.setImageResource(item.getTumbnilImageResId());
        imageView.setScaleType(ScaleType.CENTER_CROP);
        imageView.setOnClickListener(new ImageClickListener(this.callBack, this.mContext, this.galleryItem, position, this.galleryType.equals(GalleryType.FAVORITE)));
        float px = TypedValue.applyDimension(1, 100.0f, Resources.getSystem().getDisplayMetrics());
        imageView.setLayoutParams(new LayoutParams((int) px, (int) ((px / 2.0f) + px)));
        return imageView;
    }

    public float convertPixelsToDp(float px) {
        return (float) ((int) (px / Resources.getSystem().getDisplayMetrics().density));
    }
}
