package org.oeynet.godtoy.entites;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by zhaojunlike on 6/1/2017.
 */
@Entity
public class DocumentEntity implements Serializable {

    private String id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public int getGood_count() {
        return good_count;
    }

    public void setGood_count(int good_count) {
        this.good_count = good_count;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private String content;
    private String description;
    private String keywords;

    private int picture_id;

    private int create_time;

    private int update_time;
    private int good_count;

    public DocumentEntity() {
    }

    public DocumentEntity(String content, String description, String keywords, int picture_id, int create_time, int update_time, int good_count, int view_count, int category_id, int status) {
        this.content = content;
        this.description = description;
        this.keywords = keywords;
        this.picture_id = picture_id;
        this.create_time = create_time;
        this.update_time = update_time;
        this.good_count = good_count;
        this.view_count = view_count;
        this.category_id = category_id;
        this.status = status;
    }

    private int view_count;
    private int category_id;
    private int status;

    public int getRemote_id() {
        return remote_id;
    }

    private int remote_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRemote_id(int remote_id) {
        this.remote_id = remote_id;
    }
}
