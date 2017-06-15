package org.oeynet.godtoy.daos;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.oeynet.godtoy.daos.interf.DocumentDao;
import org.oeynet.godtoy.entites.DocumentEntity;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Repository
public class DocumentDaoImpl extends HibernateDaoSupport implements DocumentDao {

    @Resource
    public void setMySessionFactory(SessionFactory factory) {
        super.setSessionFactory(factory);
    }

    @Override
    public DocumentEntity findById(int id) {
        return null;
    }

    @Override
    public int add(DocumentEntity documentEntity) {
        Object obj = this.getHibernateTemplate().save(documentEntity);
        System.out.print(obj);
        return 0;
    }

    @Override
    public int getCount(String hql) {
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        return ((Number) query.iterate().next()).intValue();
    }

    @Override
    public List<DocumentEntity> getList(String hql, int offset, int length) {
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(length);
        return (List<DocumentEntity>) query.list();
    }


    @Override
    public int[] addAll(List<DocumentEntity> documentEntityList) {
        return new int[0];
    }

    @Override
    public int incHeart(String uuid) {
        Query query = getSessionFactory().getCurrentSession().createSQLQuery("UPDATE ey_document d set d.heart_count=d.heart_count+1 WHERE d.id='" + uuid + "'");
        return query.executeUpdate();
    }

    @Override
    public int incView(String uuid) {
        Query query = getSessionFactory().getCurrentSession().createSQLQuery("update ey_document d set d.view_count=d.view_count+1 WHERE d.id='" + uuid + "'");
        return query.executeUpdate();
    }

    @Override
    public int update(DocumentEntity document) {
        return 0;
    }
}
