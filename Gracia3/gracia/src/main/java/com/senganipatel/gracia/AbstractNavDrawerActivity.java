package com.senganipatel.gracia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import com.senganipatel.gracia.model.GalleryItem;
import com.senganipatel.gracia.model.GalleryItemProvider;
import com.senganipatel.gracia.model.NavDrawerActivityConfiguration;
import com.senganipatel.gracia.model.NavDrawerItem;
import com.senganipatel.gracia.model.NavMenuItem;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressLint({"NewApi"})
public abstract class AbstractNavDrawerActivity extends Activity {
    public static final int ABOUT_US_ID = 301;
    public static final int CONTACT_US_ID = 302;
    public static final int DECORATIVE_VENEERS_HEADER_ID = 200;
    public static final int GALLERY_ALL_ID = 201;
    public static final int GALLERY_FAVORITE_ID = 208;
    public static final int GALLERY_LONG_ID = 205;
    public static final int GALLERY_NEW_COLLECTION_ID = 206;
    public static final int GALLERY_NATURAL_ID = 202;
    public static final int GALLERY_ROUGHCUT_ID = 203;
    public static final int GALLERY_SMOKED_ID = 204;
    public static final int HOME_ID = 101;
    public static final int INFORMATION_HEADER_ID = 300;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private android.support.v7.app.ActionBarDrawerToggle mDrawerToggle7;
    private CharSequence mTitle;
    private NavDrawerActivityConfiguration navConf;
    protected ExecutorService pool = Executors.newFixedThreadPool(2);

