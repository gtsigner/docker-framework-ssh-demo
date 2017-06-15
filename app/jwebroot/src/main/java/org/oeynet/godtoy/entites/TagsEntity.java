package org.oeynet.godtoy.entites;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by zhaojunlike on 6/4/2017.
 */
@Entity
public class TagsEntity implements Serializable {
    private int id;
    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
    private String keywords;
    private int view_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getGood_count() {
        return good_count;
    }

    public void setGood_count(int good_count) {
        this.good_count = good_count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int good_count;
    private int status;
}
