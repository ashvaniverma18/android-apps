package com.senganipatel.gracia;

import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import com.senganipatel.gracia.model.FavoriteUpdateCallBack;
import com.senganipatel.gracia.model.GalleryItem;
import com.senganipatel.gracia.model.GalleryItemProvider;
import com.senganipatel.gracia.model.GalleryType;
import com.senganipatel.gracia.model.NavDrawerActivityConfiguration;
import com.senganipatel.gracia.model.NavDrawerAdapter;
import com.senganipatel.gracia.model.NavDrawerItem;
import com.senganipatel.gracia.model.NavMenuItem;
import com.senganipatel.gracia.model.NavMenuSection;
import java.util.List;

public class MainActivity extends AbstractNavDrawerActivity {
    private int DEFAULT_DELAY = AbstractNavDrawerActivity.DECORATIVE_VENEERS_HEADER_ID;
    private FavoriteUpdateCallBack callBack;
    private boolean isOnHomeScreen = true;

    public void onBackPressed() {
        if (this.isOnHomeScreen) {
            backButtonHandler();
            return;
        }
        selectItem(0);
        openDrawer();
    }

    public void backButtonHandler() {
        Builder alertDialog = new Builder(this);
        alertDialog.setTitle("Confirmation?");
        alertDialog.setMessage("Are you sure you want to exit Gracia APP?");
        alertDialog.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        alertDialog.setNegativeButton("No", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        }
        this.callBack = new FavoriteUpdateCallBack() {
            public void updateUI(boolean refreshGrid) {
                MainActivity.this.updateFavoriteIcon();
                if (refreshGrid) {
                    MainActivity.this.onNavItemSelected(AbstractNavDrawerActivity.GALLERY_FAVORITE_ID);
                }
            }
        };
    }

    protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
        List<GalleryItem> favoriteList = getFavoriteGalleryItem();
        NavDrawerItem[] menu = new NavDrawerItem[12];
        menu[0] = NavMenuItem.create(AbstractNavDrawerActivity.HOME_ID, "Home", "home", true, null, null, this);
        menu[1] = NavMenuSection.create(AbstractNavDrawerActivity.DECORATIVE_VENEERS_HEADER_ID, "Decorative Veneers");
        menu[2] = NavMenuItem.create(AbstractNavDrawerActivity.GALLERY_ALL_ID, "All", "allv", true, "Veneers", String.valueOf(GalleryItemProvider.getGalleryItems(GalleryType.ALL).size()), this);
        menu[3] = NavMenuItem.create(AbstractNavDrawerActivity.GALLERY_NEW_COLLECTION_ID, "New Collection", "newcollection", true, "Veneers", String.valueOf(GalleryItemProvider.getGalleryItems(GalleryType.NEW_COLLECTION).size()), this);
        menu[4] = NavMenuItem.create(AbstractNavDrawerActivity.GALLERY_NATURAL_ID, "Natural", "natural", true, "Veneers", String.valueOf(GalleryItemProvider.getGalleryItems(GalleryType.NATURAL).size()), this);
        menu[5] = NavMenuItem.create(AbstractNavDrawerActivity.GALLERY_ROUGHCUT_ID, "Rough Cut", "roughcut", true, "Veneers", String.valueOf(GalleryItemProvider.getGalleryItems(GalleryType.ROUGHCUT).size()), this);
        menu[6] = NavMenuItem.create(AbstractNavDrawerActivity.GALLERY_SMOKED_ID, "Smoked", "smoked", true, "Veneers", String.valueOf(GalleryItemProvider.getGalleryItems(GalleryType.SMOKED).size()), this);
        menu[7] = NavMenuItem.create(AbstractNavDrawerActivity.GALLERY_LONG_ID, "Long", "longview", true, "Veneers", String.valueOf(GalleryItemProvider.getGalleryItems(GalleryType.LONG).size()), this);

        String str = "Favorites";
        String str2 = (favoriteList == null || favoriteList.isEmpty()) ? "fav_icon_emp" : "fav_icon";
        String str3 = "Veneers";
        String str4 = (favoriteList == null || favoriteList.isEmpty()) ? "0" : String.valueOf(favoriteList.size());

