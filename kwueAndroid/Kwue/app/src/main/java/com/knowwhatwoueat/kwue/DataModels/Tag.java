package com.knowwhatwoueat.kwue.DataModels;

/**
 * Created by Cagla Aksoy on 23.11.2016.
 */

public class Tag {
    private String tag_name;
    private String tag_id;
    private String tag_label;
    private String tag_description;

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String a) {
        this.tag_name = a;
    }
    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String a) {
        this.tag_id = a;
    }
    public String getTag_label() {
        return tag_label;
    }

    public void setTag_label(String a) {
        this.tag_label = a;
    }
    public String getTag_description() {
        return tag_description;
    }

    public void setTag_description(String a) {
        this.tag_description = a;
    }
}
