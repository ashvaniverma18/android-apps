package com.senganipatel.gracia.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.senganipatel.gracia.R;

public class NavDrawerAdapter extends ArrayAdapter<NavDrawerItem> {
    private LayoutInflater inflater;

    private static class NavMenuItemHolder {
        private TextView countView;
        private ImageView iconView;
        private TextView labelView;

        private NavMenuItemHolder() {
        }
    }

    private class NavMenuSectionHolder {
        private TextView labelView;

        private NavMenuSectionHolder() {
        }
    }

    public NavDrawerAdapter(Context context, int textViewResourceId, NavDrawerItem[] objects) {
        super(context, textViewResourceId, objects);
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        NavDrawerItem menuItem = (NavDrawerItem) getItem(position);
        if (menuItem.getType() == 1) {
            return getItemView(convertView, parent, menuItem);
        }
        return getSectionView(convertView, parent, menuItem);
    }

    public View getItemView(View convertView, ViewGroup parentView, NavDrawerItem navDrawerItem) {
        NavMenuItem menuItem = (NavMenuItem) navDrawerItem;
        NavMenuItemHolder navMenuItemHolder = null;
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.navdrawer_item, parentView, false);
            TextView labelView = (TextView) convertView.findViewById(R.id.navmenuitem_label);
            ImageView iconView = (ImageView) convertView.findViewById(R.id.navmenuitem_icon);
            TextView countView = (TextView) convertView.findViewById(R.id.navmenuitem_count);
            navMenuItemHolder = new NavMenuItemHolder();
            navMenuItemHolder.labelView = labelView;
            navMenuItemHolder.iconView = iconView;
            navMenuItemHolder.countView = countView;
            convertView.setTag(navMenuItemHolder);
        }
        if (navMenuItemHolder == null) {
            navMenuItemHolder = (NavMenuItemHolder) convertView.getTag();
        }
        navMenuItemHolder.labelView.setText(menuItem.getLabel());
        navMenuItemHolder.iconView.setImageResource(menuItem.getIcon());
        if (menuItem.getCount() == null) {
            navMenuItemHolder.countView.setVisibility(8);
        } else {
            navMenuItemHolder.countView.setVisibility(0);
            navMenuItemHolder.countView.setText(menuItem.getCount());
        }
        return convertView;
    }

    public View getSectionView(View convertView, ViewGroup parentView, NavDrawerItem navDrawerItem) {
        NavMenuSection menuSection = (NavMenuSection) navDrawerItem;
        NavMenuSectionHolder navMenuSectionHolder = null;
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.navdrawer_section, parentView, false);
            TextView labelView = (TextView) convertView.findViewById(R.id.navmenusection_label);
            navMenuSectionHolder = new NavMenuSectionHolder();
            navMenuSectionHolder.labelView = labelView;
            convertView.setTag(navMenuSectionHolder);
        }
        if (navMenuSectionHolder == null) {
            navMenuSectionHolder = (NavMenuSectionHolder) convertView.getTag();
        }
        navMenuSectionHolder.labelView.setText(menuSection.getLabel());
        return convertView;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int position) {
        return ((NavDrawerItem) getItem(position)).getType();
    }

    public boolean isEnabled(int position) {
        return ((NavDrawerItem) getItem(position)).isEnabled();
    }
}
