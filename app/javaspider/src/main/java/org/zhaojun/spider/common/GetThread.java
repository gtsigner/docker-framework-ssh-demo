package org.zhaojun.spider.common;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * Created by zhaojunlike on 2017/6/3.
 */
//http线程
public class GetThread extends Thread {

    private final CloseableHttpClient httpClient;
    private final HttpContext context;
    private final HttpGet httpget;

    private final CallBackInterf callBackInterf;

    public GetThread(CloseableHttpClient httpClient, HttpGet httpget, CallBackInterf callBackInterf) {
        this.httpClient = httpClient;
        this.context = HttpClientContext.create();
        this.httpget = httpget;
        this.callBackInterf = callBackInterf;
    }

    @Override
    public void run() {
        try {
            CloseableHttpResponse response = httpClient.execute(
                    httpget, context);
            try {
                HttpEntity entity = response.getEntity();
                callBackInterf.callback(entity, response);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException ex) {
            // Handle protocol errors
        } catch (IOException ex) {
            // Handle I/O errors
        }
    }

}
