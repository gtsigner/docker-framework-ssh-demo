package org.oeynet.godtoy.daos.interf;

import org.oeynet.godtoy.entites.DocumentEntity;

import java.util.List;

/**
 * Created by zhaojunlike on 6/1/2017.
 */

public interface DocumentDao {

    public DocumentEntity findById(int id);

    public int add(DocumentEntity documentEntity);

    public int getCount(String hql);

    public List<DocumentEntity> getList(String hql, int offset, int length);

    public int[] addAll(List<DocumentEntity> documentEntityList);

    int incHeart(String uuid);

    int incView(String uuid);

    int update(DocumentEntity document);
}
