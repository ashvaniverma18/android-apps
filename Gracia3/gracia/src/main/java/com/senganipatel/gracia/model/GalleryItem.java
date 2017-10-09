package com.senganipatel.gracia.model;

public class GalleryItem {
    private String description;
    private int fullImageREsId;
    private GalleryType galleryType;
    private int itemId;
    private int tumbnilImageResId;

    public GalleryItem(String description, int tumbnilImageResId, int fullImageREsId, int itemId, GalleryType galleryType) {
        this.description = description;
        this.tumbnilImageResId = tumbnilImageResId;
        this.fullImageREsId = fullImageREsId;
        this.itemId = itemId;
        this.galleryType = galleryType;
    }

    public String getDescription() {
        return this.description;
    }

    public int getTumbnilImageResId() {
        return this.tumbnilImageResId;
    }

    public int getFullImageREsId() {
        return this.fullImageREsId;
    }

    public int getItemId() {
        return this.itemId;
    }

    public GalleryType getGalleryType() {
        return this.galleryType;
    }
}
