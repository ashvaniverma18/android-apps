package com.senganipatel.gracia.model;

public interface NavDrawerItem {
    int getId();

    String getLabel();

    String getSuffixForTitle();

    int getType();

    boolean isEnabled();

    boolean updateActionBarTitle();
}
