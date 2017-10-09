package com.senganipatel.gracia.model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;

import com.senganipatel.gracia.R;

public class GalleryItemProvider {

    private static List<GalleryItem> longGalleryItems = new ArrayList<GalleryItem>();
    private static List<GalleryItem> naturalGalleryItems = new ArrayList<GalleryItem>();
    private static List<GalleryItem> roughGalleryItems = new ArrayList<GalleryItem>();
    private static List<GalleryItem> smokedGalleryItems = new ArrayList<GalleryItem>(25);
    private static List<GalleryItem> allGalleryItems = new ArrayList<GalleryItem>(50);
    private static List<GalleryItem> newCollectionGalleryItems = new ArrayList<GalleryItem>();


    static {
        longGalleryItems.add(createGalleryItem(0, "Amara Ebony", R.drawable.thumb_long1, R.drawable.gallery_long1, GalleryType.LONG));
        longGalleryItems.add(createGalleryItem(1, "Mac. Ebony 10x4", R.drawable.thumb_long2, R.drawable.gallery_long2, GalleryType.LONG));
        longGalleryItems.add(createGalleryItem(2, "Sm. Fig. Euc. 10x4", R.drawable.thumb_long3, R.drawable.gallery_long3, GalleryType.LONG));
        longGalleryItems.add(createGalleryItem(3, "Sm. Larch 10x4", R.drawable.thumb_long4, R.drawable.gallery_long4, GalleryType.LONG));
        longGalleryItems.add(createGalleryItem(4, "Sm. Oak RTX 10x4", R.drawable.thumb_long5, R.drawable.gallery_long5, GalleryType.LONG));
        longGalleryItems.add(createGalleryItem(5, "Sm. Oak Sap 10x4", R.drawable.thumb_long6, R.drawable.gallery_long6, GalleryType.LONG));

        naturalGalleryItems.add(createGalleryItem(0, "Fig. Ovenkol", R.drawable.thumb_natural1, R.drawable.gallery_natural1, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(1, "Macassor Ebony", R.drawable.thumb_natural2, R.drawable.gallery_natural2, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(2, "Oak Crack", R.drawable.thumb_natural3, R.drawable.gallery_natural3, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(3, "Paldao", R.drawable.thumb_natural4, R.drawable.gallery_natural4, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(4, "Paldao Crown Hz", R.drawable.thumb_natural5, R.drawable.gallery_natural5, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(5, "Santos", R.drawable.thumb_natural6, R.drawable.gallery_natural6, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(6, "Santos GM", R.drawable.thumb_natural7, R.drawable.gallery_natural7, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(7, "Sapeli GM", R.drawable.thumb_natural8, R.drawable.gallery_natural8, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(8, "Sm. Larch", R.drawable.thumb_natural9, R.drawable.gallery_natural9, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(9, "Teak Crack Sap", R.drawable.thumb_natural10, R.drawable.gallery_natural10, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(10, "Teak Crack Sap HZ", R.drawable.thumb_natural11, R.drawable.gallery_natural11, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(11, "White Ash", R.drawable.thumb_natural12, R.drawable.gallery_natural12, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(12, "Walnut Crown HZ", R.drawable.thumb_natural13, R.drawable.gallery_natural13, GalleryType.NATURAL));
        naturalGalleryItems.add(createGalleryItem(13, "Sm. Mak. Pommele", R.drawable.thumb_natural14, R.drawable.gallery_natural14, GalleryType.NATURAL));

        roughGalleryItems.add(createGalleryItem(0, "Burma Teak RTX", R.drawable.thumb_rough1, R.drawable.gallery_rough1, GalleryType.ROUGHCUT));
        roughGalleryItems.add(createGalleryItem(1, "Coast Ebony", R.drawable.thumb_rough2, R.drawable.gallery_rough2, GalleryType.ROUGHCUT));
        roughGalleryItems.add(createGalleryItem(2, "Sm. Met.Oak RTX", R.drawable.thumb_rough3, R.drawable.gallery_rough3, GalleryType.ROUGHCUT));
        roughGalleryItems.add(createGalleryItem(3, "Sm. Sapeli RTX", R.drawable.thumb_rough4, R.drawable.gallery_rough4, GalleryType.ROUGHCUT));
        roughGalleryItems.add(createGalleryItem(4, "Sm. Walnut RTX", R.drawable.thumb_rough5, R.drawable.gallery_rough5, GalleryType.ROUGHCUT));
        roughGalleryItems.add(createGalleryItem(5, "Sm. Sapeli RTX GM", R.drawable.thumb_rough6, R.drawable.gallery_rough6, GalleryType.ROUGHCUT));

        smokedGalleryItems.add(createGalleryItem(0, "Sm. Curupixa", R.drawable.thumb_smoked1, R.drawable.gallery_smoked1, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(1, "Sm. Fig. Euc. Heavy GM", R.drawable.thumb_smoked2, R.drawable.gallery_smoked2, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(2, "Sm. Golden Cherry GM", R.drawable.thumb_smoked3, R.drawable.gallery_smoked3, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(3, "Sm. Larch Interno", R.drawable.thumb_smoked4, R.drawable.gallery_smoked4, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(4, "Sm. Oak Crack", R.drawable.thumb_smoked5, R.drawable.gallery_smoked5, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(5, "Sm. Chestnut Gold", R.drawable.thumb_smoked6, R.drawable.gallery_smoked6, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(6, "Sm. Makore Pommele", R.drawable.thumb_smoked7, R.drawable.gallery_smoked7, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(7, "Sm. Oak Mix Sap", R.drawable.thumb_smoked8, R.drawable.gallery_smoked8, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(8, "Sm. Oak Sap", R.drawable.thumb_smoked9, R.drawable.gallery_smoked9, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(9, "Sm. Santos Sap", R.drawable.thumb_smoked10, R.drawable.gallery_smoked10, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(10, "Sm. Sapeli Crown", R.drawable.thumb_smoked11, R.drawable.gallery_smoked11, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(11, "Sm. Teak Sap", R.drawable.thumb_smoked12, R.drawable.gallery_smoked12, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(12, "Sm. Walnut Brenta", R.drawable.thumb_smoked13, R.drawable.gallery_smoked13, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(13, "Sm. Santos GM", R.drawable.thumb_smoked14, R.drawable.gallery_smoked14, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(14, "Sm. White Oak", R.drawable.thumb_smoked15, R.drawable.gallery_smoked15, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(15, "Sm. ChestNut Crown", R.drawable.thumb_smoked16, R.drawable.gallery_smoked16, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(16, "Sm. Montel Sapeli", R.drawable.thumb_smoked17, R.drawable.gallery_smoked17, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(17, "Sm. Oak Crack", R.drawable.thumb_smoked18, R.drawable.gallery_smoked18, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(18, "Sm.Oak Flacky", R.drawable.thumb_smoked19, R.drawable.gallery_smoked19, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(19, "Sm. Plain Tree", R.drawable.thumb_smoked20, R.drawable.gallery_smoked20, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(20, "Sm. Sapeli Crown", R.drawable.thumb_smoked21, R.drawable.gallery_smoked21, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(21, "Sm. Sapeli Crown HZ", R.drawable.thumb_smoked22, R.drawable.gallery_smoked22, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(22, "Sm. Paldao", R.drawable.thumb_smoked23, R.drawable.gallery_smoked23, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(23, "Sm.Wenge Crown", R.drawable.thumb_smoked24, R.drawable.gallery_smoked24, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(24, "Sm. Santos", R.drawable.thumb_smoked25, R.drawable.gallery_smoked25, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(25, "Sm. Rain Tree Crotch", R.drawable.thumb_smoked26, R.drawable.gallery_smoked26, GalleryType.SMOKED));
        smokedGalleryItems.add(createGalleryItem(26, "Sm. Zebrano HZ", R.drawable.thumb_smoked27, R.drawable.gallery_smoked27, GalleryType.SMOKED));


        newCollectionGalleryItems.add(createGalleryItem(0, "Bobalian", R.drawable.thumb_newcollection1, R.drawable.gallery_newcollection1, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(1, "Crotch", R.drawable.thumb_newcollection2, R.drawable.gallery_newcollection2, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(2, "Kashmiri Walnut", R.drawable.thumb_newcollection3, R.drawable.gallery_newcollection3, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(3, "Live Tree", R.drawable.thumb_newcollection4, R.drawable.gallery_newcollection4, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(4, "Rose Wood Sap", R.drawable.thumb_newcollection5, R.drawable.gallery_newcollection5, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(5, "Rosso", R.drawable.thumb_newcollection6, R.drawable.gallery_newcollection6, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(6, "Santos", R.drawable.thumb_newcollection7, R.drawable.gallery_newcollection7, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(7, "SM Knotty Larch", R.drawable.thumb_newcollection8, R.drawable.gallery_newcollection8, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(8, "SM  Larch", R.drawable.thumb_newcollection9, R.drawable.gallery_newcollection9, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(9, "SM Rain Tree Sap", R.drawable.thumb_newcollection10, R.drawable.gallery_newcollection10, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(10, "SM Santos", R.drawable.thumb_newcollection11, R.drawable.gallery_newcollection11, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(11, "SM Vintage Larch", R.drawable.thumb_newcollection12, R.drawable.gallery_newcollection12, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(12, "Turbo HZ", R.drawable.thumb_newcollection13, R.drawable.gallery_newcollection13, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(13, "Walnut Crotch", R.drawable.thumb_newcollection14, R.drawable.gallery_newcollection14, GalleryType.NEW_COLLECTION));
        newCollectionGalleryItems.add(createGalleryItem(14, "Zigma", R.drawable.thumb_newcollection15, R.drawable.gallery_newcollection15, GalleryType.NEW_COLLECTION));

        allGalleryItems.addAll(longGalleryItems);
        allGalleryItems.addAll(naturalGalleryItems);
        allGalleryItems.addAll(smokedGalleryItems);
        allGalleryItems.addAll(roughGalleryItems);
        allGalleryItems.addAll(newCollectionGalleryItems);
    }

    public static void loadItems() {
    }

    public static List<GalleryItem> getGalleryItems(GalleryType galleryType) {
        List<GalleryItem> items = null;
        if (galleryType == GalleryType.LONG) {
            items = longGalleryItems;
        } else if (galleryType == GalleryType.NATURAL) {
            items = naturalGalleryItems;
        } else if (galleryType == GalleryType.ROUGHCUT) {
            items = roughGalleryItems;
        } else if (galleryType == GalleryType.SMOKED) {
            items = smokedGalleryItems;
        } else if (galleryType == GalleryType.ALL) {
            items = allGalleryItems;
        } else if (galleryType == GalleryType.NEW_COLLECTION) {
            items = newCollectionGalleryItems;
        }
        return items;
    }

    public static List<GalleryItem> getGalleryItems(Context context, GalleryType galleryType) {
        if (galleryType == GalleryType.LONG) {
            return longGalleryItems;
        }
        if (galleryType == GalleryType.NATURAL) {
            return naturalGalleryItems;
        }
        if (galleryType == GalleryType.ROUGHCUT) {
            return roughGalleryItems;
        }
        if (galleryType == GalleryType.SMOKED) {
            return smokedGalleryItems;
        }
        if (galleryType == GalleryType.ALL) {
            return allGalleryItems;
        }
        else if (galleryType == GalleryType.NEW_COLLECTION) {
            return newCollectionGalleryItems;
        }
        if (galleryType == GalleryType.FAVORITE) {
            return getFavoriteGalleryItem(context);
        }
        return null;
    }


    private static GalleryItem createGalleryItem(int itemId, String description, int tumbnilImageResId, int fullImageREsId, GalleryType galleryType) {
        return new GalleryItem(description, tumbnilImageResId, fullImageREsId, itemId, galleryType);
    }

    public static GalleryItem getGalleryItem(int itemId, GalleryType galleryType) {
        try {
            return (GalleryItem) getGalleryItems(galleryType).get(itemId);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<GalleryItem> getFavoriteGalleryItem (Context context){
        List<GalleryItem> favoriteItemList = null;
        Set<String> favoriteImages = PreferenceManager.getDefaultSharedPreferences(context).getStringSet("favoriteImages", null);
        if (favoriteImages != null) {
            favoriteItemList = new ArrayList();
            for (String split : favoriteImages) {
                String[] value = split.split("\\|");
                GalleryItem favItem = getGalleryItem(Integer.parseInt(value[1]), GalleryType.valueOf(value[0]));
                if (favItem != null) {
                    favoriteItemList.add(favItem);
                }
            }
        }
        return favoriteItemList;
    }

    public static void putImageInFavorite(Context context, GalleryItem item) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> favoriteImages = prefs.getStringSet("favoriteImages", null);
        if (favoriteImages == null) {
            favoriteImages = new HashSet();
        }
        favoriteImages.add(new StringBuilder(String.valueOf(item.getGalleryType().toString())).append("|").append(item.getItemId()).toString());
        prefs.edit().putStringSet("favoriteImages", favoriteImages).commit();
    }

    public static void removeImageFromFavorite(Context context, GalleryItem item) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> favoriteImages = prefs.getStringSet("favoriteImages", null);
        if (favoriteImages != null) {
            favoriteImages.remove(new StringBuilder(String.valueOf(item.getGalleryType().toString())).append("|").append(item.getItemId()).toString());
        }
        prefs.edit().putStringSet("favoriteImages", favoriteImages).commit();
    }

    public static boolean isItemFavorite(Context context, GalleryItem item) {
        Set<String> favoriteImages = PreferenceManager.getDefaultSharedPreferences(context).getStringSet("favoriteImages", null);
        if (favoriteImages == null || favoriteImages.isEmpty() || !favoriteImages.contains(new StringBuilder(String.valueOf(item.getGalleryType().toString())).append("|").append(item.getItemId()).toString())) {
            return false;
        }
        return true;
    }

}
