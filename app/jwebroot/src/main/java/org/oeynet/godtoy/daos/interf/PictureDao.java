package org.oeynet.godtoy.daos.interf;

import org.oeynet.godtoy.entites.PictureEntity;

import java.util.List;

/**
 * Created by zhaojunlike on 6/4/2017.
 */
public interface PictureDao {

    List<PictureEntity> getList(int p, int pLimit, String keywords);

    List getList(int p, int ps, int remote_id);

    List<PictureEntity> getList(String hql, int length, int offset);

    int getCount(String hql);

    void insert(PictureEntity entity);
}
