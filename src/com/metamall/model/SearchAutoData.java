package com.metamall.model;

/**
 * <p>.</p>
 *
 * @author Bells
 * @version SearchAutoData.java 1.0 Created@2016-01-21 18:12 $
 */
public class SearchAutoData {

    private String id ="";
    private String content = "";
    public String getId() {
        return id;
    }
    public SearchAutoData setId(String id) {
        this.id = id;
        return this;
    }
    public String getContent() {
        return content;
    }
    public SearchAutoData setContent(String content) {
        this.content = content;
        return this;
    }
}
