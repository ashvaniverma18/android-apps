package com.senganipatel.gracia.model;

public class NavMenuSection implements NavDrawerItem {
    public static final int SECTION_TYPE = 0;
    private int id;
    private String label;

    private NavMenuSection() {
    }

    public static NavMenuSection create(int id, String label) {
        NavMenuSection section = new NavMenuSection();
        section.setLabel(label);
        return section;
    }

    public int getType() {
        return 0;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isEnabled() {
        return false;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean updateActionBarTitle() {
        return false;
    }

    public String getSuffixForTitle() {
        return null;
    }
}
