package org.oeynet.godtoy.entites;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zhaojunlike on 6/1/2017.
 */
@Entity
public class EmailEntity {

    private int id;
    private int from_uid;
    private int rec_ud;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(int from_uid) {
        this.from_uid = from_uid;
    }

    public int getRec_ud() {
        return rec_ud;
    }

    public void setRec_ud(int rec_ud) {
        this.rec_ud = rec_ud;
    }
}
