package org.oeynet.godtoy.services;

import org.oeynet.godtoy.base.Page;
import org.oeynet.godtoy.daos.interf.PictureDao;
import org.oeynet.godtoy.entites.PictureEntity;
import org.oeynet.godtoy.services.interf.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhaojunlike on 6/4/2017.
 */
@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao dao;

    @Override
    public List<PictureEntity> getList(int p, int pLimit, String keywords) {
        return dao.getList(p, pLimit, keywords);
    }

    @Override
    public List<PictureEntity> getList(String keywords) {
        return dao.getList(1, 20, keywords);
    }

    public List<PictureEntity> getList(int p, int ps, int remote_id) {
        String hql = "from PictureEntity where remote_id=" + remote_id;
        int total = dao.getCount("select count(*) from PictureEntity");
        Page page = new Page();
        page.setTotalCount(total);
        page.setPageSize(ps);
        page.setCuttentPageNo(p);
        return dao.getList(hql, ps, page.getPageOffset());
    }

    @Override
    public void insert(PictureEntity entity) {
        dao.insert(entity);
    }
}
