package com.sit305.myapplication.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class BookmarkStorage {
    private static final String PREFS_NAME = "sports_bookmarks";
    private static final String KEY_BOOKMARKS = "bookmark_ids";

    private BookmarkStorage() {
    }

    public static boolean isBookmarked(Context context, String itemId) {
        return getBookmarkIds(context).contains(itemId);
    }

    public static void toggleBookmark(Context context, String itemId) {
        Set<String> ids = new HashSet<>(getBookmarkIds(context));
        if (ids.contains(itemId)) {
            ids.remove(itemId);
        } else {
            ids.add(itemId);
        }
        getPrefs(context).edit().putStringSet(KEY_BOOKMARKS, ids).apply();
    }

    public static List<SportsItem> getBookmarkedItems(Context context) {
        List<SportsItem> items = new ArrayList<>();
        Set<String> ids = getBookmarkIds(context);
        for (SportsItem item : SportsRepository.getAllItems()) {
            if (ids.contains(item.getId())) {
                items.add(item);
            }
        }
        return items;
    }

    private static Set<String> getBookmarkIds(Context context) {
        return new HashSet<>(getPrefs(context).getStringSet(KEY_BOOKMARKS, new HashSet<>()));
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}
