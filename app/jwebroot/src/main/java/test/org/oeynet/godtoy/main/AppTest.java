package test.org.oeynet.godtoy.main;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.oeynet.godtoy.main.App;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * App Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jun 1, 2017</pre>
 */
public class AppTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: SayHelloWorld()
     */
    @Test
    public void testSayHelloWorld() throws Exception {
//TODO: Test goes here...
        App app = new App();
        app.SayHelloWorld();
    }



    @Test
    public void test() {


    }


} 
