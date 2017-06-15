package test.org.oeynet.godtoy.main;

import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsSpringTestCase;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oeynet.godtoy.contollers.DocumentController;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.swing.text.Document;

/**
 * Created by zhaojunlike on 6/11/2017.
 */
public class ActionTest extends StrutsSpringTestCase {
    private Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
        FlushMode flushMode = FlushMode.NEVER;
        if (flushMode != null) {
            session.setFlushMode(flushMode);
        }
        return session;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");

        Session hibernateSession = getSession(sessionFactory);
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(hibernateSession));
        //已登录
    }

    public void testAll() throws Exception {
        request.addParameter("p", "1");
        request.addParameter("ps", "20");
        request.addParameter("token", "123123123");
        String res = executeAction("/Document_list.action");
        System.out.println(res);
    }

    public void testUserNameCorrect() throws Exception {
        request.addParameter("p", "1");
        request.addParameter("ps", "20");
        request.addParameter("token", "123123123");

        ActionProxy proxy = getActionProxy("/Document_list.action");

        DocumentController accountAction = (DocumentController) proxy.getAction();
        String result = proxy.execute();
        System.out.println(result);
    }
}
