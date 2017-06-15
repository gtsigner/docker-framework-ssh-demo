package org.zhaojun.spider.controller;

import com.sun.deploy.net.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.zhaojun.spider.common.HtmlParser;
import org.zhaojun.spider.conf.Conf;

import org.zhaojun.spider.utils.XxOoUtil;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.HashMap;

import java.util.Map;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static sun.net.www.protocol.http.HttpURLConnection.userAgent;


public class SpiderController {

    private final String webRoot = "http://www.mzitu.com/zhuanti/";//妹子图根目录
    private final String downloadRoot = "http://pic.mmfile.net";//下载图片的根路径

    private Jedis jedis;//临时存
    private String prefix_Page = "xx_p_";//前缀
    private String prefix_Src = "xx_s_";//src前缀
    private String prefix_img = "xx_img_";//图片源地址前缀

    /*redis 数据库中的key*/
    private final String redis_img_key = prefix_img + "list";
    private final String redis_pages_key = prefix_Page + "list";
    private final String redis_src_key = prefix_Src + "list";
    private final String redis_img_url_queue = prefix_img + "url_list";

    private LogsController logsControl;
    private CloseableHttpAsyncClient httpClient;


    //Warn: 如果title response包含了一个带有404的，那说明数据已经采集完成
    //1.获取首页的标签数据：http://www.mzitu.com/zhuanti/
    //2.获取标签页面的列表数据  http://www.mzitu.com/tag/xinggan/
    //3.获取详情页面图片数据，也就是直接高清大图pgae
    //4.下载图片线程


    private Map<String, String> requestHeaders = new HashMap<String, String>();

    public SpiderController() {
        requestHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3047.4 Safari/537.36");
        requestHeaders.put("Upgrade-Insecure-Requests", "1");
        requestHeaders.put("Cache-Control", "max-age=0");
        requestHeaders.put("Host", "www.mzitu.com");
        requestHeaders.put("Cookie", "Hm_lvt_dbc355aef238b6c32b43eacbbf161c3c=1496548387; Hm_lpvt_dbc355aef238b6c32b43eacbbf161c3c=1496551662");
    }

    public void start() {
        //初始化redis
        jedis = new Jedis(Conf.Redis_Host, Conf.Redis_Port);
        logsControl = new LogsController();
        httpClient = HttpAsyncClients.createDefault();
        this.httpClient.start();
        // Execute 100 request in async
        final HttpGet request = new HttpGet(this.webRoot);
        request.setHeader("Connection", "close");
        this.requestHeaders.forEach(request::setHeader);

        //this.requestHeaders.
        httpClient.execute(request, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                try {
                    String html = EntityUtils.toString(httpResponse.getEntity());
                    HtmlParser.parseTags(html);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception e) {

            }

            @Override
            public void cancelled() {

            }
        });

