package org.oeynet.godtoy.services;


import org.hibernate.SessionFactory;
import org.oeynet.godtoy.base.Page;
import org.oeynet.godtoy.daos.interf.DocumentDao;
import org.oeynet.godtoy.entites.DocumentEntity;
import org.oeynet.godtoy.services.interf.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.text.Document;
import java.util.List;

/**
 * Created by zhaojunlike on 6/1/2017.
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    public DocumentDao documentDao;


    public List<DocumentEntity> getList() {
        return null;
    }

    //去重复
    public int clearSame() {

        return 0;
    }


    @Override
    public int update(DocumentEntity document) {
        return documentDao.update(document);
    }

    @Override
    public int incView(String uuid) {
        return documentDao.incView(uuid);
    }

    @Override
    public int incHeart(String uuid) {
        return documentDao.incHeart(uuid);
    }

    @Override
    public int add(DocumentEntity document) {
        return documentDao.add(document);
    }

    @Override
    public List<DocumentEntity> getDocumentEntityList() {
        return documentDao.getList("from DocumentEntity order BY remote_id DESC", 0, 20);
    }

    @Override
    public List<DocumentEntity> getDocumentEntityList(DocumentEntity where, int PageSize, int page) {
        //total
        int total = documentDao.getCount("select count(*) from DocumentEntity");
        Page p = new Page();
        p.setTotalCount(total);
        p.setPageSize(PageSize);
        p.setCuttentPageNo(page);
        return documentDao.getList("from DocumentEntity ORDER BY remote_id DESC", p.getPageOffset(), PageSize);
    }
}
