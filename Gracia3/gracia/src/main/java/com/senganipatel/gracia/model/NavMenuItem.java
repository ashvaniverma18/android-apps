package com.senganipatel.gracia.model;

import android.content.Context;

public class NavMenuItem implements NavDrawerItem {
    public static final int ITEM_TYPE = 1;
    private String count;
    private int icon;
    private int id;
    private String label;
    private String suffixForTitle;
    private boolean updateActionBarTitle;

    private NavMenuItem() {
    }

    public static NavMenuItem create(int id, String label, String icon, boolean updateActionBarTitle, String suffixForTitle, String count, Context context) {
        NavMenuItem item = new NavMenuItem();
        item.setId(id);
        item.setLabel(label);
        item.setIcon(context.getResources().getIdentifier(icon, "drawable", context.getPackageName()));
        item.setUpdateActionBarTitle(updateActionBarTitle);
        item.setSuffixForTitle(suffixForTitle);
        item.setCount(count);
        return item;
    }

    public int getType() {
        return 1;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean updateActionBarTitle() {
        return this.updateActionBarTitle;
    }

    public void setUpdateActionBarTitle(boolean updateActionBarTitle) {
        this.updateActionBarTitle = updateActionBarTitle;
    }

    public String getSuffixForTitle() {
        return this.suffixForTitle;
    }

    public void setSuffixForTitle(String suffixForTitle) {
        this.suffixForTitle = suffixForTitle;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