        return;
//        //定时器去发送函数心跳包
//        String url = jedis.lpop(redis_img_key);//获取一个源
//        if (url != null) {
//            System.out.println("Doing Download：开始下载妹子图片-Url=" + url);
//            ImageUtil.downloadImg(url, ImageUtil.createFileName() + ".jpg", Conf.Download_Path + ImageUtil.createSavePath() + "/", new DownloadImgCallBack() {
//                public void success(String fileSaveName, String savePath) {
//                    System.out.println("图片下载成功:" + savePath + fileSaveName);
//                }
//
//                public void error(String msg) {
//                    System.out.println(msg);
//                }
//            });
//        }

    }


    /**
     * 加载列表数据分页，其实只是需要判断最大页面数量
     * 其实这个只需要在开始运行一次就行了，所以不用把isPageDoing设置true
     *
     * @param html
     */
    private void parsePages(String html) {
        //进行缓存
        html = html.replace("\n", "");
        Pattern p = Pattern.compile("<span(.*?)class=\"meta-nav(.*?)screen(.*?)></span>[1-9]{0,5}");
        Matcher matcher = p.matcher(html);
        int maxPage = 0;
        while (matcher.find()) {
            String str = matcher.group();
            str = str.replace("<span class=\"meta-nav screen-reader-text\"></span>", "");
            str = str.replace("\n", "");
            System.out.println("解析分页：" + str);
        }
    }

    /**
     * 加载分页解析后的链接
     *
     * @param urlSrc
     */
    public void pasrseUrlSrc(String urlSrc) {
        HttpGet get = new HttpGet(urlSrc);
        System.out.println("src=" + urlSrc);
        requestHeaders.forEach(get::setHeader);

        //异步
        Future<HttpResponse> futureSrc = httpClient.execute(get, new FutureCallback<HttpResponse>() {
            /**
             * 完成数据
             * @param httpResponse
             */
            public void completed(HttpResponse httpResponse) {
                StatusLine statusLine = httpResponse.getStatusLine();
                if (statusLine.getStatusCode() != 200) {
                    //请求失败，切换代理模式
                    System.out.println("请求失败，切换代理解决");
                } else {
                    HttpEntity entity = httpResponse.getEntity();
                    try {
                        String htmlDom = EntityUtils.toString(entity);//获取到静态页面
                        //进行缓存
                        htmlDom = htmlDom.replace("\n", "");
                        System.out.println(htmlDom);
                        Pattern p = Pattern.compile("<ul id=\"pins\"([\\s\\S]*)</ul>");//匹配所有图片信息
                        Matcher matcher = p.matcher(htmlDom);
                        System.out.println("Doing Src：匹配妹子图列列表");
                        while (matcher.find()) {
                            String str = matcher.group();
                            Pattern pli = Pattern.compile("<li>([\\s\\S]*?)</li>");
                            Matcher matcherLi = pli.matcher(str);
                            while (matcherLi.find()) {
                                String srcc = XxOoUtil.matchSource(matcherLi.group());
                                System.out.println("Doing Src：解析妹子图详细页面路径" + srcc);
                                jedis.rpush(redis_src_key, srcc);//加入redis右边
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            public void failed(Exception e) {

            }

            public void cancelled() {

            }
        });
    }


    /**
     * 这才是获取高清大图的函数，这里还没有下载，只是加载数据到redis
     */
    public void pasrseImgUrl() {
        String url = jedis.lpop(redis_src_key);//获取路径
        if (url == null) {
            System.out.println("等待Src数据解析");
            return;
        }
        HttpGet get = new HttpGet(url);
        System.out.println("采集高清大图:" + url);
        get.setHeader("User-Agent", userAgent);
        Future<HttpResponse> futureSrc = httpClient.execute(get, new FutureCallback<HttpResponse>() {
            /**
             * 完成数据
             * @param httpResponse
             */
            public void completed(HttpResponse httpResponse) {
                StatusLine statusLine = httpResponse.getStatusLine();
                if (statusLine.getStatusCode() != 200) {
                    //请求失败，切换代理模式
                    logsControl.console("请求失败", LogsController.LOG_ERROR);
                } else {
                    HttpEntity entity = httpResponse.getEntity();
                    try {
                        String htmlDom = EntityUtils.toString(entity);//获取到静态页面
                        //进行缓存
                        htmlDom = htmlDom.replace("\n", "");
                        Pattern p = Pattern.compile("http://pic.mmfile.net/2016/(.*?).jpg");//匹配大图地址路径和根
                        Matcher matcher = p.matcher(htmlDom);
                        String ful = "";
                        while (matcher.find()) {
                            ful = matcher.group();
                            System.out.println(ful);
                        }
                        p = Pattern.compile("class=['|\"]pagenavi([\\s\\S]*?)</div>");
                        Matcher matcher1 = p.matcher(htmlDom);
                        int max = 10;
                        String pagesF = "";
                        while (matcher1.find()) {
                            pagesF = matcher1.group();
                        }
                        p = Pattern.compile("<span>[\\d]{1,2}</span>");
                        Matcher matcherPage = p.matcher(pagesF);
                        while (matcherPage.find()) {
                            String pageSS = matcherPage.group();
                            pageSS = pageSS.replace("<span>", "").replace("</span>", "").replace(" ", "");
                            try {
                                max = Integer.parseInt(pageSS);
                            } catch (Exception ex) {

                            }
                        }

                        //加入redis
                        for (int i = 1; i <= max; i++) {
                            StringBuilder builder = new StringBuilder(ful);
                            //开始替换
                            String page = i + "";
                            if (i <= 9) {
                                page = "0" + i;
                            }
                            int at = builder.length() - 6;
                            builder.replace(at, at + 2, page);
                            //array[at] = page.substring(0, 1).toCharArray()[0];//第一个字符
                            //array[at + 1] = page.substring(1, 2).toCharArray()[0];//第二个字符
                            System.out.println("Doing Images：成功获取图片：" + builder.toString());
                            jedis.rpush(redis_img_key, builder.toString());//加入
                        }
                        System.out.println("Doing Images：找到该妹子套组图 共" + max + "张");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            public void failed(Exception e) {
                logsControl.console("对不起解析异常", LogsController.LOG_ERROR);
            }

            public void cancelled() {

            }
        });
    }

    /**
     * 销毁资源
     */
    public void destroy() {
        System.out.println("释放系统资源...");
        jedis = null;
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
