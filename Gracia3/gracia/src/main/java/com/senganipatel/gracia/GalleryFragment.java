package com.senganipatel.gracia;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.senganipatel.gracia.model.FavoriteUpdateCallBack;
import com.senganipatel.gracia.model.GalleryGridImageAdapter;
import com.senganipatel.gracia.model.GalleryType;

public class GalleryFragment extends Fragment {
    private FavoriteUpdateCallBack callBack;
    private GalleryType galleryType;

    public GalleryFragment(){}


    public void setGalleryType(GalleryType galleryType) {
        this.galleryType = galleryType;
    }

    public void setCallBack(FavoriteUpdateCallBack callBack) {
        this.callBack = callBack;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null && this.galleryType == null) {
            this.galleryType = GalleryType.valueOf(savedInstanceState.getString("galleryType"));
        }
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GridView rootView = (GridView) inflater.inflate(R.layout.gallery_grid_layout, container, false);
        rootView.setAdapter(new GalleryGridImageAdapter(getActivity(), this.galleryType, this.callBack));
        return rootView;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("galleryType", this.galleryType.toString());
    }
}
