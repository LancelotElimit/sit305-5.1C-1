package com.sit305.myapplication.data;

import java.io.Serializable;

public class SportsItem implements Serializable {
    public static final String TYPE_MATCH = "match";
    public static final String TYPE_STORY = "story";

    private final String id;
    private final String type;
    private final String title;
    private final String summary;
    private final String description;
    private final String category;
    private final String meta;
    private final int imageResId;

    public SportsItem(String id, String type, String title, String summary, String description,
                      String category, String meta, int imageResId) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.category = category;
        this.meta = meta;
        this.imageResId = imageResId;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getMeta() {
        return meta;
    }

    public int getImageResId() {
        return imageResId;
    }
}