    private class DrawerItemClickListener implements OnItemClickListener {
        private DrawerItemClickListener() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            AbstractNavDrawerActivity.this.selectItem(position);
        }
    }

    protected abstract NavDrawerActivityConfiguration getNavDrawerConfiguration();

    protected abstract void onNavItemSelected(int i);

    protected void onSaveInstanceState(Bundle outState) {
        outState.putCharSequence("mDrawerTitle", this.mDrawerTitle);
        outState.putCharSequence("mTitle", this.mTitle);
        super.onSaveInstanceState(outState);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.navConf = getNavDrawerConfiguration();
        setContentView(this.navConf.getMainLayout());
        this.mDrawerLayout = (DrawerLayout) findViewById(this.navConf.getDrawerLayoutId());
        this.mDrawerList = (ListView) findViewById(this.navConf.getLeftDrawerId());
        this.mDrawerList.setAdapter(this.navConf.getBaseAdapter());
        this.mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        initDrawerShadow();
        getActionBar().setTitle(getColoredText(getString(R.string.app_name) + getString(R.string.headline)));
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actiobar));
        if (VERSION.SDK_INT >= 14) {
            getActionBar().setHomeButtonEnabled(true);
        }
        if (VERSION.SDK_INT <= 13) {
            this.mDrawerToggle = new ActionBarDrawerToggle(this, this.mDrawerLayout, R.drawable.ic_drawer, this.navConf.getDrawerOpenDesc(), this.navConf.getDrawerCloseDesc()) {
                public void onDrawerClosed(View view) {
                    AbstractNavDrawerActivity.this.getActionBar().setTitle(AbstractNavDrawerActivity.this.getColoredText(AbstractNavDrawerActivity.this.mTitle));
                    AbstractNavDrawerActivity.this.invalidateOptionsMenu();
                }

                public void onDrawerOpened(View drawerView) {
                    AbstractNavDrawerActivity.this.getActionBar().setTitle(AbstractNavDrawerActivity.this.getColoredText(AbstractNavDrawerActivity.this.mDrawerTitle));
                    AbstractNavDrawerActivity.this.invalidateOptionsMenu();
                }
            };
            this.mDrawerLayout.setDrawerListener(this.mDrawerToggle);
            this.mDrawerToggle.setDrawerIndicatorEnabled(true);
        } else {
            this.mDrawerToggle7 = new android.support.v7.app.ActionBarDrawerToggle(this, this.mDrawerLayout, this.navConf.getDrawerOpenDesc(), this.navConf.getDrawerCloseDesc()) {
                public void onDrawerClosed(View view) {
                    AbstractNavDrawerActivity.this.getActionBar().setTitle(AbstractNavDrawerActivity.this.getColoredText(AbstractNavDrawerActivity.this.mTitle));
                    AbstractNavDrawerActivity.this.invalidateOptionsMenu();
                }

                public void onDrawerOpened(View drawerView) {
                    AbstractNavDrawerActivity.this.getActionBar().setTitle(AbstractNavDrawerActivity.this.getColoredText(AbstractNavDrawerActivity.this.mDrawerTitle));
                    AbstractNavDrawerActivity.this.invalidateOptionsMenu();
                }
            };
            this.mDrawerLayout.setDrawerListener(this.mDrawerToggle7);
            this.mDrawerToggle7.setDrawerIndicatorEnabled(true);
        }
        if (savedInstanceState != null) {
            this.mTitle = savedInstanceState.getCharSequence("mTitle");
            this.mDrawerTitle = savedInstanceState.getCharSequence("mDrawerTitle");
            if (this.mDrawerLayout.isDrawerOpen(this.mDrawerList)) {
                setTitle(getColoredText(getString(R.string.app_name) + " " + getString(R.string.headline)));
                return;
            } else {
                setTitle(this.mTitle);
                return;
            }
        }
        CharSequence charSequence = getTitle() + " " + getString(R.string.headline);
        this.mDrawerTitle = charSequence;
        this.mTitle = charSequence;
        this.mDrawerLayout.openDrawer(this.mDrawerList);
    }

    protected void initDrawerShadow() {
        if (this.navConf.getDrawerShadow() != 0) {
            this.mDrawerLayout.setDrawerShadow(this.navConf.getDrawerShadow(), 8388611);
        }
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.syncState();
        } else {
            this.mDrawerToggle7.syncState();
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.onConfigurationChanged(newConfig);
        } else {
            this.mDrawerToggle7.onConfigurationChanged(newConfig);
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.navConf.getActionMenuItemsToHideWhenDrawerOpen() != null) {
            boolean drawerOpen = this.mDrawerLayout.isDrawerOpen(this.mDrawerList);
            for (int iItem : this.navConf.getActionMenuItemsToHideWhenDrawerOpen()) {
                menu.findItem(iItem).setVisible(!drawerOpen);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0){// R.id.action_favourite) {
            selectItem(7);
            return true;
        } else if (this.mDrawerToggle != null && this.mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            if (this.mDrawerToggle7 == null || !this.mDrawerToggle7.onOptionsItemSelected(item)) {
                return false;
            }
            return true;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem favouriteAction = menu.getItem(0);
        if (isFavoriteListEmpty()) {
            favouriteAction.setIcon(R.drawable.fav_icon_emp);
        } else {
            favouriteAction.setIcon(R.drawable.fav_icon);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 82) {
            return super.onKeyDown(keyCode, event);
        }
        if (this.mDrawerLayout.isDrawerOpen(this.mDrawerList)) {
            this.mDrawerLayout.closeDrawer(this.mDrawerList);
        } else {
            this.mDrawerLayout.openDrawer(this.mDrawerList);
        }
        return true;
    }

    protected DrawerLayout getDrawerLayout() {
        return this.mDrawerLayout;
    }

    public void selectItem(int position) {
        NavDrawerItem selectedItem = this.navConf.getNavItems()[position];
        if (selectedItem.getId() == GALLERY_FAVORITE_ID && isFavoriteListEmpty()) {
            Toast.makeText(getApplicationContext(), "No Favorites", Toast.LENGTH_LONG).show();
            return;
        }
        onNavItemSelected(selectedItem.getId());
        this.mDrawerList.setItemChecked(position, true);
        if (selectedItem.updateActionBarTitle()) {
            setTitle(selectedItem.getLabel() + (selectedItem.getSuffixForTitle() == null ? "" : " " + selectedItem.getSuffixForTitle()));
        }
        if (this.mDrawerLayout.isDrawerOpen(this.mDrawerList)) {
            this.mDrawerLayout.closeDrawer(this.mDrawerList);
        }
    }

    public void openDrawer() {
        if (!this.mDrawerLayout.isDrawerOpen(this.mDrawerList)) {
            this.mDrawerLayout.openDrawer(this.mDrawerList);
        }
    }

    public void updateFavoriteIcon() {
        List<GalleryItem> galleryItems = GalleryItemProvider.getFavoriteGalleryItem(getApplicationContext());
        NavMenuItem item = (NavMenuItem) this.navConf.getBaseAdapter().getItem(8);
        String str = (galleryItems == null || galleryItems.isEmpty()) ? "0" : String.valueOf(galleryItems.size());
        item.setCount(str);
        int i = (galleryItems == null || galleryItems.isEmpty()) ? R.drawable.fav_icon_emp : R.drawable.fav_icon;
        item.setIcon(i);
        this.mDrawerList.invalidateViews();
        invalidateOptionsMenu();
    }

    public void setTitle(CharSequence title) {
        this.mTitle = title;
        getActionBar().setTitle(getColoredText(this.mTitle));
    }

    public Spanned getColoredText(CharSequence text) {
        return Html.fromHtml("<font color=\"#D3D3D3\"><b>" + text + "</b></font>");
    }

    public List<GalleryItem> getFavoriteGalleryItem() {
        return GalleryItemProvider.getFavoriteGalleryItem(getApplicationContext());
    }

    public boolean isFavoriteListEmpty() {
        List<GalleryItem> favoriteItemList = getFavoriteGalleryItem();
        return favoriteItemList == null || favoriteItemList.isEmpty();
    }
}
