package com.senganipatel.gracia.model;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.senganipatel.gracia.R;
import java.util.List;

public class ImageClickListener implements OnClickListener {
    private FavoriteUpdateCallBack callBack;
    private Context context;
    private List<GalleryItem> galleryItem;
    private LayoutInflater inflater;
    private int initialPos;
    private boolean isFavoriteGallery;

    class FavImageClickListener implements OnClickListener {
        GalleryItem item;

        FavImageClickListener(GalleryItem item) {
            this.item = item;
        }

        public void onClick(View v) {
            String messageToShow = "";
            ImageView view = (ImageView) v;
            if (GalleryItemProvider.isItemFavorite(ImageClickListener.this.context, this.item)) {
                GalleryItemProvider.removeImageFromFavorite(ImageClickListener.this.context, this.item);
                view.setImageDrawable(ImageClickListener.this.context.getResources().getDrawable(R.drawable.fav_plus));
                messageToShow = "Removed From Favorite";
            } else {
                GalleryItemProvider.putImageInFavorite(ImageClickListener.this.context, this.item);
                view.setImageDrawable(ImageClickListener.this.context.getResources().getDrawable(R.drawable.fav_minus));
                messageToShow = "Added to Favorite";
            }
            ImageClickListener.this.callBack.updateUI(ImageClickListener.this.isFavoriteGallery);
            Toast.makeText(ImageClickListener.this.context, messageToShow, Toast.LENGTH_LONG).show();
        }
    }

    private class DialogFullImageAdapter extends PagerAdapter {
        private DialogFullImageAdapter() {
        }

        public int getCount() {
            return ImageClickListener.this.galleryItem.size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View dialogFullImageLayout = ImageClickListener.this.inflater.inflate(R.layout.dialog_full_image, container, false);
            GalleryItem itemToShow = (GalleryItem) ImageClickListener.this.galleryItem.get(position);
            ImageView favImage = (ImageView) dialogFullImageLayout.findViewById(R.id.fav_icon_view);
            if (ImageClickListener.this.isFavoriteGallery || GalleryItemProvider.isItemFavorite(ImageClickListener.this.context, itemToShow)) {
                favImage.setImageDrawable(ImageClickListener.this.context.getResources().getDrawable(R.drawable.fav_minus));
            } else {
                favImage.setImageDrawable(ImageClickListener.this.context.getResources().getDrawable(R.drawable.fav_plus));
            }
            favImage.setOnClickListener(new FavImageClickListener(itemToShow));
            TouchImageView3 imgDisplay = (TouchImageView3) dialogFullImageLayout.findViewById(R.id.fullImageView);
            imgDisplay.setImageDrawable(ImageClickListener.this.context.getResources().getDrawable(itemToShow.getFullImageREsId()));
            imgDisplay.setScaleType(ScaleType.CENTER_INSIDE);
            imgDisplay.resetZoom();
            ((TextView) dialogFullImageLayout.findViewById(R.id.messageView)).setText(itemToShow.getDescription());
            ((ViewPager) container).addView(dialogFullImageLayout);
            return dialogFullImageLayout;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((RelativeLayout) object);
        }
    }

    public ImageClickListener(FavoriteUpdateCallBack callBack, Context context, List<GalleryItem> galleryItem, int initalPosition, boolean isFavoriteGallery) {
        this.galleryItem = galleryItem;
        this.context = context;
        this.initialPos = initalPosition;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.isFavoriteGallery = isFavoriteGallery;
        this.callBack = callBack;
    }

    public void onClick(View v) {
        final Dialog dialog = new Dialog(this.context);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_layout);
        ((ImageButton) dialog.findViewById(R.id.dialogCloseButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ViewPager viewPager = (ViewPager) dialog.findViewById(R.id.pagerDialog);
        viewPager.setAdapter(new DialogFullImageAdapter());
        viewPager.setCurrentItem(this.initialPos, true);
        dialog.show();
    }
}
