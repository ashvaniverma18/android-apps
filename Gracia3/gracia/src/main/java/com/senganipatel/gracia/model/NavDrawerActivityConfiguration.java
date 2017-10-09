package com.senganipatel.gracia.model;

import android.widget.BaseAdapter;

public class NavDrawerActivityConfiguration {
    private int[] actionMenuItemsToHideWhenDrawerOpen;
    private BaseAdapter baseAdapter;
    private int drawerCloseDesc;
    private int drawerLayoutId;
    private int drawerOpenDesc;
    private int drawerShadow;
    private int leftDrawerId;
    private int mainLayout;
    private NavDrawerItem[] navItems;

    public int getMainLayout() {
        return this.mainLayout;
    }

    public void setMainLayout(int mainLayout) {
        this.mainLayout = mainLayout;
    }

    public int getDrawerShadow() {
        return this.drawerShadow;
    }

    public void setDrawerShadow(int drawerShadow) {
        this.drawerShadow = drawerShadow;
    }

    public int getDrawerLayoutId() {
        return this.drawerLayoutId;
    }

    public void setDrawerLayoutId(int drawerLayoutId) {
        this.drawerLayoutId = drawerLayoutId;
    }

    public int getLeftDrawerId() {
        return this.leftDrawerId;
    }

    public void setLeftDrawerId(int leftDrawerId) {
        this.leftDrawerId = leftDrawerId;
    }

    public int[] getActionMenuItemsToHideWhenDrawerOpen() {
        return this.actionMenuItemsToHideWhenDrawerOpen;
    }

    public void setActionMenuItemsToHideWhenDrawerOpen(int[] actionMenuItemsToHideWhenDrawerOpen) {
        this.actionMenuItemsToHideWhenDrawerOpen = actionMenuItemsToHideWhenDrawerOpen;
    }

    public NavDrawerItem[] getNavItems() {
        return this.navItems;
    }

    public void setNavItems(NavDrawerItem[] navItems) {
        this.navItems = navItems;
    }

    public int getDrawerOpenDesc() {
        return this.drawerOpenDesc;
    }

    public void setDrawerOpenDesc(int drawerOpenDesc) {
        this.drawerOpenDesc = drawerOpenDesc;
    }

    public int getDrawerCloseDesc() {
        return this.drawerCloseDesc;
    }

    public void setDrawerCloseDesc(int drawerCloseDesc) {
        this.drawerCloseDesc = drawerCloseDesc;
    }

    public BaseAdapter getBaseAdapter() {
        return this.baseAdapter;
    }

    public void setBaseAdapter(BaseAdapter baseAdapter) {
        this.baseAdapter = baseAdapter;
    }
}
