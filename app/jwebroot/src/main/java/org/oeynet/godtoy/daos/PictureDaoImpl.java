package org.oeynet.godtoy.daos;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.oeynet.godtoy.daos.interf.PictureDao;
import org.oeynet.godtoy.entites.PictureEntity;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaojunlike on 6/4/2017.
 */
@Repository
public class PictureDaoImpl extends HibernateDaoSupport implements PictureDao {
    @Resource
    public void setMySessionFactory(SessionFactory factory) {
        super.setSessionFactory(factory);
    }

    @Override
    public List<PictureEntity> getList(int p, int pLimit, String keywords) {
        List<PictureEntity> list = (List<PictureEntity>) this.getHibernateTemplate().find("from PictureEntity");
        return list;
    }

    @Override
    public List getList(int p, int ps, int remote_id) {
        return null;
    }

    @Override
    public List<PictureEntity> getList(String hql, int length, int offset) {
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(length);
        return (List<PictureEntity>) query.list();
    }

    @Override
    public int getCount(String hql) {
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        return ((Number) query.iterate().next()).intValue();
    }

    @Override
    public void insert(PictureEntity entity) {
        this.getHibernateTemplate().save(entity);
    }
}