        menu[8] = NavMenuItem.create(AbstractNavDrawerActivity.GALLERY_FAVORITE_ID, str, str2, true, str3, str4, this);
        menu[9] = NavMenuSection.create(AbstractNavDrawerActivity.INFORMATION_HEADER_ID, "Information");
        menu[10] = NavMenuItem.create(AbstractNavDrawerActivity.ABOUT_US_ID, "About Us", "about_us", true, null, null, this);
        menu[11] = NavMenuItem.create(AbstractNavDrawerActivity.CONTACT_US_ID, "Contact Us", "contact", true, null, null, this);
        NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
        navDrawerActivityConfiguration.setMainLayout(R.layout.activity_main);
        navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
        navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
        navDrawerActivityConfiguration.setNavItems(menu);
        navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
        navDrawerActivityConfiguration.setDrawerCloseDesc(R.string.drawer_close);
        navDrawerActivityConfiguration.setBaseAdapter(new NavDrawerAdapter(this, R.layout.navdrawer_item, menu));
        return navDrawerActivityConfiguration;
    }

    protected void onNavItemSelected(int id) {
        Fragment fragment = null;
        switch (id) {
            case AbstractNavDrawerActivity.HOME_ID /*101*/:
                fragment = new HomeFragment();
                this.DEFAULT_DELAY = 250;
                this.isOnHomeScreen = true;
                break;
            case AbstractNavDrawerActivity.GALLERY_ALL_ID /*201*/:
                fragment = new GalleryFragment();
                ((GalleryFragment)fragment).setGalleryType(GalleryType.ALL);
                ((GalleryFragment)fragment).setCallBack(this.callBack);

                this.DEFAULT_DELAY = AbstractNavDrawerActivity.DECORATIVE_VENEERS_HEADER_ID;
                this.isOnHomeScreen = false;
                break;
            case AbstractNavDrawerActivity.GALLERY_NATURAL_ID /*202*/:
                fragment = new GalleryFragment();
                ((GalleryFragment)fragment).setGalleryType(GalleryType.NATURAL);
                ((GalleryFragment)fragment).setCallBack(this.callBack);

                this.DEFAULT_DELAY = AbstractNavDrawerActivity.DECORATIVE_VENEERS_HEADER_ID;
                this.isOnHomeScreen = false;
                break;
            case AbstractNavDrawerActivity.GALLERY_ROUGHCUT_ID /*203*/:
                fragment = new GalleryFragment();
                ((GalleryFragment)fragment).setGalleryType(GalleryType.ROUGHCUT);
                ((GalleryFragment)fragment).setCallBack(this.callBack);

                this.DEFAULT_DELAY = AbstractNavDrawerActivity.DECORATIVE_VENEERS_HEADER_ID;
                this.isOnHomeScreen = false;
                break;
            case AbstractNavDrawerActivity.GALLERY_SMOKED_ID /*204*/:
                fragment = new GalleryFragment();
                ((GalleryFragment)fragment).setGalleryType(GalleryType.SMOKED);
                ((GalleryFragment)fragment).setCallBack(this.callBack);

                this.DEFAULT_DELAY = AbstractNavDrawerActivity.DECORATIVE_VENEERS_HEADER_ID;
                this.isOnHomeScreen = false;
                break;
            case AbstractNavDrawerActivity.GALLERY_LONG_ID /*205*/:
                fragment = new GalleryFragment();
                ((GalleryFragment)fragment).setGalleryType(GalleryType.LONG);
                ((GalleryFragment)fragment).setCallBack(this.callBack);

                this.DEFAULT_DELAY = AbstractNavDrawerActivity.DECORATIVE_VENEERS_HEADER_ID;
                this.isOnHomeScreen = false;
                break;
            case AbstractNavDrawerActivity.GALLERY_FAVORITE_ID /*208*/:
                fragment = new GalleryFragment();
                ((GalleryFragment)fragment).setGalleryType(GalleryType.FAVORITE);
                ((GalleryFragment)fragment).setCallBack(this.callBack);

                this.DEFAULT_DELAY = AbstractNavDrawerActivity.DECORATIVE_VENEERS_HEADER_ID;
                this.isOnHomeScreen = false;
                break;
            case AbstractNavDrawerActivity.GALLERY_NEW_COLLECTION_ID /*208*/:
                fragment = new GalleryFragment();
                ((GalleryFragment)fragment).setGalleryType(GalleryType.NEW_COLLECTION);
                ((GalleryFragment)fragment).setCallBack(this.callBack);

                this.DEFAULT_DELAY = AbstractNavDrawerActivity.DECORATIVE_VENEERS_HEADER_ID;
                this.isOnHomeScreen = false;
                break;
            case AbstractNavDrawerActivity.ABOUT_US_ID /*301*/:
                fragment = new AboutUsFragment();
                this.DEFAULT_DELAY = AbstractNavDrawerActivity.DECORATIVE_VENEERS_HEADER_ID;
                this.isOnHomeScreen = false;
                break;
            case AbstractNavDrawerActivity.CONTACT_US_ID /*302*/:
                fragment = new ContactUsFragment();
                this.DEFAULT_DELAY = AbstractNavDrawerActivity.DECORATIVE_VENEERS_HEADER_ID;
                this.isOnHomeScreen = false;
                break;
        }
        if (fragment != null) {
            commitFragment(fragment);
        }
    }

    protected void commitFragment(final Fragment fragment) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                MainActivity.this.getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
            }
        }, (long) this.DEFAULT_DELAY);
    }
}
