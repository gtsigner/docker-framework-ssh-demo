package org.oeynet.godtoy.entites;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by zhaojunlike on 6/4/2017.
 */
@Entity
public class PictureEntity implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoacal_path() {
        return loacal_path;
    }

    public void setLoacal_path(String loacal_path) {
        this.loacal_path = loacal_path;
    }

    public int getRemote_id() {
        return remote_id;
    }

    public void setRemote_id(int remote_id) {
        this.remote_id = remote_id;
    }

    public String getRemote_url() {
        return remote_url;
    }

    public void setRemote_url(String remote_url) {
        this.remote_url = remote_url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int id;
    private String loacal_path;
    private int remote_id;
    private String remote_url;
    private String filename;
    private String hashcode;
    private String description;
    private int document_id;
    private int tag_id;
    private String keywords;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDocument_id() {
        return document_id;
    }

    //对应document_remote_id
    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    private int create_time;
    private int update_time;
    private int status;
}
