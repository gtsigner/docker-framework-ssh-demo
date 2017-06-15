package org.oeynet.godtoy.services.interf;

import org.oeynet.godtoy.entites.PictureEntity;

import java.util.List;

/**
 * Created by zhaojunlike on 6/4/2017.
 */
public interface PictureService {
    List<PictureEntity> getList(int p, int pLimit, String keywords);

    List<PictureEntity> getList(String keywords);

    List<PictureEntity> getList(int p, int ps, int remote_id);

    void insert(PictureEntity entity);
}
