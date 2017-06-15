package org.zhaojun.spider.common;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

public interface CallBackInterf {
    void callback(HttpEntity entity, CloseableHttpResponse response);
}
