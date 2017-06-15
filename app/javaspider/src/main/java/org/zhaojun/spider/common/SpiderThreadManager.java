package org.zhaojun.spider.common;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaojunlike on 2017/6/3.
 */
class SpiderThreadType {
    public final static int GET_TAG = 1;
    public final static int GET_LIST = 2;
    public final static int GET_PAGE = 3;
    public final static int GET_IMGS = 5;

}

public class SpiderThreadManager {


    private PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    private CloseableHttpClient httpClient;
    private Jedis redis;
    private Map<Integer, List<GetThread>> threads;

    public SpiderThreadManager(Jedis redis) {
        threads = new HashMap<Integer, List<GetThread>>();
        cm.setMaxTotal(100);
        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
    }

    public SpiderThreadManager setThreadCount(SpiderThreadType type, int count) {
        List<GetThread> oldThreads = threads.get(type);

        if (threads.get(SpiderThreadType.GET_TAG) != null) {

        }
        threads.put(SpiderThreadType.GET_TAG, new ArrayList<GetThread>());
        return this;
    }

    public void start() {

        HttpGet httpget = new HttpGet("http://www.cnblogs.com/blogs-hty/p/5290981.html");
        GetThread thread = new GetThread(httpClient, httpget, new CallBackInterf() {
            @Override
            public void callback(HttpEntity entity, CloseableHttpResponse response) {
                //处理
                try {
                    System.out.println("1" + entity.getContent());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //redis.lpop("")


            }
        });
        thread.start();
        //httpClient.execute()


//// start the threads
//        for (int j = 0; j < threads.length; j++) {
//            threads[j].start();
//        }
//
//// join the threads
//        for (int j = 0; j < threads.length; j++) {
//            try {
//                threads[j].join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        httpClient.getConnectionManager().shutdown();
    }
}
