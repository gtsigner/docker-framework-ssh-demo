package org.oeynet.godtoy.daos;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.oeynet.godtoy.daos.interf.SearchDao;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaojunlike on 6/15/2017.
 */
@Repository
public class SearchDaoImpl extends HibernateDaoSupport implements SearchDao {
    @Resource
    public void setMySessionFactory(SessionFactory factory) {
        super.setSessionFactory(factory);
    }

    @Override
    public List search() {
        Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("from DocumentEntity");
        return q.list();
    }
}
